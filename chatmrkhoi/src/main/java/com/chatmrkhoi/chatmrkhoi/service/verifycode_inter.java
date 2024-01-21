package com.chatmrkhoi.chatmrkhoi.service;

import org.springframework.http.ResponseEntity;

public interface verifycode_inter {

	public void createverify(String gmail);
	
	public void chanepass(String gmail, String pass);
	
	public ResponseEntity<String> check_code(String gmail, String code);
}
