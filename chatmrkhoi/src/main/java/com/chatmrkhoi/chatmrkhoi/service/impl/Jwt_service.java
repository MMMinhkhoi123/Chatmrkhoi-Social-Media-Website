package com.chatmrkhoi.chatmrkhoi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Jwt_entity;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Jwt_repo;
import com.chatmrkhoi.chatmrkhoi.service.Jwt_inter;

@Service
public class Jwt_service implements Jwt_inter {

	@Autowired
	Jwt_repo jwt_repo;
	@Override
	public void save(Jwt_entity data) {
		// TODO Auto-generated method stub
		jwt_repo.save(data);
	}
}
