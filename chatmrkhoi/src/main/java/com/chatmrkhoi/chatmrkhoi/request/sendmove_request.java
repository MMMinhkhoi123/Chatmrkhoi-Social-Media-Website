package com.chatmrkhoi.chatmrkhoi.request;

import lombok.Data;

@Data
public class sendmove_request {
	private String type;
	private Long idmess;
	private Long idfriend;
	private Long idgroup;
}
