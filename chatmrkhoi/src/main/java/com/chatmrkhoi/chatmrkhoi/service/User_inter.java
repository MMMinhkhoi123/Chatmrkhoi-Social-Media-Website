package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.SignIn_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.getfriend_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.token_repoonse;
import com.chatmrkhoi.chatmrkhoi.request.SignIn_request;
import com.chatmrkhoi.chatmrkhoi.request.SignUp_request;
import com.chatmrkhoi.chatmrkhoi.request.update_profile_request;


public interface User_inter {
	public void saveUser(SignUp_request signRequest);
	
	public ResponseEntity<String> get_verify(String gmail);
	
	public void get_verify_send(String gmail);
	
	public ResponseEntity<token_repoonse> getinfo(String token);
	
	public boolean check_verify_token(String token);
	
	public ResponseEntity<SignIn_reponse> SignIn(SignIn_request signInrequest);
	
	public ResponseEntity<List<getfriend_reponse>> user(String gmail);

	public ResponseEntity<List<getfriend_reponse>> user_name(String name);
	
	public ResponseEntity<List<getfriend_reponse>> list_send_request();
	
	public ResponseEntity<List<getfriend_reponse>> list_friend();
	
	public ResponseEntity<List<getfriend_reponse>> list_friend_request();
	
	public ResponseEntity<List<getfriend_reponse>> list_not_friend();
	
	
	public ResponseEntity<List<array_connect_reponse>> array_connect();
	
	public ResponseEntity<token_repoonse> uploadprofile(update_profile_request data);
	
	public ResponseEntity<List<getfriend_reponse>> getusergroup(Long idgroup);
}
