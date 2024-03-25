package com.chatmrkhoi.chatmrkhoi.entity;

import jakarta.persistence.Entity;
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
public class InfoEn extends Base {
	private Long idusers;
	private String descs;
	private String fullnames;
	private String avatars;
	private Long birday;
	private String gender;
	private String type_avatas;
	private boolean darkMode;
	private boolean notify;
}
