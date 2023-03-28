package com.xworkz.commonmodule.entity;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "signup_table")
public class SignUpEntity extends AbstractAuditEntity{
	@Id
	@Column(name = "signup_id")
	private int id;
	@Column(name = "userId")
	private String userId;
	@Column(name = "email")
	private String email;
	@Column(name = "mobile")
	private long mobile;
	@Column(name = "password")
	private String password;
	@Column(name = "signup_accept_aggrement")
	private boolean acceptAgreement;
}
