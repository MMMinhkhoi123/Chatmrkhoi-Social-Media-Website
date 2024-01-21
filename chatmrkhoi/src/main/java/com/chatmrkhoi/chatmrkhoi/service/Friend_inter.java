package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.getfriend_reponse;

public interface Friend_inter {
	public void add_friend(Long idfriend);
	public ResponseEntity<array_connect_reponse> action_friend(Long idfriend, String status);
	public void unfriend(Long idfriend);
	
}
