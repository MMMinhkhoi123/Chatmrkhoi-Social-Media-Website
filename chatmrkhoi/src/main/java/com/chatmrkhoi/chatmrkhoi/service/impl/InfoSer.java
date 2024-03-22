package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.Optional;

import com.chatmrkhoi.chatmrkhoi.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoReq;
import com.chatmrkhoi.chatmrkhoi.service.IInfo;

@Service
public class InfoSer implements IInfo {

	
	@Autowired IInfoRepo INFO_REPO;
	@Autowired IUserRepo USER_REPO;
	@Autowired IActionRepo ACTION_REPO;
	@Autowired IFriendRepo FRIEND_REPO;
	@Autowired private SimpMessageSendingOperations messagingTemplate;
	@Autowired Common COMMON;

	
	String statussx = "offline";
	Long timeaction = null;
	public DataInfoUserOtherRep mode_friend(Long idUser, String status, String CodeRoom) {
		statussx = "offline";
		UserEn data = USER_REPO.findById(idUser).orElseThrow();
		Optional<ActionEn> acEntity = ACTION_REPO.findByIdUser(idUser);
		acEntity.ifPresent((e) -> {
			statussx = e.getStatus();
			timeaction= e.getTimetamp();
		});
		if(!data.isVerify()) {
			return null;
		}
		InfoEn info = INFO_REPO.findByIdUser(data.getId()).orElseThrow();
		if(info.getAvatars() == null) {
			return null;
		}
		return DataInfoUserOtherRep.builder()
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
				.coderoom(CodeRoom)
				.timeaction(timeaction)
				.countfriend(getCountFriend(idUser))
				.build();
	}
	
		

	private int getCountFriend(Long id) {
		int count = 0;
		UserEn user = USER_REPO.findById(id).orElseThrow();
		count = user.getFriend_entities().size();
		count = count + FRIEND_REPO.findAllByFriendId(id).orElseThrow().size();
		   return count;
	}
		
		
	@Override
	public void save(String name, Long idUser) {
	  INFO_REPO.save(InfoEn.builder()
			  .idusers(idUser)
			  .fullnames(name)
			  .build());
	}


	@Override
	public void update(InfoEn data) {
		INFO_REPO.save(data);
	}


	

	@Override
	public void update(DataInfoReq data) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Long id = USER_REPO.findByEmail(userDetails.getUsername()).orElseThrow().getId();
		InfoEn info = INFO_REPO.findByIdUser(id).orElseThrow();
		info.setAvatars(data.getNamefile());
		info.setBirday(data.getDate());
		info.setGender(data.getGender());
		info.setType_avatas(data.getType());
		INFO_REPO.save(info);
		messagingTemplate.convertAndSend("/everyone/newuser", mode_friend(COMMON.getUserAuthentication().getId(), "not", null));
	}

	@Override
	public boolean updateNotify(boolean status) {
		InfoEn infoUse =  INFO_REPO.findByIdUser(COMMON.getUserAuthentication().getId()).orElseThrow();
		infoUse.setNotify(status);
		INFO_REPO.save(infoUse);
		return !status;
	}

}
