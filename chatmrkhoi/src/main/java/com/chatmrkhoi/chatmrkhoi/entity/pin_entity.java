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
public class pin_entity extends base {
	
	@Column
	private Long time;
	
	@Column
	private String type;
	
	 @ManyToOne
	    @JoinColumn(name = "mess_id")
	    Mess_entity messentity;
	 
	 @ManyToOne
	    @JoinColumn(name = "user_id")
	    Users_entity userEntity;
}
