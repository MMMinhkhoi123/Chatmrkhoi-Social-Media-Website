package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataInfoMessPinRep {
	private Long id;
	private Long iduser;
	private Long idmess;
	private String type;
	private Long time;
	private String coderoom;
}
