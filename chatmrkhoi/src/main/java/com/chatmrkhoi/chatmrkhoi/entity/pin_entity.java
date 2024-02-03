package com.chatmrkhoi.chatmrkhoi.entity;

import com.chatmrkhoi.chatmrkhoi.design.Factory.IActionMessenger;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pin_reponse;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class pin_entity extends base implements IActionMessenger {
	
	@Column
	private Long time;
	
	@Column
	private String type;

	@Column
	private String status;
	
	 @ManyToOne
		@JoinColumn(name = "mess_id")
		Mess_entity messentity;

	 @ManyToOne
	    @JoinColumn(name = "user_id")
	    Users_entity userEntity;

	@Override
	public Mess_entity createMess() {
		Mess_entity MessEntity = messentity.getGroupmess() == null
				? Mess_entity.builder().pin(this.status).friendmess(this.messentity.getFriendmess()).time(System.currentTimeMillis()).usersentity(this.userEntity)
				.build()
				: Mess_entity.builder().pin(this.status).groupmess(this.messentity.getGroupmess()).time(System.currentTimeMillis()).usersentity(this.userEntity)
				.build();
		return  MessEntity;
	}
}
