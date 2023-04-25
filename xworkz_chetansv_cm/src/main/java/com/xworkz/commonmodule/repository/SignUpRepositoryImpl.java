package com.xworkz.commonmodule.repository;

import java.time.LocalTime;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;

import com.xworkz.commonmodule.constants.TechEnum;
import com.xworkz.commonmodule.entity.SignUpEntity;
import com.xworkz.commonmodule.entity.TechEntity;

import lombok.extern.slf4j.Slf4j;

@Repository
@Slf4j
public class SignUpRepositoryImpl implements SignUpRepository {
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean save(SignUpEntity signUpEntity) {
		log.info("Running save in Repository");
		EntityManager em = this.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(signUpEntity);
		et.commit();
		em.close();

		return true;
	}

	@Override
	public List<SignUpEntity> uniqe(String user, String email, long mobile) {
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

	@Override
	public Long findByUser(String user) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findByUser");
			query.setParameter("useridby", user);
			log.info("Query :" + query);
			Object object = query.getSingleResult();
			Long value = (Long) object;
			log.info("value of the query" + value);
			return value;
		} finally {
			manager.close();
			log.info("released the connection...");
		}

	}

	@Override
	public Long findByEmail(String email) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findByEmail");
			query.setParameter("emailby", email);
			log.info("Query :" + query);
			Object object = query.getSingleResult();
			Long value = (Long) object;
			log.info("value of the query" + value);
			return value;
		} finally {
			manager.close();
			log.info("released the connection...");
		}

	}

	@Override
	public Long findByMobile(Long mobile) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findByMobile");
			query.setParameter("mobileby", mobile);
			log.info("Query :" + query);
			Object object = query.getSingleResult();
			Long value = (Long) object;
			log.info("value of the query" + value);
			return value;
		} finally {
			manager.close();
			log.info("released the connection...");
		}

	}

	@Override
	public SignUpEntity findByUserAndPassword(String userId) {
		EntityManager manager = this.entityManagerFactory.createEntityManager();
		try {
			Query query = manager.createNamedQuery("findByUserAndPassword");
			log.info("Query:" + query);
			query.setParameter("useridby", userId);

			Object obj = query.getSingleResult();

			SignUpEntity entity = (SignUpEntity) obj;

			log.info("total list found in repo");
			log.info("" + entity);
			return entity;

			// Ignore this because as per your logic this is ok!

		} finally {
			manager.close();
			log.info("released the connection...");

		}

	}

	@Override
	public boolean update(SignUpEntity signupEntity) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			em.merge(signupEntity);
			et.commit();
			return true;
		} finally {
			em.close();
		}

	}

	@Override
	public boolean updatePassword(String userId, String password, boolean resetPassword,
			LocalTime passwordChangedTime) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			Query query = em.createNamedQuery("updatePassword");
			query.setParameter("useridby", userId);
			query.setParameter("uppasswordby", password);
			query.setParameter("uresetpasrdby", resetPassword);
			query.setParameter("passwordChangedTimeby", passwordChangedTime);
			query.executeUpdate();
			et.commit();
			return true;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean logincount(String userId, int count) {
		log.info("count:" + count);
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			EntityTransaction et = em.getTransaction();
			et.begin();
			Query query = em.createNamedQuery("updateLoginCount");
			query.setParameter("useridby", userId);
			query.setParameter("countby", count);
			query.executeUpdate();
			et.commit();
			return true;
		} finally {
			em.close();

		}
	}

	@Override
	public SignUpEntity reSetPassword(String email) {
		EntityManager em = this.entityManagerFactory.createEntityManager();
		try {
			Query query = em.createNamedQuery("emailid");
			query.setParameter("emailby", email);
			Object object = query.getSingleResult();
			SignUpEntity entity = (SignUpEntity) object;
			log.info("" + entity);
			return entity;
		} finally {
			em.close();
		}
	}

	@Override
	public boolean saveTech(TechEntity techEntity) {
		log.info("Running save in Repository");
		EntityManager em = this.entityManagerFactory.createEntityManager();
		EntityTransaction et = em.getTransaction();

		et.begin();
		em.persist(techEntity);
		et.commit();
		em.close();

		return true;
	}

//	@Override
//	public List<TechEntity> findByTechName(String techName, String userID) {
//		EntityManager manager = this.entityManagerFactory.createEntityManager();
//		try {
//			Query query = manager.createNamedQuery("findBytechName");
//			System.out.println("Query:" + query);
//			query.setParameter("techNameby", techName);
//			query.setParameter("signupIdBy", userID);
//			List<TechEntity> list = query.getResultList();
//			System.out.println("total list found in repo" + list.size());
//			return list;
//		} finally {
//			manager.close();
//			System.out.println("released the connection...");
//		}
//	}
}
