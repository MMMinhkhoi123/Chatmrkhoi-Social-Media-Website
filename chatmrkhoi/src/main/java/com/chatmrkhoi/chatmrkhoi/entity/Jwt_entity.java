package com.chatmrkhoi.chatmrkhoi.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Jwt_entity extends base {
	private String jwts;
	private Long time;
	private Long id_user;
}
