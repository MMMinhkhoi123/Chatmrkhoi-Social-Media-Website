package com.chatmrkhoi.chatmrkhoi.Data.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DataKickOrAddAnNumberReq {
	private Long id;
	private Long idfriend;
	private String roomuser;
	private String codenew;
}
