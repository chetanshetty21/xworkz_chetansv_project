package com.xworkz.commonmodule.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Properties;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.xworkz.commonmodule.constants.TechEnum;
import com.xworkz.commonmodule.dto.SignUpDTO;
import com.xworkz.commonmodule.entity.SignUpEntity;
import com.xworkz.commonmodule.entity.TechEntity;
import com.xworkz.commonmodule.repository.SignUpRepository;
import lombok.extern.slf4j.Slf4j;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service
@EnableAsync
@Slf4j
public class SignUpServiceImpl implements SignUpService {
	@Autowired
	private SignUpRepository signUpRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	String reSetPassword = DefaultPasswordGenerator.generate(6);

	public SignUpServiceImpl() {
		log.info("creating" + this.getClass().getSimpleName());
	}

//	@Override
//	public Set<ConstraintViolation<SignUpDTO>> validateAndSave(SignUpDTO signUpDTO) {
//		Long emailCount = this.signUpRepository.findByEmail(signUpDTO.getEmail());
//		Long userCount = this.signUpRepository.findByUser(signUpDTO.getUserId());
//		Long mobileCount = this.signUpRepository.findByMobile(signUpDTO.getMobile());
//		log.info("emailCount-" + emailCount);
//		log.info("userCount-" + userCount);
//		log.info("mobileCount-" + mobileCount);
//		Set<ConstraintViolation<SignUpDTO>> constraintViolations = validate(signUpDTO);
//		if (constraintViolations != null && !constraintViolations.isEmpty()) {
//			log.error("constraintViolations in SignUpDTO" + signUpDTO);
//			return constraintViolations;
//		}
//		if (signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
//			log.info("Password is not matching");
//			return null;
//		}if (emailCount == 0 && userCount == 0 && mobileCount == 0) {
//			log.info("No Violations procceding to save");
//
//			SignUpEntity entity = new SignUpEntity();
//			entity.setUserId(signUpDTO.getUserId());
//			entity.setEmail(signUpDTO.getEmail());
//			entity.setMobile(signUpDTO.getMobile());
//			entity.setAcceptAgreement(signUpDTO.isAcceptAgreement());
//			entity.setCreatedBy(signUpDTO.getUserId());
//			entity.setCreatedDate(LocalDateTime.now());
//			entity.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
//			entity.setResetPassword(false);
//			//entity.setPasswordChangedTime(LocalTime.of(0, 0, 0));
//			// BeanUtils.copyProperties(userDTO, entity);
//
//			boolean saved = this.signUpRepository.save(entity);
//			log.info("Saved in Entity-" + saved);
//
//			if (saved) {
//				boolean sent = this.sendMail(signUpDTO.getEmail(), "Thanks for registration");
//				log.info("Email sent -:" + sent);
//
//			}
//
//		}
//		return Collections.emptySet();
//	}
//		
	@Override
	public Set<ConstraintViolation<SignUpDTO>> validateAndSave(SignUpDTO signUpDTO) {
		Long emailCount = this.signUpRepository.findByEmail(signUpDTO.getEmail());
		Long userCount = this.signUpRepository.findByUser(signUpDTO.getUserId());
		Long mobileCount = this.signUpRepository.findByMobile(signUpDTO.getMobile());
		log.info("emailCount-" + emailCount);
		log.info("userCount-" + userCount);
		log.info("mobileCount-" + mobileCount);
		if (emailCount == 0 && userCount == 0 && mobileCount == 0) {

			Set<ConstraintViolation<SignUpDTO>> constraintViolations = validate(signUpDTO);
			if (constraintViolations != null && !constraintViolations.isEmpty()) {
				log.error("constraintViolations in SignUpDTO" + signUpDTO);
				return constraintViolations;
			} else {
				log.info("constraintViolations does not exist data is good" + signUpDTO);
				if (signUpDTO.getPassword().equals(signUpDTO.getConfirmPassword())) {
					SignUpEntity entity = new SignUpEntity();

					entity.setUserId(signUpDTO.getUserId());
					entity.setEmail(signUpDTO.getEmail());
					entity.setMobile(signUpDTO.getMobile());
					// entity.setPassword(signUpDTO.getPassword());
					entity.setCreatedBy(signUpDTO.getUserId());
					entity.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));
					// entity.setCreatedDate(LocalDateTime.now());

					boolean saved = this.signUpRepository.save(entity);
					log.info("entity is save validate and save method" + saved);

					if (saved) {
						boolean sent = this.sendMail(signUpDTO.getEmail(), "THANKS FOR RERGISTRATION");
						log.info("Email sent -:" + sent);
						return Collections.emptySet();
					}

				} else {
					log.error("password must be same");
				}
			}

		} else {

			log.info("user id or email or mobile does  exits");
			return null;
		}
		return null;

	}

	private Set<ConstraintViolation<SignUpDTO>> validate(SignUpDTO signUpDTO) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<SignUpDTO>> violations = validator.validate(signUpDTO);
		return violations;
	}

	@Override
	public Long findByEmail(String email) {
		Long emailcount = this.signUpRepository.findByEmail(email);

		return emailcount;
	}

	@Override
	public Long findByMobile(Long mobile) {
		Long mobilecount = this.signUpRepository.findByMobile(mobile);
		return mobilecount;
	}

	@Override
	public Long findByUser(String user) {
		Long userCount = this.signUpRepository.findByUser(user);
		return userCount;
	}

	@Override
	public SignUpDTO findByUserAndPassword(String userId, String password) {
		SignUpEntity entity = this.signUpRepository.findByUserAndPassword(userId);
		log.info("running findByUserAndPassword in service " + "property1" + userId);
		SignUpDTO dto = new SignUpDTO();
		log.info(entity.getUserId());
		log.info(entity.getPassword());
		// log.info(entity.getLoginCount());
		// dto.setUserId(entity.getUserId());
		BeanUtils.copyProperties(entity, dto);
//		log.info(entity.getUserId());
//		log.info(entity.getPassword());

		log.info("password covertying " + passwordEncoder.matches(password, entity.getPassword())); // passwordEncoder.encode(dto.getPassword());

		if (entity.getLoginCount() >= 3) {
			log.info("Running in Login count condition");
			return dto;
		}
		if (passwordEncoder.matches(password, entity.getPassword()) && dto.getUserId().equals(userId)) {

			log.info("data is  same");
			log.info("dto===" + dto);
			return dto;
		} else {
			this.signUpRepository.logincount(userId, entity.getLoginCount() + 1);
			// this.signUpRepository.logincount(user, entity.getLoginCount() + 1);
			log.info("count of login" + entity.getLoginCount() + 1);
			log.info("data is  not  same");
			return null;
		}

	}

	@Override
	public SignUpDTO reSetPassword(String email) {
		log.info("ReSetd password--" + reSetPassword);
		SignUpEntity entity = this.signUpRepository.reSetPassword(email);
		if (entity != null) {
			entity.setPassword(passwordEncoder.encode(reSetPassword));
			entity.setUpdatedBy("System");
			entity.setUpdatedDate(LocalDateTime.now());
			entity.setLoginCount(0);
			entity.setResetPassword(true);
			entity.setPasswordChangedTime(LocalTime.now().plusSeconds(120));
			boolean update = this.signUpRepository.update(entity);
			if (update) {
				sendMail(entity.getEmail(), "your reset password is->" + reSetPassword);

			}
			log.info("Updated---" + update);
			SignUpDTO updatedDto = new SignUpDTO();
			BeanUtils.copyProperties(entity, updatedDto);
			return updatedDto;
		}
		return SignUpService.super.reSetPassword(email);
	}

	@Override
	public SignUpDTO updatePassword(String userId, String password, String confirmPassword) {
		SignUpEntity uentity = new SignUpEntity();
		if (password.equals(confirmPassword)) {
			uentity.setUserId(userId);
			uentity.setPassword(passwordEncoder.encode(password));
			uentity.setResetPassword(false);
			boolean passwordUpdated = this.signUpRepository.updatePassword(userId, passwordEncoder.encode(password),
					false, LocalTime.of(0, 0, 0));
			log.info("passwordUpdated--" + passwordUpdated);

		}
		return SignUpService.super.updatePassword(userId, password, confirmPassword);
	}

	@Override
	public SignUpDTO updateData(String userId, String email, Long mobile) {
		SignUpEntity upEntity = this.signUpRepository.reSetPassword(email);

		log.info("userId: " + userId + "mobile : " + mobile);

		upEntity.setUserId(userId);
		upEntity.setMobile(mobile);
		boolean updated = this.signUpRepository.update(upEntity);
		log.info("updated--" + updated);
		return SignUpService.super.updateData(userId, email, mobile);

	}

	@Override
	public SignUpDTO updateImg(String userId, Long mobile, String email, String path) {
		SignUpEntity upEntity = this.signUpRepository.reSetPassword(email);
		log.info("email: " + email + "image name: " + path);
		upEntity.setUserId(userId);
		upEntity.setMobile(mobile);
		upEntity.setFileName(path);
		boolean updated = this.signUpRepository.update(upEntity);
		log.info("updated--" + updated);
		return SignUpService.super.updateImg(userId, mobile, email, path);
	}

	@Async
	@Override
	public boolean sendMail(String email, String text) {
		System.out.println(Thread.currentThread().getName());
		String portNumber = "587";// 485,587,25
		String hostName = "smtp.office365.com";
		String fromEmail = "chetanveer21@outlook.com";
		String password = "Chetan@21";
		String to = email;

		Properties prop = new Properties();
		prop.put("mail.smtp.host", hostName);
		prop.put("mail.smtp.port", portNumber);
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.debug", "true");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.transport.protocol", "smtp");

		Session session = Session.getInstance(prop, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(fromEmail, password);
			}
		});
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(fromEmail));
			message.setSubject("Registration  Completed");
			message.setText("Thanks for registration");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			Transport.send(message);
			log.info("mail sent sucessfully");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return true;
	}

	public final static class DefaultPasswordGenerator {
		private static final String[] charCategories = new String[] { "abcdefghijklmnopqrstuvwxyz",
				"ABCDEFGHIJKLMNOPQRSTUVWXYZ", "0123456789" };

		public static String generate(int length) {
			StringBuilder password = new StringBuilder(length);
			Random random = new Random(System.nanoTime());

			for (int i = 0; i < length; i++) {
				String charCategory = charCategories[random.nextInt(charCategories.length)];
				int position = random.nextInt(charCategory.length());
				password.append(charCategory.charAt(position));
			}

			return new String(password);
		}

	}

	@Override
	public SignUpDTO addTech(String userId, TechEntity techEntity) {
		SignUpEntity entity = this.signUpRepository.findByUserAndPassword(userId);
		entity.setCreatedBy(userId);
		techEntity.setSignUpEntity(entity);
		boolean saved = this.signUpRepository.saveTech(techEntity);
		log.info("entity is save validate and save method" + saved);

		return SignUpService.super.addTech(userId, techEntity);

	}

	@Override
	public List<TechEntity> tech(String userId) {
		SignUpEntity entity = this.signUpRepository.findByUserAndPassword(userId);
		List<TechEntity> list = entity.getTech();
		return list;

	}

	@Override
	public List<TechEntity> findByProperties(String techName, String userID, String lang, double version, String owner,
			String supportFrom, String supportTo, String license, String openSoure, TechEnum osType) {
		SignUpEntity entity = this.signUpRepository.findByUserAndPassword(userID);
		int id = entity.getId();

		List<TechEntity> list = this.signUpRepository.findByProperties(techName, userID, lang, version, owner,
				supportFrom, supportTo, license, openSoure, osType);

		log.info("Search List" + list);
		return list;
	}
}
//	@Async
//	@Scheduled(fixedRate = 10000)
//	public void scheduleFixedRateTaskAsync() throws InterruptedException {
//		System.out.println("Fixed rate task async - " + System.currentTimeMillis() / 1000);
//		// Thread.sleep(2000);
//	}
