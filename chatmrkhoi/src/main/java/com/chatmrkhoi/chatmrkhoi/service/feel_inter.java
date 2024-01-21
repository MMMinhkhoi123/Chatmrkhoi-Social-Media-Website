package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.addfeel_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.request.updatefeel_request;

public interface feel_inter {
	
	public ResponseEntity<addfeel_reponse> add_feel(updatefeel_request data);
	
	public ResponseEntity<mess_reponse> delete_feel(Long idmess, String type);
}
