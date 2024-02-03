package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pin_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pindata_reponse;
import com.chatmrkhoi.chatmrkhoi.request.addpin_request;

public interface pin_inter {
	public ResponseEntity<pindata_reponse> AddPingMessenger(addpin_request data);
	public ResponseEntity<List<pin_reponse>> GetAllPingMessenger();
	public ResponseEntity<pindata_reponse>  RemovePingMessenger(Long pin);
}
