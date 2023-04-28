package com.xworkz.commonmodule.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import javax.persistence.NamedQuery;
import javax.persistence.NoResultException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.xworkz.commonmodule.constants.TechEnum;
import com.xworkz.commonmodule.dto.SignUpDTO;
import com.xworkz.commonmodule.entity.TechEntity;
import com.xworkz.commonmodule.service.SignUpService;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class SignUpController {
	@Autowired
	private SignUpService signUpService;
	private List<String> openSoure = Arrays.asList("Yes", "NO");
	private List<TechEnum> techEnum = new ArrayList<TechEnum>(Arrays.asList(TechEnum.values()));

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
	public String findByUserAndPassword(@RequestParam String userId, String password, Model model,
			HttpServletRequest request) {
		log.info("running findByUserAndPassword controller" + "property1" + userId + "property2" + password);
		try {
			SignUpDTO updto = this.signUpService.findByUserAndPassword(userId, password);
			log.info("Login count" + updto);

			if (updto.getLoginCount() >= 3) {
				model.addAttribute("msg", "Account locked Reset password");
				return "SignIn";
			}
			if (updto != null) {

				if (updto.isResetPassword() == true) {
					log.info("User ID" + updto.getUserId());
					model.addAttribute("userID", updto.getUserId());
					return "updatePassword";
				}
				log.info("User ID and password is matched");
				HttpSession httpSession = request.getSession(true);
				httpSession.setAttribute("userID", updto.getUserId());
				model.addAttribute("userID", updto.getUserId());
				model.addAttribute("email", updto.getEmail());
				httpSession.setAttribute("dtoPic", updto.getFileName());
				model.addAttribute("dto", updto);
				httpSession.setAttribute("dto", updto);
				return "Success";
			}

		} catch (Exception nre) {
			// TODO: handle exception
			System.err.println(nre.getMessage());
		}

		model.addAttribute("match", "UserID OR Password is not matching");
		return "SignIn";

	}

	@PostMapping("/reset")
	public String reSetPassword(String email, Model model) {
		try {
			SignUpDTO udto = this.signUpService.reSetPassword(email);
			if (udto.isResetPassword() == true) {
				model.addAttribute("msg", "Password reset sucessful plz login");
				return "resetpassword";
			}
		} catch (Exception e) {
			// TODO: handle exception
			log.info(e.getMessage());
		}
		return "resetpassword";

	}

	@PostMapping("/passwordUpdate")
	public String upDatePassword(String userId, String password, String confirmPassword) {
		this.signUpService.updatePassword(userId, password, confirmPassword);
		return "PasswordSuccess";
	}

	@PostMapping("/addTech")
	public String addTech(String userId, TechEntity techEntity, Model model) {
		this.signUpService.addTech(userId, techEntity);
		model.addAttribute("openSoure", openSoure);
		model.addAttribute("techEnum", techEnum);
		// model.addAttribute("license", "license");
		model.addAttribute("techie", "User Technologies added Successfully");
		model.addAttribute("tech", techEntity);
		return "add";
	}

	@GetMapping("/addTech")
	public String addTech(Model model, TechEntity techEntity) {

		model.addAttribute("openSoure", openSoure);
		model.addAttribute("techEnum", techEnum);
		// model.addAttribute("license", "license");

		return "add";
	}

	@GetMapping("/show")
	public String onTechView(String userId, Model model) {
		List<TechEntity> techList = this.signUpService.tech(userId);
		model.addAttribute("techList", techList);

		return "ShowTechnology";
	}

	@PostMapping("/UpdateImg")
	public String onUploadImg(@RequestParam("chitra") MultipartFile multipartFile, String userId, String email,
			Long mobile, Model model) throws IOException {
		log.info("multipartFile" + multipartFile);
		log.info(multipartFile.getOriginalFilename());
		log.info(multipartFile.getContentType());
		log.info("Size of file" + multipartFile.getSize());
		log.info("Size of bite" + multipartFile.getBytes());
		if (multipartFile.isEmpty()) {
			log.info("file not uploaded");
			model.addAttribute("error", "please select file");
			this.signUpService.updateData(userId, email, mobile);
			return "UpdateProfile";
		}
		model.addAttribute("sucess", "image uploaded sucessfully");

		byte[] bytes = multipartFile.getBytes();
		Path path = Paths.get("D:\\common\\" + userId + System.currentTimeMillis() + ".jpg");
		Files.write(path, bytes);
		String imageName = path.getFileName().toString();
		log.info("Image name--" + imageName);
		log.info("Image name in tostring--" + path.toString());
		log.info("Image file name--" + path.getFileName());
		this.signUpService.updateImg(userId, mobile, email, imageName);
		model.addAttribute("error", "image has been uploaded successfully");
		return "UpdateProfile";
	}

	@GetMapping("/download")
	public void onDownload(HttpServletResponse response, @RequestParam String fileName, SignUpDTO user)
			throws IOException {
		Path path = Paths.get("D:\\highway\\" + user.getFileName());
		path.toFile();
		response.setContentType("image/jpeg");
		File file = new File("D:\\common\\" + fileName);
		InputStream in = new BufferedInputStream(new FileInputStream(file));
		ServletOutputStream out = response.getOutputStream();
		IOUtils.copy(in, out);
		response.flushBuffer();

	}

	@GetMapping("/findByProperties")
	public String findByProperties(String techName, String userID, String lang, double version, String owner,
			String supportFrom, String supportTo, String license, String openSoure, TechEnum osType, Model model) {
		log.info("running onsearchByName controller");
		List<TechEntity> list = this.signUpService.findByProperties(techName, userID, lang, version, owner, supportFrom,
				supportTo, license, openSoure, osType);
		model.addAttribute("list", list);

		return "SearchTwoProperties";
	}

}
//@PostMapping("/UpdateProfile")
//public String onUpdate(SignUpDTO dto, Model model) {
//	log.info("running onUpdate " + dto);
//
//	Set<ConstraintViolation<SignUpDTO>> constraintViolations = this.signUpService.u
//	if (constraintViolations.size() > 0) {
//		model.addAttribute("error", constraintViolations);
//	} else {
//		model.addAttribute("message", "update success");
//	}
//	return "Signup";
//}
//@PostMapping("/UpdateData")
//public String onUploadData(String userId, String email, Long mobile, Model model) {
//	log.info("running on onUploadData post method");
//
//	log.info("running findByUserAndPassword controller" + "property1" + userId + "property2" + email);
//
//	try {
//		this.signUpService.updateData(email, userId, mobile);
//		SignUpDTO udto = this.signUpService.updateData(userId,email,mobile);
//		if (udto != null) {
//			model.addAttribute("msg", "Password reset sucessful plz login");
//			return "Success";
//		}
//	} catch (Exception e) {
//		// TODO: handle exception
//		log.info(e.getMessage());
//	}
//	return "UpdateProfile";
//
//}

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
