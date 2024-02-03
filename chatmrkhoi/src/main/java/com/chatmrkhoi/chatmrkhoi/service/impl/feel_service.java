package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.chatmrkhoi.chatmrkhoi.design.Factory.FactoryFeelMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.feel_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.addfeel_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.feel_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.mess_repo;
import com.chatmrkhoi.chatmrkhoi.request.updatefeel_request;
import com.chatmrkhoi.chatmrkhoi.service.feel_inter;

@Service
public class feel_service implements feel_inter {
	@Autowired mess_repo mess_repo;
	@Autowired feel_repo feel_repo;
	@Autowired User_repo user_repo;
	@Autowired mess_service mess_service;

	// RETURN USER AUTHENCATED
	public Users_entity user_authe() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users_entity user = user_repo.findbygmail(userDetails.getUsername()).get();
		return user;
	}

	@Override
	public ResponseEntity<addfeel_reponse> AddFeelMessenger(updatefeel_request data) {
		FactoryFeelMessenger factoryfeel = new FactoryFeelMessenger();
		factoryfeel.type = data.getType();
		factoryfeel.usersEntity = user_authe();
		factoryfeel.messEntity = mess_repo.findById(data.getIdmess()).get();
		factoryfeel.key = data.getFeel();
		
		feel_repo.save((feel_entity) factoryfeel.CreateActionMess());
		Mess_entity messEntity = mess_repo.save(factoryfeel.MessNewRespon());

		return ResponseEntity.ok(addfeel_reponse
				.builder()
					.messnew(mess_service.convert_reponse(messEntity))
					.messupdate(mess_service.convert_reponse(mess_repo.findById(data.getIdmess()).get()))
				.build());
	}


	@Override
	public ResponseEntity<mess_reponse> RemoveFeelMessenger(Long idmess, String type) {
		Long id = user_authe().getId();
		feel_repo.deletefeel(idmess,id, type);
		Mess_entity mess = mess_repo.findById(idmess).get();
		return ResponseEntity.ok(mess_service.convert_reponse(mess));
	}
}
