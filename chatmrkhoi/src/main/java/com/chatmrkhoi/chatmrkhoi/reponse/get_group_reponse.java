package com.chatmrkhoi.chatmrkhoi.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class get_group_reponse {
	private Long id;
	private String name;
	private String img;
	private long master;
	private  int count;
	private String coderoom;
}
