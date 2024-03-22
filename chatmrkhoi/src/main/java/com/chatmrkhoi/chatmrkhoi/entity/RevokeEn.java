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
public class RevokeEn extends Base {

	@Column
	private String type;

	@Column
	private  Long time;
	
	@ManyToOne
	@JoinColumn(name = "mess_id")
	private MessageEn messentity;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEn userentity;

}
