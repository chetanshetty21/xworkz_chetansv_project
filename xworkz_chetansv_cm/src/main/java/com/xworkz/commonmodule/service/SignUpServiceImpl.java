package com.xworkz.commonmodule.service;

import java.util.Collections;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xworkz.commonmodule.dto.SignUpDTO;
import com.xworkz.commonmodule.entity.SignUpEntity;
import com.xworkz.commonmodule.repository.SignUpRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SignUpServiceImpl implements SignUpService {
	@Autowired
	private SignUpRepository signUpRepository;

	public SignUpServiceImpl() {
		log.info("creating" + this.getClass().getSimpleName());
	}

	@Override
	public Set<ConstraintViolation<SignUpDTO>> validateAndSave(SignUpDTO signUpDTO) {
		Set<ConstraintViolation<SignUpDTO>> constraintViolations = validate(signUpDTO);
		if (constraintViolations != null && !constraintViolations.isEmpty()
				&& signUpDTO.getPassword().matches(signUpDTO.getConfirmPassword())) {
			log.error("constraintViolations in SignUpDTO" + signUpDTO);
			return constraintViolations;
		} else {
			log.info("constraintViolations does not exist data is good" + signUpDTO);
			SignUpEntity entity = new SignUpEntity();
			BeanUtils.copyProperties(signUpDTO, entity);
			this.signUpRepository.save(entity);
			return Collections.emptySet();
		}

	}

	private Set<ConstraintViolation<SignUpDTO>> validate(SignUpDTO signUpDTO) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<SignUpDTO>> violations = validator.validate(signUpDTO);
		return violations;
	}

}
