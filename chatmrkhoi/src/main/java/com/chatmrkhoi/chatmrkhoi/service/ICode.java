package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

public interface ICode {

	String getCodeVerify(String gmail);
	
	void updatePassword(String gmail, String pass);
	
	ResponseEntity<String> getStatusVerifyCode(String gmail, String code);
}
