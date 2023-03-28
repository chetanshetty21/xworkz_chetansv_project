package com.xworkz.commonmodule.entity;

import java.io.Serializable;

import java.time.LocalDateTime;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@MappedSuperclass
@Data
public class AbstractAuditEntity implements Serializable {
	private String createdBy = "chetansv";
	private LocalDateTime createdDate = LocalDateTime.now();
	private String updatedBy = "chetansv";
	private LocalDateTime updatedDate = LocalDateTime.now();
}
