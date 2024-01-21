package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Friend_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.pin_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pin_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pindata_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Friend_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.group_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.mess_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.pin_repo;
import com.chatmrkhoi.chatmrkhoi.request.addpin_request;
import com.chatmrkhoi.chatmrkhoi.service.pin_inter;

@Service
public class pin_service implements pin_inter {

	@Autowired
	User_repo user_repo;
	
	@Autowired
	mess_repo mess_repo;
	
	@Autowired
	mess_service mess_service;
	
	@Autowired
	pin_repo pin_repo;
	
	@Autowired
	User_service user_service;
	
	@Autowired
	group_repo group_repo;
	
	@Autowired
	Friend_repo friend_repo;
	
	// RETURN USER AUTHENCATED
	public Users_entity user_authe() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users_entity user = user_repo.findbygmail(userDetails.getUsername()).get();
		return user;
	}
	
	
	//PIN MESS 
	Friend_entity x = null;
	@Override
	public ResponseEntity<pindata_reponse> addping(addpin_request data) {
		pindata_reponse datareponsesx = new pindata_reponse();
		Mess_entity mess = mess_repo.findById(data.getIdmess()).get();
		pin_entity pinx = pin_repo.save(pin_entity.builder().messentity(mess).type(data.getType()).userEntity(user_authe())
				.time(System.currentTimeMillis()).build());
		String rom = null;
		if(pinx.getMessentity().getGroupmess() != null) {
			rom = pinx.getMessentity().getGroupmess().getCoderoom();
		} else {
			rom = pinx.getMessentity().getFriendmess().getCoderoom();
		}
		pin_reponse dataxxcv = 
				pin_reponse.builder()
				.id(pinx.getId())
				.idmess(pinx.getMessentity().getId())
				.iduser(pinx.getUserEntity().getId())
				.time(pinx.getTime())
				.coderoom(rom)
				.build();
		datareponsesx.setPin_chid(dataxxcv);
		Mess_entity datal = null;
		
		if(data.getIdgroup() != null) {
			datal =  mess_repo.save(Mess_entity.builder()
					.usersentity(user_authe())
					.time(System.currentTimeMillis())
					.pin("ping&"+pinx.getId().toString())
					.groupmess(group_repo.findById(data.getIdgroup())
							.get()).build());
		} else {
			Optional<Friend_entity> friend = friend_repo.findbydoubleid(data.getIdfriend(), user_authe().getId());
			friend.ifPresentOrElse((e) -> {
				x = e;
			}, () -> {
				x = friend_repo.findbydoubleid(user_authe().getId(),data.getIdfriend()).get();
			});
			
			datal =  mess_repo.save(Mess_entity.builder()
					.usersentity(user_authe())
					.time(System.currentTimeMillis())
					.pin("ping&"+ pinx.getId().toString())
					.friendmess(x)
					.build());
		}
		mess_reponse datareponse = mess_service.convert_reponse(datal);
		datareponsesx.setMess_reponse(datareponse);
		datareponsesx.setPin_reponses(getpisng());
		return ResponseEntity.ok(datareponsesx);
	}
	
	
	

	@Override
	public ResponseEntity<List<pin_reponse>> getping() {
		List<pin_reponse> ping = new ArrayList<pin_reponse>();

		pin_repo.findAll().forEach((e) -> {
			String rom = null;
			if(e.getMessentity().getGroupmess() != null) {
				rom = e.getMessentity().getGroupmess().getCoderoom();
			} else {
				rom = e.getMessentity().getFriendmess().getCoderoom();
			}
			pin_reponse data = pin_reponse.builder()
					.coderoom(rom)
					.id(e.getId())
					.type(e.getType())
					.idmess(e.getMessentity().getId())
					.iduser(e.getUserEntity().getId())
					.time(e.getTime())
					.build();
			ping.add(data);
		});
		
		return ResponseEntity.ok(ping);
	}

	public List<pin_reponse> getpisng() {
		List<pin_reponse> ping = new ArrayList<pin_reponse>();
		pin_repo.findAll().forEach((e) -> {
			String rom = null;
			if(e.getMessentity().getGroupmess() != null) {
				rom = e.getMessentity().getGroupmess().getCoderoom();
			} else {
				rom = e.getMessentity().getFriendmess().getCoderoom();
			}
			pin_reponse data = pin_reponse.builder()
					.coderoom(rom)
					.id(e.getId())
					.idmess(e.getMessentity().getId())
					.iduser(e.getUserEntity().getId())
					.time(e.getTime())
					.build();
			ping.add(data);
		});
		return ping;
	}

	@Override
	public ResponseEntity<pindata_reponse> deletepin(Long id) {
		pindata_reponse  datam = new pindata_reponse();
		pin_entity datadel  = pin_repo.findById(id).get();
	    pin_repo.deleteById(id); 
	    datam.setPin_reponses(getpisng());

	    
	    Users_entity user = user_repo.findById(datadel.getUserEntity().getId()).get();
		Mess_entity datal = null;
		
	    if( datadel.getMessentity().getGroupmess() != null) {
			datal =  mess_repo.save(Mess_entity.builder()
					.usersentity(user)
					.pin("unping&"+ id)
					.time(System.currentTimeMillis())
					.groupmess(datadel.getMessentity().getGroupmess()).build());
		} else {		
			datal =  mess_repo.save(Mess_entity.builder()
					.usersentity(user)
					.pin("unping&"+ id)
					.time(System.currentTimeMillis())
					.friendmess( datadel.getMessentity().getFriendmess())
					.build());
		}
		mess_reponse datareponse = mess_service.convert_reponse(datal);
		datam.setMess_reponse(datareponse);

		return ResponseEntity.ok(datam);
	}
	

}
