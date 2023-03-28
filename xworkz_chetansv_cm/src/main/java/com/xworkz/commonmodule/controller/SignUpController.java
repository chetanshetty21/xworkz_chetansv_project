package com.xworkz.commonmodule.controller;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.xworkz.commonmodule.dto.SignUpDTO;
import com.xworkz.commonmodule.service.SignUpService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class SignUpController {
	@Autowired
	private SignUpService signUpService;

	@GetMapping("/saveSignUp")
	public String onSignUp(Model model) {
		log.info("this is onSignUp get method");

		return "SignUp";
	}

	@PostMapping("/saveSignUp")
	public String onSignUp(Model model, SignUpDTO dto) {
		log.info("running on SignUp post method");
		Set<ConstraintViolation<SignUpDTO>> violations = signUpService.validateAndSave(dto);
		if (violations.isEmpty()) {
			log.info("no volations in contoller go to success page");
			return "SignUpSucess";
		}
		model.addAttribute("errors", violations);
		model.addAttribute("dto", dto);
		log.error("volation in controller");
		return "SignUp";

	}

}
