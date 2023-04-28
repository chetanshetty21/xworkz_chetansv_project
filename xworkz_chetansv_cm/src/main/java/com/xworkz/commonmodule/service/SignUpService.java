package com.xworkz.commonmodule.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.xworkz.commonmodule.constants.TechEnum;
import com.xworkz.commonmodule.dto.SignUpDTO;
import com.xworkz.commonmodule.entity.TechEntity;

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

	default SignUpDTO findByUserAndPassword(String userId, String passWord) {
		return null;
	}

	boolean sendMail(String email, String text);

	default SignUpDTO reSetPassword(String email) {
		return null;
	}

	default SignUpDTO updateImg(String userId, Long mobile, String email, String path) {
		return null;
	}

	default SignUpDTO updateData(String userId, String email, Long mobile) {
		return null;
	}

	default SignUpDTO updatePassword(String userId, String password, String confirmPassword) {
		return null;
	}

	default SignUpDTO addTech(String userId, TechEntity techEntity) {
		return null;
	}

	default List<TechEntity> tech(String userid) {
		return null;

	}

	default List<TechEntity> findByProperties(String techName, String userID, String lang, double version, String owner,
			String supportFrom, String supportTo, String license, String openSoure, TechEnum osType) {
		return null;

	}
}