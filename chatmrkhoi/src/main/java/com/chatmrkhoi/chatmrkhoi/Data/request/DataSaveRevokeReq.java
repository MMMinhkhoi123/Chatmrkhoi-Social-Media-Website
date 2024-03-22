package com.chatmrkhoi.chatmrkhoi.Data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DataSaveRevokeReq {
	private String type;
	private Long idmess;
	private Long iduser;
}
