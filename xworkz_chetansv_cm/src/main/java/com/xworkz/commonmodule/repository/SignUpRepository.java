package com.xworkz.commonmodule.repository;

import java.time.LocalTime;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestParam;

import com.xworkz.commonmodule.constants.TechEnum;
import com.xworkz.commonmodule.entity.SignUpEntity;
import com.xworkz.commonmodule.entity.TechEntity;

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

	default SignUpEntity findByUserAndPassword(String userId) {
		return null;

	}

	default SignUpEntity reSetPassword(String email) {
		return null;
	}
//	default SignUpEntity updateData(String userId,Long mobile,String email) {
//		return null;
//	}

	boolean update(SignUpEntity signupEntity);

	boolean updatePassword(String userId, String password, boolean resetPassword, LocalTime passwordChangedTime);

	// boolean updateProfile(String userId, Long mobile, String email);

	boolean logincount(String userId, int count);

	boolean saveTech(TechEntity techEntity);

	default List<TechEntity> findByProperties(String techName, String userID, String lang, double version, String owner,
			String supportFrom, String supportTo, String license, String openSoure, TechEnum osType) {
		return null;
	}
	

	

}
