package com.xworkz.commonmodule.service;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.commonmodule.dto.SignUpDTO;

public interface SignUpService {
	Set<ConstraintViolation<SignUpDTO>> validateAndSave(SignUpDTO signUpDTO);

	default List<SignUpDTO> uniqe(String user, String email, long mobile) {
		return Collections.emptyList();
	}
}
