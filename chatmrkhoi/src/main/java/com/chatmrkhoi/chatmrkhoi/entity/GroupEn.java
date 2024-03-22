package com.chatmrkhoi.chatmrkhoi.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.chatmrkhoi.chatmrkhoi.design.factorymethod.IActivityMessenger;
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
public class GroupEn extends Base implements IActivityMessenger {

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
	UserEn userEntity;
	
	@ManyToMany
	@JoinTable(
	  name = "Group_user", 
	  joinColumns = @JoinColumn(name = "Group_id"), 
	  inverseJoinColumns = @JoinColumn(name = "User_id"))
	List<UserEn> usersentitiesx = new ArrayList<>();
	
	@OneToMany(mappedBy = "groupmess")
	Set<MessageEn> mess_entity;

	@Override
	public MessageEn createMessage() {
		return MessageEn.builder()
				.usersentity(userEntity)
				.content("")
				.time(System.currentTimeMillis())
				.groupmess(this)
				.addgroup( "add&" + usersentitiesx.get(usersentitiesx.size() -1).getId())
				.build();
	}
}
