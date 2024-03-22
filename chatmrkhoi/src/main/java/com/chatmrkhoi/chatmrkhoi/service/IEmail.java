package com.chatmrkhoi.chatmrkhoi.service;

public interface IEmail {

	void SendEmailVerify(String email);
	
	void SendEmailCodeVerify(String token, String to);

}
