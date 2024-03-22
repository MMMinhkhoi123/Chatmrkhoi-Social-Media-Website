package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataInfoUserOtherRep {
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
	private List<Long> sugg;
}
