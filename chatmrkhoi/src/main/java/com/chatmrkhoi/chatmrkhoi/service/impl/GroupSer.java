package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chatmrkhoi.chatmrkhoi.Util.convert.GroupConvert;
import com.chatmrkhoi.chatmrkhoi.Util.convert.MessageConverts;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.factorymethod.ACenterActivityMess;
import com.chatmrkhoi.chatmrkhoi.design.factorymethod.ConcreteGroupMessenger;
import com.chatmrkhoi.chatmrkhoi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoGroupNewAndRoomConnectRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessNewAndInfoNumberRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoRoomRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoGroupRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataAddGroupReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataKickOrAddAnNumberReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataUpdateNameGropReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoGroupUpdateReq;
import com.chatmrkhoi.chatmrkhoi.service.IGroup;


@Service
public class GroupSer implements IGroup {

	@Autowired IFileRepo FILE_REPO;
	@Autowired IFriendRepo FRIEND_REPO;
	@Autowired IUserRepo USER_REPO;
	@Autowired IGroupRepo GROUP_REPO;
	@Autowired IMessageRepo MESSAGE_REPO;
	@Autowired MessageSer MESSAGE_SER;
	@Autowired IInfoRepo INFO_REPO;
	@Autowired IFeelRepo FEEL_REPO;
	@Autowired IPinRepo PIN_REPO;
	@Autowired IRevokeRepo REVOKE_REPO;
	@Autowired FileSer FILE_SER;
	@Autowired IWatchRepo WATCH_REPO;
	@Autowired Common COMMON;
    @Autowired MessageConverts CONVERT;
	@Autowired GroupConvert CONVERT_GROUP;
	@Autowired NoticeSer noticeSer;
	
	@Override
	public ResponseEntity<DataInfoGroupNewAndRoomConnectRep> save(DataAddGroupReq data) {
		String img = null;
		if(data.getIdimg() != null) {
		   img = FILE_REPO.findById(data.getIdimg()).orElseThrow().getNamefile();
		}
		GroupEn groupNew = GroupEn.builder().avata(img)
				.name(data.getNamegroup())
				.userEntity(COMMON.getUserAuthentication())
				.usersentitiesx(getMaster(data.getArrayperson()))
				.coderoom(data.getCoderoom())
				.time(new Date().getTime())
				.build();

		GroupEn groupSaved = GROUP_REPO.save(groupNew);
		noticeSer.save("Group", groupSaved, "AddGroup", null);


		groupSaved.setCoderoom(groupSaved.getId() + "GR" + groupSaved.getCoderoom());
		GROUP_REPO.save(groupSaved);

	 DataInfoRoomRep infoRepo = DataInfoRoomRep.builder()
			 .idgroup(groupSaved.getId())
			 .coderoom(groupSaved.getCoderoom())
			 .time(groupNew.getTime())
			 .build();
	 
	 DataInfoGroupRep infoGroup = DataInfoGroupRep.builder()
				 .coderoom(infoRepo.getCoderoom())
				 .id(groupSaved.getId())
				 .img(groupSaved.getAvata())
				 .master(groupSaved.getUserEntity().getId())
				 .name(groupSaved.getName())
			 .build();
	 DataInfoGroupNewAndRoomConnectRep dataRep = DataInfoGroupNewAndRoomConnectRep.builder().connect(infoRepo).mygroup(infoGroup).build();
	  return ResponseEntity.ok(dataRep);
	}


	private List<UserEn> getMaster(List<Long> array) {
		List<UserEn> arrays = new ArrayList<>();
		array.forEach((e) -> {
			arrays.add(USER_REPO.findById(e).orElseThrow());
		});
		return arrays;
	}

	@Override
	public ResponseEntity<List<DataInfoGroupRep>> getInfo() {
	  UserEn user = COMMON.getUserAuthentication();
	  List<DataInfoGroupRep> array = new ArrayList<DataInfoGroupRep>();
	  user.getGrouarraymaster().forEach((e) -> {
		  DataInfoGroupRep data = DataInfoGroupRep.builder()
				  .count(getCountNumber(e))
				  .id(e.getId())
				  .img(e.getAvata())
				  .coderoom(e.getCoderoom())
				  .name(e.getName())
				  .master(e.getUserEntity()
				  .getId())
						  .build();
		  array.add(data);
	  });
	  user.getGroup_entities().forEach((e) -> {
		  DataInfoGroupRep data = DataInfoGroupRep.builder()
				  .count(getCountNumber(e))
				  .id(e.getId())
				  .img(e.getAvata())
				  .coderoom(e.getCoderoom())
				  .name(e.getName())
				  .master(e.getUserEntity()
					.getId())
				  .build();
		  array.add(data);
	  });
		return ResponseEntity.status(200).body(array);
	}



	@Override
	public ResponseEntity<DataInfoGroupRep> updateNameGroup(DataUpdateNameGropReq data) {
		GroupEn groupFindEd = GROUP_REPO.findById(data.getId()).orElseThrow();
		groupFindEd.setName(data.getName());
		GroupEn groupSaved = GROUP_REPO.save(groupFindEd);
		DataInfoGroupRep dataRep = DataInfoGroupRep.builder()
				.coderoom(groupSaved.getCoderoom())
				.id(groupSaved.getId())
				.img(groupSaved.getAvata())
				.master(groupSaved.getUserEntity().getId())
				.name(groupSaved.getName())
				.build();
		return ResponseEntity.ok(dataRep);
	}



	@Override
	public ResponseEntity<DataInfoGroupRep> updateImgGroup(DataInfoGroupUpdateReq data) {
		String name = "";
	     GroupEn groupFindEd = GROUP_REPO.findById(data.getId()).orElseThrow();
			     if(data.getFile() != null) {
			    	 File file_one = new File("");
					 String currentDirectory_img = file_one.getAbsolutePath() + "/target/classes/static/img/";
					  String  nameApply = COMMON.getUserAuthentication().getId().toString() + "&3&" + data.getFile().getOriginalFilename();
				       name = convertFileName(currentDirectory_img, nameApply);
				       Path  paths = Paths.get(currentDirectory_img + name);
					     try {
							  Files.copy(data.getFile().getInputStream(),paths,StandardCopyOption.REPLACE_EXISTING);
							} catch (IOException e) {
							  System.out.println(e.getMessage());
							}
							groupFindEd.setAvata(name);
			     }

			     if(data.getName() != null) {
			    	 groupFindEd.setName(data.getName());
			     }

				GroupEn groupSaved = GROUP_REPO.save(groupFindEd);

				 DataInfoGroupRep dataRep = DataInfoGroupRep.builder()
						.coderoom(groupSaved.getCoderoom())
						.id(groupSaved.getId())
						.img(groupSaved.getAvata())
						.master(groupSaved.getUserEntity().getId())
						.name(groupSaved.getName())
						.build();

		return ResponseEntity.ok(dataRep);
	}



	private String convertFileName(String url, String name) {
		int i = 0;
		File file  = new File(url + i +  "&3&" + name );
		while (file.exists()) {
			i++;
			file  = new File(url + i + "&3&" + name);
		}
		return   i + "&3&" + name;
	}

	@Autowired
    ConcreteGroupMessenger FactoryGroupMess;

	@Override
	public ResponseEntity<DataMessNewAndInfoNumberRep> addNumber(DataKickOrAddAnNumberReq data) {
		FactoryGroupMess.setData(data);
		String codeOld = GROUP_REPO.findById(data.getId()).orElseThrow().getCoderoom();
		ACenterActivityMess FactoryMess = FactoryGroupMess;
		MessageEn m = MESSAGE_REPO.save(FactoryMess.getMessageNew());
		DataMessageRep messNew = CONVERT.convertMessRep(m);
		DataInfoUserOtherRep info = CONVERT_GROUP.ConvertData(USER_REPO.findById(data.getIdfriend()).orElseThrow(), codeOld);
		DataMessNewAndInfoNumberRep dataRep = DataMessNewAndInfoNumberRep.builder().mess_reponse(messNew).getfriend_reponse(info).build();
		noticeSer.save("Group", GROUP_REPO.findById(data.getId()).orElseThrow(), "AddMember", USER_REPO.findById(data.getIdfriend()).orElseThrow());
		return ResponseEntity.ok(dataRep);
	}


	public int getCountNumber(GroupEn group) {
		List<DataInfoUserOtherRep> dataRep = new ArrayList<DataInfoUserOtherRep>();
		group.getUsersentitiesx().forEach((e) -> {
			dataRep.add(CONVERT_GROUP.ConvertData(e, group.getCoderoom()));
		});
		return dataRep.size();
	}


	@Override
	public ResponseEntity<DataMessNewAndInfoNumberRep> kickNumber(DataKickOrAddAnNumberReq data) {
		List<UserEn> arrayUser = new ArrayList<>();
		GROUP_REPO.findById(data.getId()).orElseThrow().getUsersentitiesx().forEach((e) -> {
			if(!e.getId().equals(data.getIdfriend())) {
				arrayUser.add(e);
			}
		});

		GroupEn group =  GROUP_REPO.findById(data.getId()).orElseThrow();
		group.setUsersentitiesx(arrayUser);
		group.setCoderoom(data.getCodenew());
		GROUP_REPO.save(group);

		MessageEn mess = MessageEn.builder()
				.usersentity(COMMON.getUserAuthentication())
				.content("")
				.time(System.currentTimeMillis())
				.groupmess(GROUP_REPO.findById(data.getId()).orElseThrow())
				.addgroup( "kich&" + data.getIdfriend())
				.build();

		MESSAGE_REPO.save(mess);
		DataMessageRep messNew = MESSAGE_SER.CONVERT.convertMessRep(mess);
		DataMessNewAndInfoNumberRep mata = DataMessNewAndInfoNumberRep.builder().mess_reponse(messNew).build();
		noticeSer.save("Group", group, "KickMember", USER_REPO.findById(data.getIdfriend()).orElseThrow());
		return ResponseEntity.ok(mata);
	}

	@Override
	public void delete(Long id) {
		GroupEn group = GROUP_REPO.findById(id).orElseThrow();
		noticeSer.save("Group", group, "RemoveGroup", null);
		group.getMess_entity().forEach((e) -> {
			FEEL_REPO.deleteByIdMess(e.getId());
			e.getFile_entities().forEach((ex) -> {
				FILE_SER.delete(ex.getNamefile(), ex.getTypefile());
			});
			FILE_REPO.deleteAllByIdMess(e.getId());
			PIN_REPO.deleteByIdMess(e.getId());
			REVOKE_REPO.deleteByIdMess(e.getId());
			WATCH_REPO.deleteByIdMess(e.getId());
			MESSAGE_REPO.deleteById(e.getId());
		});
		group.setUsersentitiesx(null);
	    GROUP_REPO.deleteById(id);
	}

	
	@Override
	public ResponseEntity<DataMessageRep> out(Long idGroup, String code) {
		GroupEn group = GROUP_REPO.findById(idGroup).orElseThrow();
		List<UserEn> array = new ArrayList<>();
		group.getUsersentitiesx().forEach((e) -> {
			if(!e.getId().equals(COMMON.getUserAuthentication().getId())) {
				array.add(e);
			}
		});

		group.setCoderoom(code);
		group.setUsersentitiesx(array);
		GROUP_REPO.save(group);
		
		MessageEn mess = MessageEn.builder()
				.addgroup("out&" + COMMON.getUserAuthentication().getId().toString())
				.time(System.currentTimeMillis())
				.start_date(false)
				.groupmess(group)
				.content("")
				.usersentity(COMMON.getUserAuthentication())
				.status(false)
				.build();

		MessageEn messSaved = MESSAGE_REPO.save(mess);
		DataMessageRep dataRep = DataMessageRep.builder()
				.room(code).id(messSaved.getId())
				.id_user(messSaved.getUsersentity().getId())
				.id_group(messSaved.getGroupmess().getId())
				.time(messSaved.getTime())
				.group_status(messSaved.getAddgroup()).build();
		return ResponseEntity.ok(dataRep);
	}

}
