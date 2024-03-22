package com.chatmrkhoi.chatmrkhoi.Data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DataFeelReq {
	private String type;
	private Long idmess;
	private String feel;
	private Long iduser;
}
