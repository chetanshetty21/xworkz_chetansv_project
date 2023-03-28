package com.xworkz.commonmodule.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.commonmodule.entity.SignUpEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SignUpRepositoryImpl implements SignUpRepository {
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public boolean save(SignUpEntity signUpEntity) {
		log.info("Running save in Repository");
		EntityManager em = this.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(signUpEntity);
		et.commit();
		em.close();

		return false;
	}
}
