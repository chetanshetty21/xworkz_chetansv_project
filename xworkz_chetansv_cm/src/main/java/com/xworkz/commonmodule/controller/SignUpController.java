package com.xworkz.commonmodule.controller;

import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.beans.factory.annotation.Autowired;
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

		return "index";
	}

	@PostMapping("/saveSignUp")
	public String onSignUp(Model model, SignUpDTO dto) {
		log.info("running on SignUp post method");
		if (dto.getPassword().equals(dto.getConfirmPassword())) {
			Set<ConstraintViolation<SignUpDTO>> constraintViolations = signUpService.validateAndSave(dto);
			List<SignUpDTO> dtos = this.signUpService.uniqe(dto.getUserId(), dto.getEmail(), dto.getMobile());
			if (constraintViolations != null && !constraintViolations.isEmpty()) {
				log.info("volations in contoller ");
				model.addAttribute("errors", constraintViolations);
				model.addAttribute("dto", dto);
				return "index";
			} if (dtos!=null) {
				model.addAttribute("error", "User already exist");
				return "index";
				
			} else {

				model.addAttribute("success", "SignedUp Successfully");
				log.error(" no volation  in controller go to success page");
				return "index";

			}
		} else {
			model.addAttribute("error", "Password not matching");
			return "index";
		}

	}


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
}

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
