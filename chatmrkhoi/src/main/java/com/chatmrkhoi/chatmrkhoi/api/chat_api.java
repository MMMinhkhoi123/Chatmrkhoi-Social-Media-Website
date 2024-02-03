package com.chatmrkhoi.chatmrkhoi.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pin_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.service.impl.User_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.pin_service;
@RestController
@RequestMapping("/chat")
public class chat_api {

	@Autowired
	pin_service pin_service;
	
	@Autowired
	User_service user_service;
	
	
	@GetMapping("/array-connect")
	public ResponseEntity<List<array_connect_reponse>> array_connect() {
		return user_service.array_connect();
	}
	
	@GetMapping("/pin")
	public ResponseEntity<List<pin_reponse>> getpin() {
	  return pin_service.GetAllPingMessenger();
	}
	
	
}
