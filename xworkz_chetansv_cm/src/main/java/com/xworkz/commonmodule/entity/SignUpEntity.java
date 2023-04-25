package com.xworkz.commonmodule.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
//@EqualsAndHashCode(callSuper = false)
@Table(name = "signup_table")
@NamedQuery(name = "findByUser", query = "select count(*) from SignUpEntity entity where entity.userId=:useridby")
@NamedQuery(name = "findByEmail", query = "select count(*) from SignUpEntity entity where entity.email=:emailby")
@NamedQuery(name = "findByMobile", query = "select count(*) from SignUpEntity entity where entity.mobile=:mobileby")
@NamedQuery(name = "findByUserAndPassword", query = "select entity from SignUpEntity entity where entity.userId=:useridby ")

@NamedQuery(name = "emailid", query = "select entity from SignUpEntity entity where entity.email=:emailby")
//"update Employee emp set emp.surname=:surname where emp.name=:name\"")
@NamedQuery(name = "updateLoginCount", query = "update SignUpEntity entity set entity.loginCount=:countby where entity.userId=:useridby")
@NamedQuery(name = "updatePassword", query = "update SignUpEntity entity set entity.password=:uppasswordby , entity.resetPassword=:uresetpasrdby,entity.passwordChangedTime=:passwordChangedTimeby where entity.userId=:useridby")
public class SignUpEntity extends AbstractAuditEntity {

	// private static final long OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes
	@Id
	@Column(name = "signup_id")
	private int id;
	@Column(name = "userId")
	private String userId;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile")
	private Long mobile;
	@Column(name = "password")
	private String password;
	@Column(name = "signup_accept_aggrement")
	private boolean acceptAgreement;
	@Column(name = "logincount")
	private int loginCount;
	private boolean resetPassword;
	@Column(name = "passwordChangedTime")
	private LocalTime passwordChangedTime;
	@Column(name = "file_name")
	private String fileName;
	@OneToMany(mappedBy = "signUpEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<TechEntity> tech;

}
