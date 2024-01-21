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
public class finduser_reponse {
	private String gmail;
	private String fullname;
	private String desc;
	private String images;
	private boolean friendly;
}
