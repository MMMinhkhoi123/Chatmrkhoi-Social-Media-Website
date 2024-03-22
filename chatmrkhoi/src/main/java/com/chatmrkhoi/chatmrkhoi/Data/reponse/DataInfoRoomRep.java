package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataInfoRoomRep {
	private Long idfriend;
	private Long idgroup;
	private String coderoom;
	private Long time;
}
