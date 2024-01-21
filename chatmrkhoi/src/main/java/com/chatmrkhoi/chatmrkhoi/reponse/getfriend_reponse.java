package com.chatmrkhoi.chatmrkhoi.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class getfriend_reponse {
	private Long id;
	private String gmail;
	private String fullname;
	private String desc;
	private String images;
	private String status;
	private String coderoom;
	private String type_img;
	private int countfriend;
	private Long briday;
	private String action;
	private String gender;
	private Long timeaction;
}
