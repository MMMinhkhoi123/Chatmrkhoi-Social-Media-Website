package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.*;
import com.chatmrkhoi.chatmrkhoi.Util.convert.UserConvert;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.common.EUserInitial;
import com.chatmrkhoi.chatmrkhoi.design.Interator.ConcreteCollection;
import com.chatmrkhoi.chatmrkhoi.design.Interator.IConnection;
import com.chatmrkhoi.chatmrkhoi.design.Interator.IListIterator;
import com.chatmrkhoi.chatmrkhoi.design.Interator.UserSugIterator;
import com.chatmrkhoi.chatmrkhoi.design.state.ContextUser;
import com.chatmrkhoi.chatmrkhoi.design.state.OnlineState;
import com.chatmrkhoi.chatmrkhoi.design.strategy.user.finduser.ContextFindUser;
import com.chatmrkhoi.chatmrkhoi.design.strategy.user.finduser.FindByEmail;
import com.chatmrkhoi.chatmrkhoi.design.strategy.user.finduser.FindByName;
import com.chatmrkhoi.chatmrkhoi.design.strategy.user.getlistuser.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.exception.cutomer.LoginException;
import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IJwtRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IGroupRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataLoginReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataRegisterReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoUpdateReq;
import com.chatmrkhoi.chatmrkhoi.security.JwtToken;
import com.chatmrkhoi.chatmrkhoi.service.IUser;

@Service
public class UserSer implements IUser {

	@Autowired IUserRepo USER_REPO;
	
	@Autowired EmailSer EMAIL_SER;
	
	@Autowired JwtToken centerToken;

	@Autowired AuthenticationManager manager;

	@Autowired IFriendRepo FRIEND_REPO;
	
	@Autowired JwtSer JWT_SER;
	
	@Autowired IJwtRepo JWT_REP;
	
	@Autowired IGroupRepo GROUP_REPO;
	
	@Autowired InfoSer INFO_SER;
	
	@Autowired IInfoRepo INFO_REPO;
	
	@Autowired IActionRepo ACTION_REPO;

	@Autowired GroupSer GROUP_SER;

	@Autowired MessageSer MESSAGE_SER;

	@Autowired Common COMMON;

	@Autowired UserConvert CONVERT;

	@Autowired ContextFindUser CONTEXT_FIND;

	@Autowired FindByEmail FINDBYEMAIL;

	@Autowired FindByName FINDBYNAME;

	@Autowired ContextGetDataInitialUser CONTEXT_INIT;

	@Autowired ListMadeFriends LIST_MADE_FRIEND;

	@Autowired ListNotFriend LIST_NOT_FRIEND;

	@Autowired ListSendFriendRequest LIST_SEND_FRIEND_REQUEST;

	@Autowired ListFriendRequests LIST_FRIEND_REQUEST;


	private final PasswordEncoder encoder = new BCryptPasswordEncoder();


	@Override
	public ResponseEntity<List<DataInfoUserOtherRep>> findAllByType(String key, String data) {
		switch (key) {
			case "email" -> CONTEXT_FIND.setTypeFind(FINDBYEMAIL);
			case "name" -> CONTEXT_FIND.setTypeFind(FINDBYNAME);
            default -> {
                return ResponseEntity.status(500).body(null);
            }
        }
		List<DataInfoUserOtherRep> dataResult =  CONTEXT_FIND.findUser(data);
		return ResponseEntity.status(200).body(dataResult);
	}

	@Override
	public ResponseEntity<List<DataInfoUserOtherRep>> getAllDataInitialByType(String type) {
		switch (EUserInitial.valueOf(type)) {
			case FRIEND -> CONTEXT_INIT.setTypeUser(LIST_MADE_FRIEND);
			case FRIEND_REQUEST -> CONTEXT_INIT.setTypeUser(LIST_FRIEND_REQUEST);
			case NOT_FRIEND -> CONTEXT_INIT.setTypeUser(LIST_NOT_FRIEND);
			case SEND_FRIEND_REQUEST -> CONTEXT_INIT.setTypeUser(LIST_SEND_FRIEND_REQUEST);
			default ->  {
				return ResponseEntity.status(500).body(null);
			}
		}
		return ResponseEntity.status(200).body(CONTEXT_INIT.getData());
	}




	@Override
	public void save(DataRegisterReq DataReq) {

		UserEn DataSave = UserEn.builder()
				.gmails(DataReq.getGmails())
				.passwords(encoder.encode(DataReq.getPasswords()))
				.verify(false)
				.build();

		Optional<UserEn> DataFindEd = USER_REPO.findByEmail(DataReq.getGmails());

		DataFindEd.ifPresentOrElse((e) -> {
			if(e.isVerify()) {
				USER_REPO.save(DataSave);
			} else {
				USER_REPO.updateByEmail(DataSave.getGmails(), DataSave.getPasswords());
				InfoEn info = INFO_REPO.findByIdUser(e.getId()).orElseThrow();
				info.setFullnames(DataReq.getFullname());
				INFO_SER.update(info);
			}
		},() -> {
			UserEn UserNew = USER_REPO.save(DataSave);
			INFO_SER.save(DataReq.getFullname(), UserNew.getId());
		});
		EMAIL_SER.SendEmailVerify(DataReq.getGmails());
	}

	@Override
	public boolean checkValidToken(String token) {
		if(!centerToken.checkToken(token)) {
			return false;	
		} else {
			USER_REPO.updateByEmail(centerToken.getUsernameFromToken(token));
			return true;
		}
	}


	@Override
	public ResponseEntity<DataLoginRep> login(DataLoginReq loginData) {

		DataLoginRep DataRep = new DataLoginRep();

		Optional<UserEn> user = USER_REPO.findByEmail(loginData.getGmail());

		user.ifPresentOrElse((Data) -> {

			if(!Data.isVerify()) {
				throw new LoginException("Account not verify", "Account");
			}

			boolean StatusPasswordResult = encoder.matches(loginData.getPassword(), USER_REPO.findByEmail(loginData.getGmail()).orElseThrow().getPasswords());

			if(!StatusPasswordResult) {
				throw new LoginException("Password incorrect", "Password");
			}

			Authentication author = manager.authenticate(new UsernamePasswordAuthenticationToken(loginData.getGmail(),loginData.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(author);

			String token = centerToken.getToken(loginData.getGmail());

			JWT_SER.save(loginData);

			DataRep.setToken(token);
			DataRep.setGmail(loginData.getGmail());

			Optional<InfoEn> DataInfo = INFO_REPO.findByIdUser(user.orElseThrow().getId());
			DataInfo.ifPresent((e) -> {
				DataRep.setFullname(e.getFullnames());
				if(e.getAvatars() == null) {
					DataRep.setProfile("not");
				} else {
					DataRep.setProfile("fish");
				}
			});
		}, () -> {
			throw new LoginException("Account not exist", "Account");
		});
		return ResponseEntity.status(200).body(DataRep);
	}





	@Override
	public ResponseEntity<DataInfoAuthenRep> getInfo(String token) {
		String email = centerToken.getUsernameFromToken(token);
		UserEn UserFindEd = USER_REPO.findByEmail(email).orElseThrow();
		InfoEn InfoFindEd = INFO_REPO.findByIdUser(UserFindEd.getId()).orElseThrow();
		DataInfoAuthenRep DataRep = DataInfoAuthenRep.builder()
				.desc(InfoFindEd.getDescs())
				.fullname(InfoFindEd.getFullnames())
				.gmail(UserFindEd.getGmails())
				.briday(InfoFindEd.getBirday())
				.gender(InfoFindEd.getGender())
				.images(InfoFindEd.getAvatars())
				.type_img(InfoFindEd.getType_avatas())
				.notify(InfoFindEd.isNotify())
				.id(UserFindEd.getId()).build();
		return ResponseEntity.status(200).body(DataRep);
	}




	@Override
	public ResponseEntity<List<DataInfoRoomRep>> getListRoomChat() {
		Long id = COMMON.getUserAuthentication().getId();
		return  ResponseEntity.status(200).body(CONVERT.getListInfoRoom(id));
	}
	@Override
	public ResponseEntity<List<DataInfoUserOtherRep>> getusergroup(Long idGroup) {
		GroupEn group = GROUP_REPO.findById(idGroup).orElseThrow();
		List<DataInfoUserOtherRep> dataRep = new ArrayList<DataInfoUserOtherRep>();
		group.getUsersentitiesx().forEach((e) -> {
			dataRep.add(convertData(e, group.getCoderoom()));
		});
		return ResponseEntity.ok(dataRep);
	}


	@Autowired ConcreteCollection concreteCollection;
	@Override
	public ResponseEntity<List<DataInfoUserOtherRep>> getListSugFiend() {
		List<DataInfoUserOtherRep> DataResult = new ArrayList<>();
		concreteCollection.setListUser(USER_REPO.findAll());
		IListIterator iterator = concreteCollection.CreateIterator();
		for (DataInfoUserOtherRep user = iterator.First(); iterator.hasNext() ; user = iterator.getNext() ) {
			DataResult.add(user);
		}
		iterator.reset();
		return ResponseEntity.ok(DataResult);
	}

	public DataInfoUserOtherRep convertData(UserEn data, String codeRoom) {
		InfoEn InfoFindEd = INFO_REPO.findByIdUser(data.getId()).orElseThrow();
	 	return DataInfoUserOtherRep
				.builder().coderoom(codeRoom)
				.fullname(InfoFindEd.getFullnames())
				.id(data.getId())
				.type_img(InfoFindEd.getType_avatas())
				.images(InfoFindEd.getAvatars())
				.build();
	}

	@Override
	public ResponseEntity<String> getStatusVerify(String gmail) {
		if(USER_REPO.findByEmail(gmail).orElseThrow().isVerify()) {
			return ResponseEntity.ok("verify");
		} else {
			return ResponseEntity.ok("not verify");
		}
	}

	@Override
	public ResponseEntity<DataInfoAuthenRep> updateInfo(DataInfoUpdateReq data) {

		InfoEn cake = INFO_REPO.findByIdUser(COMMON.getUserAuthentication().getId()).orElseThrow();
		cake.setAvatars(data.getAvata());
		cake.setBirday(data.getBirday());
		cake.setDescs(data.getDesc());
		cake.setFullnames(data.getFullname());
		cake.setGender(data.getGender());
		cake.setType_avatas(data.getType_img());

		InfoEn DataInfo = INFO_REPO.save(cake);
	    DataInfoAuthenRep x = DataInfoAuthenRep.builder()
						.desc(DataInfo.getDescs())
						.fullname(DataInfo.getFullnames())
						.gender(DataInfo.getGender())
						.gmail(COMMON.getUserAuthentication().getGmails())
						.id(COMMON.getUserAuthentication().getId())
						.images(DataInfo.getAvatars())
						.type_img(DataInfo.getType_avatas())
						.briday(DataInfo.getBirday())
						.build();
		
		return ResponseEntity.ok(x);
	}

}
