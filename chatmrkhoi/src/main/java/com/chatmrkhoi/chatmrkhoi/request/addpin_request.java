package com.chatmrkhoi.chatmrkhoi.request;

import lombok.Data;

@Data
public class addpin_request {
	private Long idmess;
	private Long idgroup;
	private Long idfriend;
	private Long idpin;
	private String type;
	
}
