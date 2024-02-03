package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.addfeel_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.request.updatefeel_request;

public interface feel_inter {
	
	public ResponseEntity<addfeel_reponse> AddFeelMessenger(updatefeel_request data);
	
	public ResponseEntity<mess_reponse> RemoveFeelMessenger(Long idmess, String type);
}
