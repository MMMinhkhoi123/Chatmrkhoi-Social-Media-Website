package com.chatmrkhoi.chatmrkhoi.service.impl;

import com.chatmrkhoi.chatmrkhoi.design.Factory.AFactoryActionMess;
import com.chatmrkhoi.chatmrkhoi.design.Factory.FactoryFeelMessenger;
import com.chatmrkhoi.chatmrkhoi.design.Factory.FactoryRevokeMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.revoke_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.mess_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.revoke_repo;
import com.chatmrkhoi.chatmrkhoi.request.unmess_request;
import com.chatmrkhoi.chatmrkhoi.service.revoke_inter;


@Service
public class revoke_vervice implements revoke_inter {

	@Autowired
	revoke_repo revoleRepo;
	
	@Autowired
	mess_service mess_service;
	
	@Autowired
	mess_repo messrepo;
	
	@Autowired
	User_repo user_repo;

	
	// RETURN USER AUTHENCATED
	public Users_entity user_authe() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users_entity user = user_repo.findbygmail(userDetails.getUsername()).get();
		return user;
	}
	@Override
	public ResponseEntity<mess_reponse> addunmess(unmess_request datas) {
		AFactoryActionMess factoryrevoke = new FactoryRevokeMessenger();
		factoryrevoke.type = datas.getType();
		factoryrevoke.usersEntity = user_authe();
		factoryrevoke.messEntity = messrepo.findById(datas.getIdmess()).get();
		revoke_entity data = revoke_entity.builder()
				.messentity(messrepo.findById(datas.getIdmess()).get())
				.userentity(user_repo.findById(user_authe().getId()).get())
				.type(datas.getType())
				.build();
		revoleRepo.save(data);
		Mess_entity enetity = messrepo.findById(datas.getIdmess()).get();
		return ResponseEntity.ok(mess_service.convert_reponse(enetity));
	}


}
