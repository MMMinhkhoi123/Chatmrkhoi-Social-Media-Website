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
public class Mess_entity extends base {
	@Column
	private boolean status;
	@Column
	private String content;
	@Column
	private Long time;
	@Column
	private String type;
	@Column
	private Long reply;
	@Column
	private String feel;
	@Column
	private String typereply;
	@Column
	private String pin;
	@Column
	private boolean start_date;
	@Column
	private String addgroup;
	
	
	 @ManyToOne
	    @JoinColumn(name = "group_id")
	    Group_entity groupmess;
	 @ManyToOne
	    @JoinColumn(name = "friend_id")
	    Friend_entity friendmess;	 
	 @ManyToOne
	    @JoinColumn(name = "user_id")
	    Users_entity usersentity;
	 
	 @OneToMany(mappedBy = "messid")
	 Set<file_entity> file_entities;
	 
	 @OneToMany(mappedBy = "messentity")
	 Set<feel_entity> feelentitty;
	 
	 @OneToMany(mappedBy = "messentity")
	 Set<watch_entity> watch_entities;
	 
	 @OneToMany(mappedBy = "messentity")
	 Set<revoke_entity> revokeatity;
	  
	 @OneToMany(mappedBy = "messentity")
	 Set<pin_entity> pinenEntities;
	 
}
