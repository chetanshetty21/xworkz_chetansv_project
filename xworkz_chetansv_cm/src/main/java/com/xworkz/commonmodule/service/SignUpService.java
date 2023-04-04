package com.xworkz.commonmodule.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.xworkz.commonmodule.dto.SignUpDTO;
import com.xworkz.commonmodule.entity.SignUpEntity;

public interface SignUpService {
	Set<ConstraintViolation<SignUpDTO>> validateAndSave(SignUpDTO signUpDTO);

	default List<SignUpDTO> uniqe(String user, String email, long mobile) {
		return Collections.emptyList();
	}

	default Long findByUser(String user) {
		return null;
	}

	default Long findByEmail(String email) {
		return null;
	}

	default Long findByMobile(Long mobile) {
		return null;
	}


	default SignUpDTO findByUserAndPassword(String user,String passWord) {
		return null;
	}
}
