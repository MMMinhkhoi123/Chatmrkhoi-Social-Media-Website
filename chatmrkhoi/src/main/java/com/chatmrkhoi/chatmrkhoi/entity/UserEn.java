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
public class UserEn extends Base {
	@Column(unique = true)
	private String gmails;
	
	@Column
	private String passwords;
	
	@Column
	private boolean verify;

	@ManyToMany(mappedBy  = "usersentitiesx")
	Set<GroupEn> group_entities;
	 
	@OneToMany(mappedBy  = "usersentities" )
	Set<FriendEn> friend_entities;
	 
	@OneToMany(mappedBy = "usersentity")
	Set<MessageEn> mess_entity;
	 
	@OneToMany(mappedBy = "userEntity")
	Set<GroupEn> grouarraymaster;
	
	 @OneToMany(mappedBy = "usersentity")
	 Set<WatchEn> watch_entities;
	
	@OneToMany(mappedBy = "userentity")
	 Set<RevokeEn> revokeatity;

	@OneToMany(mappedBy = "iduser")
	Set<FeelingEn> feelEntities;
	 
	@OneToMany(mappedBy = "userEntity")
	Set<PinEn> pinenEntities;
}