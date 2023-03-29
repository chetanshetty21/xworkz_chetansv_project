package com.xworkz.commonmodule.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<SignUpDTO>> constraintViolations = validator.validate(signUpDTO);
		if (constraintViolations != null && !constraintViolations.isEmpty()
				|| !signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
			System.out.println("error in passwrd");
			log.error("constraintViolations in SignUpDTO" + signUpDTO);
			return constraintViolations;
		} else {
			log.info("constraintViolations does not exist data is good" + signUpDTO);
			System.out.println("no  error in passwrd");
			SignUpEntity entity = new SignUpEntity();

			entity.setUserId(signUpDTO.getUserId());
			entity.setEmail(signUpDTO.getEmail());
			entity.setMobile(signUpDTO.getMobile());
			entity.setPassword(signUpDTO.getPassword());
			entity.setCreatedBy(signUpDTO.getUserId());
			entity.setCreatedDate(LocalDateTime.now());
			
			

			boolean saved = this.signUpRepository.save(entity);
			log.info("entity is save" + saved);
			return Collections.emptySet();
		}

	}
}


//
//}

//	private Set<ConstraintViolation<SignUpDTO>> validate(SignUpDTO signUpDTO) {
//		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
//		Validator validator = factory.getValidator();
//		Set<ConstraintViolation<SignUpDTO>> violations = validator.validate(signUpDTO);
//		return violations;
//	}

//@Override
//public List<SareeDTO> findByTwoProperties(String name, String color) {
//	log.info("running findByTwoProperties in service " + "property1" + name + "property2" + color);
//	if (name != null && !name.isEmpty() || color != null && !color.isEmpty()) {
//		log.info("Data is valid ....calling repo");
//		List<SareeEntity> entities = this.dao.findByTwoProperties(name, color);
//		List<SareeDTO> dtos = new ArrayList<SareeDTO>();
//		for (SareeEntity entity : entities) {
//			SareeDTO dto = new SareeDTO();
//			BeanUtils.copyProperties(entity, dto);
//			//dto.setName(entity.getName()); getu  first in bean utils 
//			dtos.add(dto);
//		}
//		log.info("size of dtos" + dtos.size());
//		log.info("size of entites" + entities.size());
//		return dtos;
//	}
//	return SareeService.super.findByTwoProperties(name, color);
//}
