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
public class token_repoonse {
	private Long id;
	private String gmail;
	private String fullname;
	private String desc;
	private String images;
	private Long briday;
	private String gender;
	private String status;
	private String type_img;
}
