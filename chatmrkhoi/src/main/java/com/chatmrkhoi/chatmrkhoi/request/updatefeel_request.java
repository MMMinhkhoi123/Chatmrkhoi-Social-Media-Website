package com.chatmrkhoi.chatmrkhoi.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class updatefeel_request {
	private String type;
	private Long idmess;
	private String feel;
	private Long iduser;
}
