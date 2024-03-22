package com.chatmrkhoi.chatmrkhoi.Data.request;

import lombok.Data;

@Data
public class DataSavePinReq {
	private Long idmess;
	private Long idgroup;
	private Long idfriend;
	private Long idpin;
	private String type;
	
}
