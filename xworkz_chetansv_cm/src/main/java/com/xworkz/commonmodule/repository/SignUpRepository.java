package com.xworkz.commonmodule.repository;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.xworkz.commonmodule.entity.SignUpEntity;

public interface SignUpRepository {
	boolean save(SignUpEntity signUpEntity);

	default List<SignUpEntity> uniqe(String user, String email, long mobile) {
		return null;
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

	default SignUpEntity findByUserAndPassword(String user) {
		return null;
		
	}
		
	
}
