package com.chatmrkhoi.chatmrkhoi.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class pin_reponse {
	private Long id;
	private Long iduser;
	private Long idmess;
	private String type;
	private Long time;
	private String coderoom;
}
