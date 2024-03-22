package com.chatmrkhoi.chatmrkhoi.Data.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataSaveWatchReq {
	private long idperson;
	private long idmess;
}
