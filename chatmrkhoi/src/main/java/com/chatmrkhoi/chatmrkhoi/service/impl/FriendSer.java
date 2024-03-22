package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.Date;
import java.util.Optional;

import com.chatmrkhoi.chatmrkhoi.common.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.FriendEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoRoomRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IWatchRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IFeelRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IFileRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IPinRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IRevokeRepo;
import com.chatmrkhoi.chatmrkhoi.service.IFriend;

@Service
public class FriendSer implements IFriend {

	
	@Autowired IFriendRepo FRIEND_REPO;
	@Autowired IUserRepo USER_REPO;
	@Autowired IFeelRepo FEEL_REPO;
	@Autowired IMessageRepo MESSAGE_REPO;
	@Autowired IFileRepo FILE_REPO;
	@Autowired IPinRepo PIN_REPO;
	@Autowired IRevokeRepo REVOKE_REPO;
	@Autowired FileSer FILE_SER;
	@Autowired IWatchRepo WATCH_REPO;
	@Autowired Common COMMON;
	@Override
	public void save(Long idFriend) {
		Long id = COMMON.getUserAuthentication().getId();
		String code = id.toString() + idFriend.toString();
		FriendEn data = FriendEn.builder()
				.status("request")
				.idfriend(idFriend)
				.coderoom(code)
				.time(new Date().getTime())
				.usersentities(USER_REPO.findById(id).orElseThrow())
				.build();
		FRIEND_REPO.save(data);
	}
	@Override
	public void refuse(Long idFriend) {
		Long id = COMMON.getUserAuthentication().getId();
		Optional<FriendEn> data = FRIEND_REPO.findByFriendIdAndUserId(idFriend, id);
		if(data.isPresent()) {
			FRIEND_REPO.deleteById(data.get().getId());
		} else {
			data = FRIEND_REPO.findByFriendIdAndUserId(id, idFriend);
			FRIEND_REPO.deleteById(data.orElseThrow().getId());
		}
	}
	@Override
	public ResponseEntity<DataInfoRoomRep> accept(long IdFriend) {
		Long id = COMMON.getUserAuthentication().getId();
		FriendEn friends = new FriendEn();
		Optional<FriendEn> data = FRIEND_REPO.findByFriendIdAndUserId(IdFriend, id);
		if(data.isPresent()) {
			FRIEND_REPO.updateById(data.get().getId());
		} else {
			data = FRIEND_REPO.findByFriendIdAndUserId(id, IdFriend);
			data.ifPresent((e) -> {
				e.setStatus("friend");
			});
			friends = FRIEND_REPO.save(data.orElseThrow());
		}
		return ResponseEntity.ok(DataInfoRoomRep
				.builder()
				.coderoom(friends.getCoderoom())
				.time(friends.getTime())
				.idfriend(IdFriend).build());
	}
	@Override
	public void delete(Long idFriend) {
		Long id = COMMON.getUserAuthentication().getId();
		 Optional<FriendEn> friEntity = FRIEND_REPO.findByFriendIdAndUserId(idFriend, id);
		if(friEntity.isEmpty()) {
			friEntity = FRIEND_REPO.findByFriendIdAndUserId(id,idFriend);
		}
		friEntity.orElseThrow().getMess_entity().forEach((x) -> {
			FEEL_REPO.deleteByIdMess(x.getId());
			x.getFile_entities().forEach((ex) -> {
				FILE_SER.delete(ex.getNamefile(), ex.getTypefile());
			});
			FILE_REPO.deleteAllByIdMess(x.getId());
			PIN_REPO.deleteByIdMess(x.getId());
			REVOKE_REPO.deleteByIdMess(x.getId());
			WATCH_REPO.deleteByIdMess(x.getId());
			MESSAGE_REPO.deleteById(x.getId());
		});

		FRIEND_REPO.findByFriendIdAndUserId(idFriend, id).ifPresentOrElse((e) -> {
			FRIEND_REPO.deleteByUserIdAndFriendId(id, idFriend);
		}, () -> {
			FRIEND_REPO.deleteByUserIdAndFriendId(idFriend, id);
		});
	}
}
