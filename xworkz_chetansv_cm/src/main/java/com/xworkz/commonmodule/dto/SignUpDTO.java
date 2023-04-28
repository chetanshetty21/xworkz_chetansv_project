package com.xworkz.commonmodule.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.AssertTrue;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.xworkz.commonmodule.entity.TechEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
	private int id;
	private int loginCount;
	private boolean resetPassword;
	private LocalTime passwordChangedTime;
	private String fileName;
	private List<TechEntity> tech;

}
