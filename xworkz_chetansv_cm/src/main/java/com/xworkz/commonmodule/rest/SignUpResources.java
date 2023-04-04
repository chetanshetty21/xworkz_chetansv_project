package com.xworkz.commonmodule.rest;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.xworkz.commonmodule.dto.SignUpDTO;
import com.xworkz.commonmodule.service.SignUpService;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.asm.Advice.Exit;

@RestController
@RequestMapping("/")
@Slf4j
@EnableWebMvc
public class SignUpResources {
	@Autowired
	private SignUpService signUpService;

	public SignUpResources() {
		log.info("creating" + this.getClass().getSimpleName());
	}

	@GetMapping(value = "/userId/{user}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onUser(@PathVariable String user) {

		Long userId1 = this.signUpService.findByUser(user);
		

		if (userId1 == 0) {
			log.info("userId is not exists");
			return "";

		} else {
			return "UserID already exists";

		}

	}

	@GetMapping(value = "/emailId/{email}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onEmail(@PathVariable String email) {
		Long emailId = this.signUpService.findByEmail(email);
		

		if (emailId == 0 ) {
			log.info("email is not exists");
			return "";
		} else {
			return "email already exsist";
		}
	}

	@GetMapping(value = "/mobile/{number}", produces = MediaType.APPLICATION_JSON_VALUE)
	public String onMobile(@PathVariable Long number) {
		Long numberid = this.signUpService.findByMobile(number);
		

		if (numberid == 0) {
			log.info("mobile is not exists");
			return "";

		} else {
			return "Mobile Number exsist";
		}
	}

}
