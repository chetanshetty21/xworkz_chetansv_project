package com.xworkz.commonmodule.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

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

	@Override
	public List<SignUpEntity> uniqe( String user, String email, long mobile) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("unique");
			query.setParameter("userby", user);
			query.setParameter("emailby", email);
			query.setParameter("mobileby", mobile);
			log.info("Query  :" + query);
			List<SignUpEntity> list = query.getResultList();
			log.info("total list found in repo" + list.size());
			return list;
		} finally {
			em.close();
			log.info("released the connection...");
		}
	}

}
