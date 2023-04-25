package com.xworkz.commonmodule.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.xworkz.commonmodule.constants.TechEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import javax.persistence.NamedQuery;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tech_table")
//@NamedQuery(name = "findBytechName", query = "select entity from TechEntity entity where entity.techName=:techNameby or entity.lang=:langby or entity.version=:versionby or entity.owner=:ownerby or entity.supportFrom=:supportFromby or entity.supportTo=:supportToby or entity.license=:licenseToby or entity.openSoure=:openSoureby ")
@NamedQuery(name = "findBytechName", query = "select entity from TechEntity entity where entity.techName=:techNameby and entity.signUpEntity.id=:signupIdBy ")

public class TechEntity extends AbstractAuditEntity {

	@Id
	@Column(name = "tech_id")
	private int id;
	@Column(name = "techName")
	private String techName;
	@Column(name = "lang")
	private String lang;
	@Column(name = "version")
	private double version;
	@Column(name = "owner")
	private String owner;
	@Column(name = "supportFrom")
	private String supportFrom;
	@Column(name = "supportTo")
	private String supportTo;
	@Column(name = "license")
	private String license;
	@Column(name = "openSoure")
	private String openSoure;
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "osType")
	private TechEnum osType;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "signup_id", referencedColumnName = "signup_id")
	private SignUpEntity signUpEntity;
}
