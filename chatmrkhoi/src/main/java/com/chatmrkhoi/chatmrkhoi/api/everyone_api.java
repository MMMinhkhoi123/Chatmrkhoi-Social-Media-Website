package com.chatmrkhoi.chatmrkhoi.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.getfriend_reponse;
import com.chatmrkhoi.chatmrkhoi.request.destroy_request;

import com.chatmrkhoi.chatmrkhoi.service.impl.Friend_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.User_service;
@RestController
@RequestMapping("/everyone")
public class everyone_api {

	@Autowired
	User_service user_service;

	@Autowired
	Friend_service friend_service;
	@GetMapping("/list-not-friend")
	public ResponseEntity<List<getfriend_reponse>> list_not_friend() {
		return user_service.list_not_friend();
	}
	
	@GetMapping("/list-send-request")
	public ResponseEntity<List<getfriend_reponse>> list_send_request() {
		return user_service.list_send_request();
	}
	
	@GetMapping("/list-friend")
	public ResponseEntity<List<getfriend_reponse>> list_friend() {
		return user_service.list_friend();
	}
	
	@GetMapping("/list-friend-request")
	public ResponseEntity<List<getfriend_reponse>> friend_request() {
		return user_service.list_friend_request();
	}

	@GetMapping("/finduser/{key}")
	public ResponseEntity<List<getfriend_reponse>> finduser(@PathVariable(value = "key") Optional<String> key) {
		return user_service.user(key.get());
	}
	
	@GetMapping("/findusername/{key}")
	public ResponseEntity<List<getfriend_reponse>> find_user_name(@PathVariable(value = "key") Optional<String> key) {
		return user_service.user_name(key.get());
	}
	
	@PostMapping("/add-request/{id}")
	public void addrequest(@PathVariable(value = "id") Optional<Long> id) {
		friend_service.add_friend(id.get());
	}
	
	@PostMapping("/action-request")
	public ResponseEntity<array_connect_reponse> action_request(@RequestBody Optional<destroy_request> data) {
	   return friend_service.action_friend(data.get().getIdfriend(), data.get().getStatus());
	}
	
	@PostMapping("/unfiend/{id}")
	public void unfiend(@PathVariable(value = "id") Optional<Long> id) {
		friend_service.unfriend(id.get());
	}
}
