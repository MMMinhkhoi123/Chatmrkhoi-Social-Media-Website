package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.exception.cutomer.NotfoundException;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.entity.CodeEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.ICodeRepo;
import com.chatmrkhoi.chatmrkhoi.service.ICode;

@Service
public class CodeSer implements ICode {

	@Autowired IUserRepo USER_REPO;
	@Autowired ICodeRepo CODE_REPO;
	private final PasswordEncoder encoder = new BCryptPasswordEncoder(); 
	
	@Override
	public String getCodeVerify(String gmail) {
		if(USER_REPO.findByEmail(gmail).isPresent()) {
		    Random rand = new Random();
		    int random = rand.nextInt(10, 1000);
			Date date = new Date();
		    date.setMinutes(date.getMinutes() + 5);
			CodeEn data = CodeEn.builder().code(String.valueOf(random)).time(date.getTime()).gmail(gmail).build();
			Optional<CodeEn> nim = CODE_REPO.findByEmail(gmail);
			nim.ifPresent((e) -> {
				CODE_REPO.deleteById(nim.get().getId());
			});
			CODE_REPO.save(data);
			return  String.valueOf(random);
		} else throw new NotfoundException("Account not exist");
	}

	@Override
	public ResponseEntity<String> getStatusVerifyCode(String gmail, String code) {
		Optional<CodeEn> data = CODE_REPO.findByEmail(gmail);
		data.ifPresent((e) -> {
			if(!e.getCode().equalsIgnoreCase(code)) {
				 throw new NotfoundException("The code Invalid");
			}
			if(e.getTime() < System.currentTimeMillis()){
				 throw new NotfoundException("The code is no longer available");
			}
			CODE_REPO.deleteById(data.get().getId());
		});
		return ResponseEntity.ok("Code correct");
	}
	
	@Override
	public void updatePassword(String gmail, String pass) {
		UserEn user = USER_REPO.findByEmail(gmail).orElseThrow();
		user.setPasswords(encoder.encode(pass));
		USER_REPO.save(user);
	}
	

}
