package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.action_entity;
import com.chatmrkhoi.chatmrkhoi.entity.cakeinfo_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.getfriend_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Friend_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.action_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.info_repo;
import com.chatmrkhoi.chatmrkhoi.request.updatefeel_request;
import com.chatmrkhoi.chatmrkhoi.request.upinfo_full_request;
import com.chatmrkhoi.chatmrkhoi.service.Info_inter;

@Service
public class Info_service implements Info_inter {

	
	@Autowired
	info_repo info_repo;
	@Autowired
	User_repo user_repo;
	@Autowired
	action_repo action_repo;
	@Autowired
	Friend_repo friend_repo;
	
	
	// RETURN USER AUTHENCATED
	public Users_entity user_authe() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users_entity user = user_repo.findbygmail(userDetails.getUsername()).get();
		return user;
	}
	
	String statussx = "offline";
	Long timeaction = null;
	// CONVERT FRIEND EVERYONE
		public getfriend_reponse mode_friend(Long iduser, String status, String coderoom) {
			statussx = "offline";
			Users_entity data = user_repo.findById(iduser).get();
		    Optional<action_entity> acEntity = action_repo.findbyiduser(iduser);
		    acEntity.ifPresent((e) -> {
		    	statussx = e.getStatus();
		    	timeaction= e.getTimetamp();
		    });
			if(data.isVerify() == false) {
				return null;
			}
			cakeinfo_entity info = info_repo.findbyiduser(data.getId()).get();
			if(info.getAvatars() == null) {
				return null;
			}
			return getfriend_reponse.builder()
					.desc(info.getDescs())
					.fullname(info.getFullnames())
					.gmail(data.getGmails())
					.status(status)
					.type_img(info.getType_avatas())
					.id(data.getId())
					.briday(info.getBirday())
					.gender(info.getGender())
					.images(info.getAvatars())
					.action(statussx)
					.coderoom(coderoom)
					.timeaction(timeaction)
					.countfriend(get_count_friend(iduser))
					.build();
		}
	
		
		int count = 0;
		public int get_count_friend(Long id) {
			Users_entity user = user_repo.findById(id).get();
			count = user.getFriend_entities().size();
			count = count + friend_repo.findallbyiduser(id).get().size();
			   return count;
		}
		
		
	@Override
	public void create_info_user(String fullname, Long iduser) {
		// TODO Auto-generated method stub
	  info_repo.save(cakeinfo_entity.builder()
			  .idusers(iduser)
			  .fullnames(fullname)
			  .build());
	}


	@Override
	public void update_info_user(cakeinfo_entity data) {
		// TODO Auto-generated method stub
		info_repo.save(data);
	}
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	

	@Override
	public void update_info_user_full(upinfo_full_request uprequest) {
		// TODO Auto-generated method stub
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long iduser = user_repo.findbygmail(userDetails.getUsername()).get().getId();
		cakeinfo_entity info = info_repo.findbyiduser(iduser).get();
		info.setAvatars(uprequest.getNamefile());
		info.setBirday(uprequest.getDate());
		info.setGender(uprequest.getGender());
		info.setType_avatas(uprequest.getType());
		info_repo.save(info);
		messagingTemplate.convertAndSend("/everyone/newuser", mode_friend(user_authe().getId(), "not", null));
	}

}
