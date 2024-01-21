package com.chatmrkhoi.chatmrkhoi.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class array_connect_reponse {
	private Long idfriend;
	private Long idgroup;
	private String coderoom;
	private Long time;
}
