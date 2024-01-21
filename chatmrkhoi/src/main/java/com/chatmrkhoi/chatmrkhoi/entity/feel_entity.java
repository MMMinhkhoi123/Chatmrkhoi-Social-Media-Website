package com.chatmrkhoi.chatmrkhoi.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class feel_entity extends base {
	@Column
	private String mess;
	@Column
	private Long iduser;
	@Column
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "mess_id")
	private Mess_entity messentity;
}
