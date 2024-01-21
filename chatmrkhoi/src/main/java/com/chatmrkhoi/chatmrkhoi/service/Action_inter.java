package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.action_user;

public interface Action_inter {
	public ResponseEntity< List<action_user>> getalls();
}
