package com.chatmrkhoi.chatmrkhoi.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class Users_entity extends base {
	@Column(unique = true)
	private String gmails;
	
	@Column
	private String passwords;
	
	@Column
	private boolean verify;

	@ManyToMany(mappedBy  = "usersentitiesx")
	Set<Group_entity> group_entities;
	 
	@OneToMany(mappedBy  = "usersentities" )
	Set<Friend_entity> friend_entities;
	 
	@OneToMany(mappedBy = "usersentity")
	Set<Mess_entity> mess_entity;
	 
	@OneToMany(mappedBy = "userEntity")
	Set<Group_entity> grouarraymaster;
	
	 @OneToMany(mappedBy = "usersentity")
	 Set<watch_entity> watch_entities;
	
	@OneToMany(mappedBy = "userentity")
	 Set<revoke_entity> revokeatity;
	 
	@OneToMany(mappedBy = "userEntity")
	Set<pin_entity> pinenEntities;
}