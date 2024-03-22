package com.chatmrkhoi.chatmrkhoi.entity;

import com.chatmrkhoi.chatmrkhoi.design.factorymethod.IActivityMessenger;
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
public class PinEn extends Base implements IActivityMessenger {
	
	@Column
	private Long time;
	
	@Column
	private String type;

	@Column
	private String status;
	
	 @ManyToOne
		@JoinColumn(name = "mess_id")
     MessageEn messentity;

	 @ManyToOne
	    @JoinColumn(name = "user_id")
	 UserEn userEntity;

	@Override
	public MessageEn createMessage() {
		return   messentity.getGroupmess() == null
				? MessageEn.builder().pin(this.status).friendmess(this.messentity.getFriendmess()).time(System.currentTimeMillis()).usersentity(this.userEntity)
				.build()
				: MessageEn.builder().pin(this.status).groupmess(this.messentity.getGroupmess()).time(System.currentTimeMillis()).usersentity(this.userEntity)
				.build();
	}
}
