package com.chatmrkhoi.chatmrkhoi.entity;

import java.util.Date;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class base {

	@Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	@CreatedDate
	private Date date_create;
	@Column
	@LastModifiedDate
	private Date date_modify;
	
}

