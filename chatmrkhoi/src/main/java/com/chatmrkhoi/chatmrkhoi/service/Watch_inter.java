package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.request.watch_request;

public interface Watch_inter {
	public ResponseEntity<mess_reponse>  watchmess(watch_request data);
}
