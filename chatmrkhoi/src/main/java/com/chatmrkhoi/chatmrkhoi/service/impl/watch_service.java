package com.chatmrkhoi.chatmrkhoi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.watch_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Watch_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.mess_repo;
import com.chatmrkhoi.chatmrkhoi.request.watch_request;
import com.chatmrkhoi.chatmrkhoi.service.Watch_inter;

@Service
public class watch_service implements Watch_inter {

	@Autowired
	Watch_repo watch_repo;
	
	@Autowired
	mess_repo mess_repo;
	
	@Autowired
	User_repo user_repo;
	
	@Autowired 
	mess_service mess_service;
	
	@Override
	public ResponseEntity<mess_reponse> watchmess(watch_request data) {
		watch_entity watch = watch_entity.builder()
				.messentity(mess_repo.findById(data.getIdmess()).get())
				.timetamp(System.currentTimeMillis())
				.usersentity(user_repo.findById(data.getIdperson()).get()).build();
		watch_repo.save(watch);
		mess_reponse mess = mess_service.convert_reponse(mess_repo.findById(data.getIdmess()).get());
		return ResponseEntity.ok(mess);
	}

}
