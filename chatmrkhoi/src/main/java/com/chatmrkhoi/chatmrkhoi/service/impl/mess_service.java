package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Friend_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Group_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.feel_entity;
import com.chatmrkhoi.chatmrkhoi.entity.file_entity;
import com.chatmrkhoi.chatmrkhoi.entity.revoke_entity;
import com.chatmrkhoi.chatmrkhoi.entity.watch_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.watch_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Friend_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.file_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.group_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.mess_repo;
import com.chatmrkhoi.chatmrkhoi.request.file_reuqest;
import com.chatmrkhoi.chatmrkhoi.request.sendmess_request;
import com.chatmrkhoi.chatmrkhoi.request.sendmove_request;
import com.chatmrkhoi.chatmrkhoi.request.unmess_request;
import com.chatmrkhoi.chatmrkhoi.request.updatefeel_request;
import com.chatmrkhoi.chatmrkhoi.service.mess_inter;

@Service
public class mess_service implements mess_inter {
@Autowired
mess_repo mess_repo;

@Autowired
Friend_repo friend_repo;

@Autowired
User_repo user_repo;

@Autowired
File_service file_service;


@Autowired
file_repo fireRepo;


@Autowired
group_repo group_repo;


int i;

Mess_entity mess;


Friend_entity datax;

// RETURN USER AUTHENCATED
public Users_entity user_authe() {
	UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	Users_entity user = user_repo.findbygmail(userDetails.getUsername()).get();
	return user;
}

@Override
public ResponseEntity<List<mess_reponse>> sendmess(sendmess_request sendmess_request) {
	List<mess_reponse> messrepo = new ArrayList<mess_reponse>();
	 List<Mess_entity> list = new ArrayList<Mess_entity>();
	 Users_entity user = user_authe();
	// check time send mess
	if (sendmess_request.getStatus().equalsIgnoreCase("group") ) {
		Group_entity group = group_repo.findById(sendmess_request.getId_friend()).get();
		 mess_repo.findAll().forEach((e) -> {
			 if(e.getGroupmess() != null) {
				 if(e.getGroupmess().getId() == group.getId()) {
				 list.add(e);
				 }
			 }
		 });
		 if (list.size() > 0) {
			  java.util.Date date_one = new java.util.Date();
			  Date date_two = new Date(list.get( list.size() - 1).getTime());
			 if(date_one.getDate() != date_two.getDate()) {
			   // add
				 Mess_entity messdate = Mess_entity.builder()
						 .groupmess(group)
						 .content("")
						 .start_date(true)
						 .usersentity(user)
						 .time(System.currentTimeMillis()).build();
				Mess_entity messdatex =  mess_repo.save(messdate);
				messrepo.add(convert_reponse(messdatex));
			 }
		 }	
		mess = Mess_entity.builder()
				.content(sendmess_request.getContent())
				.time(System.currentTimeMillis())
				.groupmess(group)
				.typereply(sendmess_request.getTyperep())
				.reply(sendmess_request.getReply())
				.usersentity(user)
				.build();	

	} else {
		Optional<Friend_entity> friend = friend_repo
				.findbydoubleid(sendmess_request.getId_friend(), user.getId());
		friend.ifPresentOrElse((e) -> {
			datax = e;
		}, () -> {
			datax = friend_repo
			.findbydoubleid(user.getId(), sendmess_request.getId_friend())
					.get();	
		});

		 mess_repo.findAll().forEach((e) -> {
			 if(e.getFriendmess() !=  null) {
				 if(e.getFriendmess().getId() == datax.getId()) {
					 list.add(e);
				 }
			 }
		 });
		 
		 if (list.size() > 0) {
			  java.util.Date date_one = new java.util.Date();
			  Date date_two = new Date(list.get( list.size() - 1).getTime());
			 if(date_one.getDate() != date_two.getDate()) {
			   // add
				 Mess_entity messdate = Mess_entity.builder()
						 .friendmess(datax)
						 .content("")
						 .start_date(true)
						 .usersentity(user_repo.findById(user.getId()).get())
						 .time(System.currentTimeMillis()).build();
				Mess_entity messdatex =  mess_repo.save(messdate);
				messrepo.add(convert_reponse(messdatex));
			 }
		 }		
		 
		mess = Mess_entity.builder()
				.content(sendmess_request.getContent())
				.time(System.currentTimeMillis())
				.friendmess(datax)
				.typereply(sendmess_request.getTyperep())
				.reply(sendmess_request.getReply())
				.usersentity(user_repo.findById(user.getId()).get())
				.build();
	}
	Mess_entity data = mess_repo.save(mess);
	
	if(sendmess_request.getIdfile().size() > 0) {
		sendmess_request.getIdfile().forEach((e) -> {
			fireRepo.update_file(e, mess.getId());
		});
	}
	mess_reponse datareponse = convert_reponse(data);
	messrepo.add(datareponse);
	return ResponseEntity.status(200).body(messrepo);
}



@Override
public ResponseEntity<List<mess_reponse>> getmess() {
	List<mess_reponse> array_reponse = new ArrayList<mess_reponse>();
	mess_repo.findAll().forEach((e) -> {
		array_reponse.add(convert_reponse(e));
	});
	return ResponseEntity.status(200).body(array_reponse);
}




public List<file_reuqest> getlistimg(Long idmess) {
	List<file_reuqest> data = new ArrayList<file_reuqest>();
	fireRepo.getallimg(idmess).get().forEach((e) -> {
		data.add(file_reuqest.builder()
				.type(e.getTypefile())
				.namefile(e.getNamefile())
				.id(e.getId())
				.idfile(idmess)
				.size(e.getSize())
				.status(true)
				.build());
	});
	return data;
}


public mess_reponse convert_reponse(Mess_entity mess) {
	mess_reponse data = null;
	
	if(mess.getPin() == null) {
		Long id = null;
		String code = null;
		Long groupid = null;
		if (mess.getGroupmess() == null) {
			id =  mess.getFriendmess().getIdfriend();
				if(mess.getUsersentity().getId() == id) {
					id = mess.getFriendmess().getUsersentities().getId();
				}
			code = 	mess.getFriendmess().getCoderoom();
		 } else {
			 code = mess.getGroupmess().getCoderoom();
			 groupid = mess.getGroupmess().getId();
		 }
		
		List<updatefeel_request> datafeel = new ArrayList<updatefeel_request>();
		if(mess.getFeelentitty()!= null) {
			datafeel = convertfell(mess.getFeelentitty());
		}
		List<unmess_request> datarevoke = new ArrayList<unmess_request>();
		if(mess.getRevokeatity()!= null) {
			 datarevoke = converrevoke(mess.getRevokeatity());
		}		
		List<watch_reponse> watch_reponses = new ArrayList<watch_reponse>();
		if(mess.getWatch_entities() != null) {
			watch_reponses = convertwatch(mess.getWatch_entities());
		}
		
		data = mess_reponse.builder()
				.content(mess.getContent())
				.id_user(mess.getUsersentity().getId())
				.id_friend(id)
				.time(mess.getTime())
				.listwatch(watch_reponses)
				.id(mess.getId())
				.start(mess.isStart_date())
				.feel(mess.getFeel())
				.id_group(groupid)
				.reply(mess.getReply())
				.img(getlistimg(mess.getId()))
				.typereply(mess.getTypereply())
				.listrevoke(datarevoke)
				.room(code)
				.group_status(mess.getAddgroup())
				.listfeel( datafeel)
				.build();
		
	
      } else {
    		Long id = null;
    		String code = null;
    		Long groupid = null;
    		if (mess.getGroupmess() == null) {
    			id =  mess.getFriendmess().getIdfriend();
    				if(mess.getUsersentity().getId() == id) {
    					id = mess.getFriendmess().getUsersentities().getId();
    				}
    			code = 	mess.getFriendmess().getCoderoom();
    		 } else {
    			 code = mess.getGroupmess().getCoderoom();
    			 groupid = mess.getGroupmess().getId();
    		 }
			List<watch_reponse> watch_reponses = new ArrayList<watch_reponse>();
			if(mess.getWatch_entities() != null) {
				watch_reponses = convertwatch(mess.getWatch_entities());
			}
    	  data = mess_reponse.builder().pin(mess.getPin()).time(mess.getTime())
    			  .id(mess.getId())
    			  .id_user(mess.getUsersentity()
    			  .getId()).id_group(groupid)
    			  .id_friend(id)
				  .listwatch(watch_reponses)
    			  .room(code).build();
      }
	return data;
}




public List<watch_reponse> convertwatch(Set<watch_entity> watch) {
	List<watch_reponse> watch_reponses = new ArrayList<watch_reponse>();
	watch.forEach((e) -> {
		watch_reponse ws = watch_reponse.builder().id(e.getUsersentity().getId()).timetamp(e.getTimetamp()).build();
		watch_reponses.add(ws);
	});
	return watch_reponses;
}



public List<unmess_request> converrevoke(Set<revoke_entity> revokexx) {
	List<unmess_request> x = new ArrayList<unmess_request>();
	revokexx.forEach((e) -> {
		unmess_request data = unmess_request.builder()
				.idmess(e.getMessentity().getId())
				.iduser(e.getUserentity().getId())
				.type(e.getType()).build();
		x.add(data);
	});
 return x;
}



public List<updatefeel_request> convertfell(Set<feel_entity> feel) {
	List<updatefeel_request> x = new ArrayList<updatefeel_request>();
	feel.forEach((e) -> {
         updatefeel_request datac =	updatefeel_request.builder()
        		 .feel(e.getMess())
        		 .type(e.getType())
        		 .idmess(e.getMessentity()
        		 .getId())
        		 .iduser(e.getIduser().getId())
        		 .build();
         x.add(datac);
	});
 return x;
}


@Override
public ResponseEntity<mess_reponse> unmess(Long idmess, String type) {
	mess_repo.unmess(type, idmess);
	Mess_entity mess = mess_repo.findById(idmess).get();
	mess_reponse messrreponse = convert_reponse(mess);
	return ResponseEntity.ok(messrreponse);
}




Mess_entity messxx = null;

@Override
public ResponseEntity<mess_reponse> movemess(sendmove_request data) {
	// TODO Auto-generated method stub

	Mess_entity mess = mess_repo.findById(data.getIdmess()).get();
	Mess_entity newx = getentitynew(mess);
	newx.setUsersentity(user_authe());
	if(data.getType().equalsIgnoreCase("text")) {
		newx.setFile_entities(null);
		if(data.getIdfriend() != null) {
			Friend_entity x = null;
			if(friend_repo.findbydoubleid(data.getIdfriend(),user_authe().getId() ).isPresent() == true) {
				x = friend_repo.findbydoubleid(data.getIdfriend(), user_authe().getId()).get();
			} else {
				x = friend_repo.findbydoubleid( user_authe().getId(),data.getIdfriend()).get();
			}
			newx.setGroupmess(null);
			newx.setFriendmess(x);
		} else {
			newx.setFriendmess(null);
			newx.setGroupmess(group_repo.findById(data.getIdgroup()).get());
		}
		messxx = mess_repo.save(newx);
	} else {
		newx.setContent("");
		if(data.getIdfriend() != null) {
			newx.setGroupmess(null);
			Friend_entity x = null;
			if(friend_repo.findbydoubleid(data.getIdfriend(),user_authe().getId()).isPresent() == true) {
				x = friend_repo.findbydoubleid(data.getIdfriend(), user_authe().getId()).get();
			} else {
				x = friend_repo.findbydoubleid( user_authe().getId(),data.getIdfriend()).get();
			}
			newx.setFriendmess(x);
		} else {
			newx.setFriendmess(null);
			newx.setGroupmess(group_repo.findById(data.getIdgroup()).get());
		}
		newx.setFile_entities(null);
		messxx = mess_repo.save(newx);
		fireRepo.getallimg(data.getIdmess()).get().forEach((e) -> {
		   file_entity filenew = file_service.savefile(e.getNamefile(), e.getTypefile(), e.getSize());
		   fireRepo.update_file(filenew.getId(), messxx.getId());
		});
	}
	mess_reponse dataxx = convert_reponse(messxx);
	return ResponseEntity.ok(dataxx);
}




public  Mess_entity getentitynew(Mess_entity ness) {
	Mess_entity mess_new = new Mess_entity();
			mess_new.setContent(ness.getContent());
			mess_new.setFile_entities(ness.getFile_entities());
			mess_new.setFriendmess(ness.getFriendmess());
			mess_new.setGroupmess(ness.getGroupmess());	
			mess_new.setStatus(ness.isStatus());
			mess_new.setTime(ness.getTime());
			mess_new.setType(ness.getType());
			
			mess_new.setUsersentity(ness.getUsersentity());
	
	return mess_new;
}

public List<mess_reponse> getmesscodes(String code) {
	List<mess_reponse> mess_reponse = new ArrayList<mess_reponse>();
	friend_repo.findallbycode(code).ifPresentOrElse((e) -> {
		mess_repo.getmessfriend(e.getId()).get().forEach((item) -> {
			mess_reponse.add(convert_reponse(item));
		});
	}, () -> {
	 mess_repo.getmessgroup(group_repo.findallbycode(code).get().getId()).get().forEach((item) -> {
		 mess_reponse.add(convert_reponse(item));
	 });
	});
	return mess_reponse;
}







	


}
