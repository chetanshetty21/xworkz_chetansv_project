package com.xworkz.commonmodule.dto;

import javax.validation.constraints.AssertTrue;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDTO {

	@Size(min = 3, max = 20, message = "userId should contain char min 3 and less than 20")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "userId should be stored in char only")
	private String userId;
	@Email
	private String email;
	@Min(message = "mobile number should be more than 10", value = 10)
//	@Pattern(regexp = "^[0-9]", message = "mobile number should be stored in numbers only")
	private long mobile;
	@Size(min = 8, max = 20, message = "password should contain char min 8 and less than 20")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "password should be stored in char only")
	private String password;
	@Size(min = 8, max = 20, message = "confirmPassword should contain char min 8 and less than 20")
	@Pattern(regexp = "^[a-zA-Z]+$", message = "confirmPassword should be stored in char only")
	private String confirmPassword;
	@AssertTrue
	private boolean acceptAgreement;
//	private int id;
}
