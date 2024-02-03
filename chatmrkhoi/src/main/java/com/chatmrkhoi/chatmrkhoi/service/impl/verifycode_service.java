package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception.Notfound_exception;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.verifycode_entity;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.verifycode_repo;
import com.chatmrkhoi.chatmrkhoi.service.verifycode_inter;

@Service
public class verifycode_service implements verifycode_inter {

	@Autowired
	User_repo user_repo;
	
	@Autowired
	verifycode_repo verifycode_repo;
	
	@Autowired
	SendGmail_service sendGmail_service;
	
	private final PasswordEncoder encoder = new BCryptPasswordEncoder(); 
	
	@Override
	public void createverify(String gmail) {
		if(user_repo.findbygmail(gmail).isPresent() == true) {
		    Random rand = new Random();
		    int random = rand.nextInt(10, 1000);
		    Date date = new Date();
		    date.setMinutes(date.getMinutes() + 5);
			verifycode_entity data = verifycode_entity
					.builder()
						.code(String.valueOf(random))
						.time(date.getTime())
						.gmail(gmail)
					.build();
			Optional<verifycode_entity> nim = verifycode_repo.findbygmail(gmail);
			nim.ifPresent((e) -> {
				verifycode_repo.deleteById(nim.get().getId());
			});
			verifycode_repo.save(data);
			sendGmail_service.Send_verify_gmail_code(String.valueOf(random), gmail);
			
		} else throw new Notfound_exception("Acount not exist");
	}

	String name;
	@Override
	public ResponseEntity<String> check_code(String gmail, String code) {
		// TODO Auto-generated method stub
		name = "";
		Optional<verifycode_entity> data = verifycode_repo.findbygmail(gmail);
		data.ifPresent((e) -> {
			if(!e.getCode().equalsIgnoreCase(code)) {
				 throw new Notfound_exception("The code Invalid");
			}
			if(e.getTime() < System.currentTimeMillis()){
				 throw new Notfound_exception("The code is no longer available");
			}
			verifycode_repo.deleteById(data.get().getId());
		});
		return ResponseEntity.ok("minhkhoi");
	}
	
	@Override
	public void chanepass(String gmail, String pass) {
		Users_entity user = user_repo.findbygmail(gmail).get();
		user.setPasswords(encoder.encode(pass));
		user_repo.save(user);
	}
	

}
