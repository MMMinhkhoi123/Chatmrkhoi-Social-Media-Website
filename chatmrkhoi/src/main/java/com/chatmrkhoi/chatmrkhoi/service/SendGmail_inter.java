package com.chatmrkhoi.chatmrkhoi.service;

public interface SendGmail_inter {

	public void Send_verify_gmail(String jwtoken, String to);
	
	public void Send_verify_gmail_code(String jwtoken, String to);
}
