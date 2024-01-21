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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class revoke_entity  extends base{
	
	@Column
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "mess_id")
	private Mess_entity messentity;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users_entity userentity;
	
}
