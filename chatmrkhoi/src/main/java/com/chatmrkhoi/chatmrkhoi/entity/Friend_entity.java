package com.chatmrkhoi.chatmrkhoi.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Friend_entity extends base {
	@ManyToOne
    @JoinColumn(name = "user_id")
	Users_entity  usersentities;
	
	@OneToMany(mappedBy = "friendmess")
	Set<Mess_entity> mess_entity;
	
	@Column(name = "friend_id")
	private Long idfriend;
	@Column
	private String status;
	
	@Column
	private String coderoom;
	
	@Column
	private Long time;
}
