package com.xworkz.commonmodule.repository;

import java.util.List;

import com.xworkz.commonmodule.entity.SignUpEntity;

public interface SignUpRepository {
	boolean save(SignUpEntity signUpEntity);
	default List<SignUpEntity> uniqe( String user,String email,long mobile) {
		return null;
	}
}
