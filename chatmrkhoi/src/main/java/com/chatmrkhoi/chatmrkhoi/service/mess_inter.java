package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.request.sendmess_request;
import com.chatmrkhoi.chatmrkhoi.request.sendmove_request;

public interface mess_inter {

	public  ResponseEntity< List<mess_reponse>> sendmess(sendmess_request sendmess_request);
	
	public  ResponseEntity<mess_reponse> movemess(sendmove_request data);
	
	public  ResponseEntity<mess_reponse> unmess(Long idmess, String type);
	
	public ResponseEntity<List<mess_reponse>> getmess();
	

	
}
