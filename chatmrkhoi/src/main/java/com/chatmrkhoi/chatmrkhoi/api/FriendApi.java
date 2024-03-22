package com.chatmrkhoi.chatmrkhoi.api;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoRoomRep;

import com.chatmrkhoi.chatmrkhoi.service.impl.FriendSer;

@RestController
@RequestMapping("/friend-center")
public class FriendApi {

	@Autowired FriendSer FRIEND_SER;
	@PostMapping("/add-request/{id}")
	public void addRequestFriend(@PathVariable(value = "id") Optional<Long> id) {
		FRIEND_SER.save(id.orElseThrow());
	}
	@GetMapping("/accept-request/{id}")
	public ResponseEntity<DataInfoRoomRep> acceptRequest(@PathVariable(value = "id") Optional<Long> id) {
		return  FRIEND_SER.accept(id.orElseThrow());
	}
	@GetMapping("/refuse-request/{id}")
	public void refuseRequest(@PathVariable(value = "id") Optional<Long> id) {
		FRIEND_SER.refuse(id.orElseThrow());
	}
	@GetMapping("/delete-friend/{id}")
	public void deleteFriend(@PathVariable(value = "id") Optional<Long> id) {
		FRIEND_SER.delete(id.orElseThrow());
	}
}
