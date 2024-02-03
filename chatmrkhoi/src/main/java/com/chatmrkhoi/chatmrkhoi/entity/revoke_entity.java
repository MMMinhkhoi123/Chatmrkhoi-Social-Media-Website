package com.chatmrkhoi.chatmrkhoi.entity;

import com.chatmrkhoi.chatmrkhoi.design.Factory.IActionMessenger;
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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class revoke_entity  extends base implements IActionMessenger {
	
	@Column
	private String type;

	@Column
	private  Long time;
	
	@ManyToOne
	@JoinColumn(name = "mess_id")
	private Mess_entity messentity;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users_entity userentity;

	@Override
	public Mess_entity createMess() {
		return null;
	}
}
