package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Friend_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.getfriend_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Friend_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Watch_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.feel_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.file_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.mess_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.pin_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.revoke_repo;
import com.chatmrkhoi.chatmrkhoi.service.Friend_inter;

@Service
public class Friend_service implements Friend_inter{

	
	@Autowired
	Friend_repo friend_repo;
	@Autowired
	User_repo user_repo;
	@Autowired
	feel_repo feel_repo;
	@Autowired
	mess_repo mess_repo;
	@Autowired
	file_repo file_repo;
	@Autowired
	pin_repo pin_repo;
	@Autowired
	revoke_repo Revoke_repo;
	@Autowired
	File_service file_service;
	@Autowired
	Watch_repo watch_repo;
	// RETURN USER AUTHENCATED
	public Users_entity user_authe() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users_entity user = user_repo.findbygmail(userDetails.getUsername()).get();
		return user;
	}
	
	@Override
	public void add_friend(Long idfriend) {
		Long id = user_authe().getId();
		String code = id.toString() + idfriend.toString();
		Friend_entity data = Friend_entity.builder()
				.status("request")
				.idfriend(idfriend)
				.coderoom(code)
				.time(new Date().getTime())
				.usersentities(user_repo.findById(id).get())
				.build();
		friend_repo.save(data);
	}

	@Override
	public ResponseEntity<array_connect_reponse> action_friend(Long idfriend, String status) {
		Long id = user_authe().getId();
		if(status.equalsIgnoreCase("destroy")) {
			Optional<Friend_entity> data = friend_repo.findbydoubleid(idfriend, id);
			if(data.isPresent() == true) {
				friend_repo.deleteById(data.get().getId());
			} else {
				data = friend_repo.findbydoubleid(id, idfriend);
				friend_repo.deleteById(data.get().getId());
			}
			return null;
		} else {
			Friend_entity friends = null;
			Optional<Friend_entity> data = friend_repo.findbydoubleid(idfriend, id);
			if(data.isPresent() == true) {
				friend_repo.apply(data.get().getId());
			} else {
				data = friend_repo.findbydoubleid(id, idfriend);
				data.ifPresent((e) -> {
					e.setStatus("friend");
				});
				friends = friend_repo.save(data.get());
			}
			return ResponseEntity.ok(array_connect_reponse.builder()
					.coderoom(friends.getCoderoom())
					.time(friends.getTime())
					.idfriend(idfriend).build());
		}
		
	}

	@Override
	public void unfriend(Long idfriend) {
		Long id = user_authe().getId();
		Users_entity userx = user_repo.findById(id).get();
		
		if (checkexist(userx.getFriend_entities(), idfriend) == true) {
			Friend_entity friEntity = friend_repo.findbydoubleid(idfriend, id).get();
			friEntity.getMess_entity().forEach((e) -> {
				feel_repo.deletefeelall(e.getId());
				e.getFile_entities().forEach((ex) -> {
					file_service.deletefile(ex.getNamefile(), ex.getTypefile());
				});
				file_repo.deletefileall(e.getId());
				pin_repo.deletepinall(e.getId());
				Revoke_repo.deleterevokeeall(e.getId());
				watch_repo.deleteallwatch(e.getId());
				mess_repo.deleteById(e.getId());
			});
			friend_repo.destroy(id, idfriend);
		} else {
			Friend_entity friEntity = friend_repo.findbydoubleid( id,idfriend).get();
			friEntity.getMess_entity().forEach((e) -> {
				feel_repo.deletefeelall(e.getId());
				e.getFile_entities().forEach((ex) -> {
					file_service.deletefile(ex.getNamefile(), ex.getTypefile());
				});
				file_repo.deletefileall(e.getId());
				pin_repo.deletepinall(e.getId());
				Revoke_repo.deleterevokeeall(e.getId());
				watch_repo.deleteallwatch(e.getId());
				mess_repo.deleteById(e.getId());
			});
			friend_repo.destroy(idfriend, id);
		}
	}
	
	
	
	boolean status = false;
	public boolean checkexist(Set<Friend_entity> list, Long idfriend ){
		status = false;
		list.forEach(e -> {
			
			if(e.getIdfriend().compareTo(idfriend) == 0) {
				status = true;
			}
		});
		return status;
	}

	
	
}
