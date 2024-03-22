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
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class FeelingEn extends Base implements IActivityMessenger {

	@Column
	private String mess;
	@Column
	private String type;
	@Column
	private  Long time;

	@ManyToOne
	@JoinColumn(name = "iduser")
	private UserEn iduser;

	@ManyToOne
	@JoinColumn(name = "mess_id")
	private MessageEn messentity;

	@Override
	public MessageEn createMessage() {
		return this.messentity.getGroupmess() == null
				? MessageEn.builder().feel(this.mess).friendmess(this.messentity.getFriendmess()).time(System.currentTimeMillis())
				.usersentity(this.iduser).build()
				: MessageEn.builder().feel(this.mess).groupmess(this.messentity.getGroupmess()).time(System.currentTimeMillis())
				.usersentity(this.iduser).build();
	}
}
