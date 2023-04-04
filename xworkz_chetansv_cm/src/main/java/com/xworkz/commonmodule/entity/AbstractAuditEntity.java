package com.xworkz.commonmodule.entity;

import java.io.Serializable;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public abstract class AbstractAuditEntity implements Serializable {
	private String createdBy;
	private LocalDateTime createdDate=LocalDateTime.now();
	private String updatedBy;
	private LocalDateTime updatedDate;
}
