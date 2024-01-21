package com.chatmrkhoi.chatmrkhoi.request;

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
public class watch_request {
	private long idperson;
	private long idmess;
}
