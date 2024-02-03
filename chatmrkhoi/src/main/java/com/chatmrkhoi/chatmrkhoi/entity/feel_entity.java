package com.chatmrkhoi.chatmrkhoi.entity;

import com.chatmrkhoi.chatmrkhoi.design.Factory.IActionMessenger;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class feel_entity extends base implements IActionMessenger {

	@Column
	private String mess;
	@Column
	private String type;
	@Column
	private  Long time;

	@ManyToOne
	@JoinColumn(name = "iduser")
	private Users_entity iduser;

	@ManyToOne
	@JoinColumn(name = "mess_id")
	private Mess_entity messentity;

	@Override
	public Mess_entity createMess() {
		Mess_entity MessEntity = this.messentity.getGroupmess() == null
				? Mess_entity.builder().feel(this.mess).friendmess(this.messentity.getFriendmess()).time(System.currentTimeMillis()).usersentity(this.iduser).build()
				: Mess_entity.builder().feel(this.mess).groupmess(this.messentity.getGroupmess()).time(System.currentTimeMillis()).usersentity(this.iduser).build();
		return MessEntity;
	}
}
