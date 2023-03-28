package com.xworkz.commonmodule.service;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.xworkz.commonmodule.dto.SignUpDTO;

public interface SignUpService {
	Set<ConstraintViolation<SignUpDTO>> validateAndSave(SignUpDTO signUpDTO);
}
