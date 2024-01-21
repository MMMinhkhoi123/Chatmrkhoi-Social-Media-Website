package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.reponse.action_user;
import com.chatmrkhoi.chatmrkhoi.reponsitory.action_repo;
import com.chatmrkhoi.chatmrkhoi.service.Action_inter;

@Service
public class action_service implements Action_inter {

	@Autowired
	action_repo action_repo;
	
	
	@Override
	public ResponseEntity< List<action_user>> getalls() {
		// TODO Auto-generated method stub
		List<action_user> list = new ArrayList<action_user>();
		action_repo.findAll().forEach((e) -> {
			action_user x = new action_user();
			x.setId(e.getId_user());
			x.setStatus(e.getStatus());
			x.setTimetamp(e.getTimetamp());
			list.add(x);
		});
		return ResponseEntity.ok(list);
	}

}
