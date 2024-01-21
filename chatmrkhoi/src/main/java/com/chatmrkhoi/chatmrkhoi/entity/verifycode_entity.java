package com.chatmrkhoi.chatmrkhoi.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class verifycode_entity extends base {
	private String gmail;
	private String code;
	private Long time;
}
