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
public class WatchEn extends Base {
	
	@Column
	private Long timetamp;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEn usersentity;
	
	@ManyToOne
	@JoinColumn(name = "mess_id")
	private MessageEn messentity;
}
