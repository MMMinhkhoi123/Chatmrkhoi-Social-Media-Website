package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.request.unmess_request;

public interface revoke_inter {
	
	public ResponseEntity<mess_reponse> addunmess(unmess_request data);
}
