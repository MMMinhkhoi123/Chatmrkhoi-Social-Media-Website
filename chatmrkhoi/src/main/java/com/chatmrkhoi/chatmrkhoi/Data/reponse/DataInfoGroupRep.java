package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DataInfoGroupRep {
	private Long id;
	private String name;
	private String img;
	private long master;
	private  int count;
	private String coderoom;
}
