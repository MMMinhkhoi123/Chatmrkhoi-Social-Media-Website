package com.chatmrkhoi.chatmrkhoi.entity;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileEn extends Base {
	@Column
	private String namefile;
	@Column
	private Long size;
	@Column
	private String typefile;
	
	@Column
	private boolean status;
	
	@ManyToOne
	 @JoinColumn(name = "mess_id")
	private MessageEn messid;
	
}
