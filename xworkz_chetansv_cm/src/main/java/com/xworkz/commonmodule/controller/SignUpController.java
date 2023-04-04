package com.xworkz.commonmodule.controller;

import java.util.List;
import java.util.Set;

import javax.persistence.NoResultException;
import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.providers.dao.DaoAuthenticationProvider;
import org.springframework.security.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

		return "Signup";
	}

	@PostMapping("/saveSignUp")
	public String onSignUp(Model model, SignUpDTO dto) {
		log.info("running on SignUp post method");

		Set<ConstraintViolation<SignUpDTO>> constraintViolations = signUpService.validateAndSave(dto);

		if (constraintViolations.isEmpty()) {
			model.addAttribute("success", "SignedUp Successfully");
			log.error(" no volation  in controller go to success page");
			return "Signup";

		} else {

			log.info("volations in contoller ");
			model.addAttribute("errors", constraintViolations);
			model.addAttribute("dto", dto);
			return "Signup";

		}

	}

	@PostMapping("/findByUserAndPassword")
	public String findByUserAndPassword(@RequestParam String user, @RequestParam String password, Model model) {
		log.info("running findByUserAndPassword controller" + "property1" + user + "property2" + password);
		try {
			SignUpDTO dto = this.signUpService.findByUserAndPassword(user, password);

			if (dto != null ) {

				model.addAttribute("message", dto.getUserId());
				return "Success";
			}

		} catch (NoResultException nre) {
		}
		model.addAttribute("message", "Userid or password is wrong");

		return "SignIn";

	}

}
//	@PostMapping("/findByUserAndPassword")
//	public String findByUserAndPassword(@RequestParam String user, @RequestParam String password, Model model) {
//		log.info("running findByUserAndPassword controller" + "property1" + user + "property2" + password);
//
//		SignUpDTO dto = this.signUpService.findByUserAndPassword(user, password);
//		
//		if (dto != null) {
//
//			model.addAttribute("message", dto.getUserId());
//			return "Success";
//			
//			
//		} else {
//			model.addAttribute("message", "Userid or password is wrong");
//
//			return "SignIn";
//		}
//
//	}
//
//}
//	@PostMapping("/saveSignUp")
//	public String unique(@RequestParam String user, @RequestParam String email, @RequestParam long mobile,
//			Model model) {
//
//		log.info("running onsearchByName controller" + "property1" + user + "property2" + email + "property3" + mobile);
//		List<SignUpDTO> list = this.signUpService.uniqe(user, email, mobile);
//
//		if (list != null) {
//
//			model.addAttribute("list", list);
//
//		} else {
//			model.addAttribute("error", "Data is exits");
//		}
//		return "index";
//
//	}

//public String onSearchTwoProperties(@RequestParam String name, @RequestParam String color, Model model) {
//	log.info("running onsearchByName controller" + "property1" + name + "property2" + color);
//	List<SareeDTO> list = this.sareeService.findByTwoProperties(name, color);
//
//	if (list != null) {
//
//		model.addAttribute("list", list);
//		
//	} else {
//		model.addAttribute("message", "Data not found");
//	}
//	return "SearchTwoProperties";
//}
// if(dtos!=null){model.addAttribute("error","User already
// exist");return"index";

//List<SignUpDTO> dtos = this.signUpService.uniqe(dto.getUserId(), dto.getEmail(), dto.getMobile());
