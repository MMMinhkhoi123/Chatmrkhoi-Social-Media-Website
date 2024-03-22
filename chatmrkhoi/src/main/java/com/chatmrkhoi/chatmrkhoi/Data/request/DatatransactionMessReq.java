package com.chatmrkhoi.chatmrkhoi.Data.request;

import lombok.Data;

@Data
public class DatatransactionMessReq {
	private String type;
	private Long idmess;
	private Long idfriend;
	private Long idgroup;
}
