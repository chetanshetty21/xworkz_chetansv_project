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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.userdetails.UserDetailsService;
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
	@Autowired
	private PasswordEncoder passwordEncoder;

	public SignUpServiceImpl() {
		log.info("creating" + this.getClass().getSimpleName());
	}

	@Override
	public Set<ConstraintViolation<SignUpDTO>> validateAndSave(SignUpDTO signUpDTO) {
		Long emailCount = this.signUpRepository.findByEmail(signUpDTO.getEmail());
		Long userCount = this.signUpRepository.findByUser(signUpDTO.getUserId());
		Long mobileCount = this.signUpRepository.findByMobile(signUpDTO.getMobile());
		log.info("emailCount-" + emailCount);
		log.info("userCount-" + userCount);
		log.info("mobileCount-" + mobileCount);
		if (emailCount == 0 && userCount == 0 && mobileCount == 0) {

			Set<ConstraintViolation<SignUpDTO>> constraintViolations = validate(signUpDTO);
			if (constraintViolations != null && !constraintViolations.isEmpty()) {
				log.error("constraintViolations in SignUpDTO" + signUpDTO);
				return constraintViolations;
			} else {
				log.info("constraintViolations does not exist data is good" + signUpDTO);
				if (signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
					SignUpEntity entity = new SignUpEntity();

					entity.setUserId(signUpDTO.getUserId());
					entity.setEmail(signUpDTO.getEmail());
					entity.setMobile(signUpDTO.getMobile());
					// entity.setPassword(signUpDTO.getPassword());
					entity.setCreatedBy(signUpDTO.getUserId());
					entity.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
					// entity.setCreatedDate(LocalDateTime.now());

					boolean saved = this.signUpRepository.save(entity);
					log.info("entity is save validate and save method" + saved);
					return Collections.emptySet();

				} else {
					log.error("password must be same");
				}
			}

		} else {

			log.info("user id or email or mobile does  exits");
			return null;
		}
		return null;

	}

	private Set<ConstraintViolation<SignUpDTO>> validate(SignUpDTO signUpDTO) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<SignUpDTO>> violations = validator.validate(signUpDTO);
		return violations;
	}

	@Override
	public Long findByEmail(String email) {
		Long emailcount = this.signUpRepository.findByEmail(email);

		return emailcount;
	}

	@Override
	public Long findByMobile(Long mobile) {
		Long mobilecount = this.signUpRepository.findByMobile(mobile);
		return mobilecount;
	}

	@Override
	public Long findByUser(String user) {
		Long userCount = this.signUpRepository.findByUser(user);
		return userCount;
	}

	@Override
	public SignUpDTO findByUserAndPassword(String user, String password) {
		SignUpEntity entity = this.signUpRepository.findByUserAndPassword(user);
		log.info("running findByUserAndPassword in service " + "property1" + user);
		SignUpDTO dto = new SignUpDTO();
		dto.setUserId(entity.getUserId());
		log.info(entity.getUserId());
		log.info(entity.getPassword());
		log.info("password covertying " + passwordEncoder.matches(password, entity.getPassword())); // passwordEncoder.encode(dto.getPassword());
		if (passwordEncoder.matches(password, entity.getPassword()) && entity.getUserId().equals(user)) {

			log.info("data is  same");
			return dto;
		} else {
			log.info("data is  not  same");

			return null;
		}

	}
}