package com.chatmrkhoi.chatmrkhoi.request;

import com.chatmrkhoi.chatmrkhoi.reponse.token_repoonse;

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
public class finduser_request {
 
	private Long myid;
	private String gmail;
	
}
