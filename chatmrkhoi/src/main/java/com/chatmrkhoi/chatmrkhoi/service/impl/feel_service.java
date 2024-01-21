package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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


	@Autowired
	mess_repo mess_repo;
	
	@Autowired
	feel_repo feel_repo;
	
	@Autowired
	User_repo user_repo;
	
	private mess_service mess_service = null;
	
	public feel_service(mess_service mess) {
		this.mess_service = mess;
	}
	
	// RETURN USER AUTHENCATED
	public Users_entity user_authe() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users_entity user = user_repo.findbygmail(userDetails.getUsername()).get();
		return user;
	}
	
	@Override
	public ResponseEntity<addfeel_reponse> add_feel(updatefeel_request data) {
		addfeel_reponse datareponse = new addfeel_reponse();
		Long id = user_authe().getId();
		// find user send mess
		feel_entity feel = feel_entity.builder()
				.iduser(id)
				.mess(data.getFeel())
				.messentity(mess_repo.findById(data.getIdmess()).get())
				.type(data.getType())
				.build();
		feel_repo.save(feel);
		Mess_entity mess = mess_repo.findById(data.getIdmess()).get();
		
		Mess_entity messnewx = null;
		if(mess.getGroupmess() == null) {
			Mess_entity messnew = Mess_entity.builder().feel(data.getFeel()).friendmess(mess.getFriendmess()).time(System.currentTimeMillis()).usersentity(user_authe()).build();	
			messnewx = mess_repo.save(messnew);
		} else {
			Mess_entity messnew = Mess_entity.builder().feel(data.getFeel()).groupmess(mess.getGroupmess()).time(System.currentTimeMillis()).usersentity(user_authe()).build();	
			messnewx = mess_repo.save(messnew);
		}
		
		mess_reponse messreponse = mess_service.convert_reponse(mess);
		datareponse.setMessupdate(messreponse);
		datareponse.setMessnew(mess_service.convert_reponse(messnewx));
		return ResponseEntity.ok(datareponse);
	}

	
	public List<updatefeel_request> convertfell(Set<feel_entity> feel) {
		List<updatefeel_request> x = new ArrayList<updatefeel_request>();
		feel.forEach((e) -> {
             updatefeel_request datac =	updatefeel_request.builder()
            		 .feel(e.getMess())
            		 .type(e.getType())
            		 .idmess(e.getMessentity()
            		 .getId())
            		 .iduser(e.getIduser())
            		 .build();
             x.add(datac);
		});
	 return x;
	}

	@Override
	public ResponseEntity<mess_reponse> delete_feel(Long idmess, String type) {
		Long id = user_authe().getId();
		feel_repo.deletefeel(idmess,id, type);
		Mess_entity mess = mess_repo.findById(idmess).get();
		mess_reponse messreponse = mess_service.convert_reponse(mess);
		return ResponseEntity.ok(messreponse);
	}
}
