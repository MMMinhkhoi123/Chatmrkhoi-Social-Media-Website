package com.chatmrkhoi.chatmrkhoi.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group_entity extends base {
	@Column
	private String name;
	@Column
	private int size;
	@Column
	private String avata;
	
	@Column 
	private String coderoom;
	
	@Column
	private Long time;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	Users_entity userEntity;
	
	@ManyToMany
	@JoinTable(
	  name = "Group_user", 
	  joinColumns = @JoinColumn(name = "Group_id"), 
	  inverseJoinColumns = @JoinColumn(name = "User_id"))
	Set<Users_entity> usersentitiesx;
	
	@OneToMany(mappedBy = "groupmess")
	Set<Mess_entity> mess_entity;
}
