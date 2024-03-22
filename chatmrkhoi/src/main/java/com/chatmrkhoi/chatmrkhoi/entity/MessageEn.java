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
public class MessageEn extends Base {

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
	 GroupEn groupmess;

	 @ManyToOne
	    @JoinColumn(name = "friend_id")
	 FriendEn friendmess;

	 @ManyToOne
	    @JoinColumn(name = "user_id")
	 UserEn usersentity;
	 
	 @OneToMany(mappedBy = "messid")
	 Set<FileEn> file_entities;
	 
	 @OneToMany(mappedBy = "messentity")
	 Set<FeelingEn> feelentitty;
	 
	 @OneToMany(mappedBy = "messentity")
	 Set<WatchEn> watch_entities;
	 
	 @OneToMany(mappedBy = "messentity")
	 Set<RevokeEn> revokeatity;
	  
	 @OneToMany(mappedBy = "messentity")
	 Set<PinEn> pinenEntities;
	 
}
