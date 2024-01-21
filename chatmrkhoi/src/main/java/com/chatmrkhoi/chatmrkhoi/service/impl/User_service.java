package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception.SignIn_exception;
import com.chatmrkhoi.chatmrkhoi.entity.Friend_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Group_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Jwt_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.action_entity;
import com.chatmrkhoi.chatmrkhoi.entity.cakeinfo_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.SignIn_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.getfriend_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.token_repoonse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Friend_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Jwt_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.action_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.group_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.info_repo;
import com.chatmrkhoi.chatmrkhoi.request.SignIn_request;
import com.chatmrkhoi.chatmrkhoi.request.SignUp_request;
import com.chatmrkhoi.chatmrkhoi.request.update_profile_request;
import com.chatmrkhoi.chatmrkhoi.security.custom_userdetail;
import com.chatmrkhoi.chatmrkhoi.security.generation_token;
import com.chatmrkhoi.chatmrkhoi.service.User_inter;

@Service
public class User_service implements User_inter {

	@Autowired
	User_repo user_repo;
	
	@Autowired
	SendGmail_service sendGmail_service;
	
	@Autowired
	generation_token generation_token;
	
	@Autowired
	Friend_repo friend_repo;
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	Jwt_service jwt_service;
	
	@Autowired
	Jwt_repo jwt_repo;
	
	@Autowired
	group_repo group_repo;
	
	@Autowired
	Info_service info_service;
	
	@Autowired
	info_repo info_repo;
	
	@Autowired
	action_repo action_repo;
	@Autowired
	Group_service group_service;
	
	
	private final PasswordEncoder encoder = new BCryptPasswordEncoder(); 
	
	
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
	
	// GET ROM FRIEND 
	public String getroom(Long id1, Long id2) {
		if (friend_repo.findbydoubleid(id2, id1).isPresent()) {
			return id1.toString() + id2.toString();
		} else {
			return 	id2.toString() + id1.toString();
		}
	}
	
	// CHECK STATUS FRIEND USER
	public String checkstatus(Set<Friend_entity> listfriend,Long idfriend) {
		status_fake = "not";
		listfriend.forEach((e) -> {
			if(e.getIdfriend() == idfriend) {
				status_fake = e.getStatus();
			}
		});
		return status_fake;
	}
	
	@Autowired
	mess_service mess_service;
	// GET ARRAY CONNECT USER AUHENTICATION
	public List<array_connect_reponse> get_array_connect(long myid) {
		List<array_connect_reponse> data_reponse = new ArrayList<array_connect_reponse>();
		Users_entity user = user_repo.findById(myid).get();
		   user.getFriend_entities().forEach((e) -> {
			   if(e.getStatus().equalsIgnoreCase("friend")) {
				   array_connect_reponse datax = array_connect_reponse.builder().coderoom(e.getCoderoom()).idfriend(e.getIdfriend()).time(e.getTime()).build();
				   data_reponse.add(datax);  
			   }
			});
		    friend_repo.findallbyiduser(myid).get().forEach((e) -> {
		    	 if(e.getStatus().equalsIgnoreCase("friend")) {
		    		 array_connect_reponse datax = array_connect_reponse.builder().coderoom(e.getCoderoom()).idfriend(e.getUsersentities().getId()).time(e.getTime()).build();
					   data_reponse.add(datax);  
				   }
		    });		    
		    user.getGrouarraymaster().forEach((e) -> {
		    	 array_connect_reponse datax = array_connect_reponse.builder().coderoom(e.getCoderoom()).idgroup(e.getId()).time(e.getTime()).build();
		    	 data_reponse.add(datax); 
		    });
		    user.getGroup_entities().forEach((e) -> {
		    	 array_connect_reponse datax = array_connect_reponse.builder().coderoom(e.getCoderoom()).idgroup(e.getId()).time(e.getTime()).build();
		    	 data_reponse.add(datax); 
		    });
		    Comparator<array_connect_reponse> data = new Comparator<array_connect_reponse>() {
				@Override
				public int compare(array_connect_reponse o1, array_connect_reponse o2) {
					return  Long.compare(o1.getTime(), o2.getTime());
				}
			};
			data_reponse.sort(data);
			
			// SẮP SẾP CONNECT THEO THỨ TỰ
			// NẾU KHÔNG CÓ TIN NHẮN SẾP THEO THỜI GIAN KẾT BẠN
			// NGƯỢC LẠI SẮP THEO THỜI GIAN TIN NHẮN CUỐI CỦA CUỘC TRÒ CHUYỆN
			List<array_connect_reponse> data_reponse_take = new ArrayList<array_connect_reponse>();
			List<array_connect_reponse> data_reponse_takes = new ArrayList<array_connect_reponse>();
		
					
			data_reponse.forEach((e) -> {
				if(mess_service.getmesscodes(e.getCoderoom()).size() != 0) {
					data_reponse_takes.add(e);	
				}else {
					data_reponse_take.add(e);
				}
			});
			
	
			 Comparator<array_connect_reponse> dataxc = new Comparator<array_connect_reponse>() {
					@Override
					public int compare(array_connect_reponse o1, array_connect_reponse o2) {
						return  
						 Long.compare(mess_service.getmesscodes(o1.getCoderoom()).get(mess_service.getmesscodes(o1.getCoderoom()).size() - 1).getTime()
						,mess_service.getmesscodes(o2.getCoderoom()).get(mess_service.getmesscodes(o2.getCoderoom()).size() - 1).getTime());
					}
				};
			data_reponse_takes.sort(dataxc);
				
				
			
				 Collections.reverse(data_reponse_takes);
				
			
			data_reponse_take.forEach((e) -> {
				data_reponse_takes.add(e);
			});
		
			return data_reponse_takes;
	}
	
	
	// Sign up ~ create user 
	Users_entity datacheck = null;
	@Override
	public void saveUser(SignUp_request signRequest) {
		Users_entity data = Users_entity
				.builder()
				.gmails(signRequest.getGmails())
				.passwords(encoder.encode(signRequest.getPasswords()))
				.verify(false)
				.build();	
		Optional<Users_entity> datavert = user_repo.findbygmail(signRequest.getGmails());
		datavert.ifPresentOrElse((e) -> {
			if(e.isVerify() == true) {
				user_repo.save(data);	
			} else {
				user_repo.update_ref(data.getGmails(), data.getPasswords());
				cakeinfo_entity info = info_repo.findbyiduser(e.getId()).get();
				info.setFullnames(signRequest.getFullname());
				info_service.update_info_user(info);
			}
		},() -> {
			datacheck = user_repo.save(data);
			info_service.create_info_user(signRequest.getFullname(), datacheck.getId());
		});
		// send email
		String token = generation_token.generation_token_verify(signRequest.getGmails());
		sendGmail_service.Send_verify_gmail(token, signRequest.getGmails());
	}

	
	@Override
	public boolean check_verify_token(String token) {
		if(generation_token.checktokengmail(token) != true) {
			return false;	
		} else {
			user_repo.update_verify(generation_token.getUsernameFromJWTverify(token));
			return true;
		}
	}
	
	
	@Override
	public ResponseEntity<SignIn_reponse> SignIn(SignIn_request signInrequest) {
		Optional<Users_entity> user = user_repo.findbygmail(signInrequest.getGmail());
		if(user.isEmpty() == true) {
			throw new SignIn_exception("Acount not exist", "Acount");
		}else if(user.get().isVerify() == false) {
			throw new SignIn_exception("Acount not verify", "Acount");
		}
		else if(encoder.matches(signInrequest.getPassword(),user_repo.findbygmail(signInrequest.getGmail()).get().getPasswords()) == false) {
			throw new SignIn_exception("Password incorrect", "Password");
		} else {
			Authentication author = manager.authenticate(new UsernamePasswordAuthenticationToken(signInrequest.getGmail(),signInrequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(author);
			 String token = generation_token.generation_token_verify(signInrequest.getGmail());
			 String refeshtoken = generation_token.generation_token_verify(signInrequest.getGmail());
			 Jwt_entity data = Jwt_entity.builder()
					 .id_user(user_repo.findbygmail(signInrequest.getGmail()).get().getId())
					 .jwts(refeshtoken)
					 .time(System.currentTimeMillis() + ( 24*60 *60 * 1000))
					 .build();
			 Optional<Jwt_entity> dataotp = jwt_repo.findbyjwt(user_repo.findbygmail(signInrequest.getGmail()).get().getId());
				dataotp.ifPresent((e) -> {
					data.setId(e.getId());
				});				
				
			 jwt_service.save(data);
			 SignIn_reponse data_reponse = new SignIn_reponse();
			 data_reponse.setToken(token);
			 data_reponse.setGmail(signInrequest.getGmail());
			 Optional<cakeinfo_entity> datainfo = info_repo.findbyiduser(user.get().getId());
			 datainfo.ifPresent((e) -> {
				 data_reponse.setFullname(e.getFullnames());
				 if(e.getAvatars() == null) {
					 data_reponse.setProfile("not");
				 }else {
					 data_reponse.setProfile("fish");
				 }
			 });		 
			 return ResponseEntity.status(200)
					 .body(data_reponse);
		}
	}
	
	
	
    // GET INFO USER AUTHENTICATION
	@Override
	public ResponseEntity<token_repoonse> getinfo(String token) {
		String gmail = generation_token.getUsernameFromJWTverify(token);
		Users_entity users = user_repo.findbygmail(gmail).get();
		cakeinfo_entity info = info_repo.findbyiduser(users.getId()).get();
		token_repoonse tokenreponse = token_repoonse.builder()
				.desc(info.getDescs())
				.fullname(info.getFullnames())
				.gmail(users.getGmails())
				.briday(info.getBirday())
				.gender(info.getGender())
				.images(info.getAvatars())
				.type_img(info.getType_avatas())
				.id(users.getId()).build();
		return ResponseEntity.status(200).body(tokenreponse);
	}

	
	// GET USER EMAIL REGISTER
	String status_fake = "not";	
	@Override
	public ResponseEntity<List<getfriend_reponse>> user(String gmail) {
		Users_entity my = user_authe();	
		List<getfriend_reponse> data_reponse = new ArrayList<getfriend_reponse>();
		Optional<Users_entity> user = user_repo.findbygmail(gmail);
		user.ifPresent(e -> {
			if (checkstatus(e.getFriend_entities(),my.getId()).equalsIgnoreCase("not")) {
				checkstatus(my.getFriend_entities(),e.getId());
			}
			if(mode_friend(e.getId(), status_fake, getroom(my.getId(), e.getId())) != null) {
				data_reponse.add(mode_friend(e.getId(), status_fake, getroom(my.getId(), e.getId())));
			} 
		});
		return ResponseEntity.status(200).body(data_reponse);
	}
	
	
	// GET USER NAME 
	@Override
	public ResponseEntity<List<getfriend_reponse>> user_name(String name) {
		// TODO Auto-generated method stub
		Users_entity my = user_authe();	
		List<getfriend_reponse> array = new ArrayList<getfriend_reponse>();
		user_repo.findAll().forEach((e) -> {
			cakeinfo_entity info = info_repo.findbyiduser(e.getId()).get();
			if(e.isVerify() == true && info.getAvatars() != null) {
				if (checkstatus(e.getFriend_entities(),my.getId()).equalsIgnoreCase("not")) {
					checkstatus(my.getFriend_entities(),e.getId());
				}
				if(mode_friend(e.getId(), status_fake, getroom(my.getId(), e.getId())) != null 
						&& info.getFullnames().toUpperCase().contains(name.toUpperCase()) == true
						&& e.getId() != my.getId()) {
					
					array.add(mode_friend(e.getId(), status_fake, getroom(my.getId(), e.getId())));
				} 
			}
		});
		return ResponseEntity.ok(array);
	}
	
	
	
	// RETURN LIST USER REQUEST FRIEND
	@Override
	public ResponseEntity<List<getfriend_reponse>> list_send_request() {
		 Long id = user_authe().getId();
		List<getfriend_reponse> data_reponse = new ArrayList<getfriend_reponse>();
		Users_entity user = user_repo.findById(id).get();
		   user.getFriend_entities().forEach((e) -> {
			   if(e.getStatus().equalsIgnoreCase("request")) {
				   data_reponse.add(mode_friend(e.getIdfriend(), e.getStatus(), e.getCoderoom()));  
			   }
			});
		return ResponseEntity.status(200).body(data_reponse);
	}
	
	
	
	
	// RETURN LIST USER SEND REQUEST FRIEND 
	@Override
	public ResponseEntity<List<getfriend_reponse>> list_friend_request() {
		Long id = user_authe().getId();
		List<getfriend_reponse> data_reponse = new ArrayList<getfriend_reponse>();
		friend_repo.findallbyiduser(id).get().forEach((e) -> {	
			if (e.getStatus().equalsIgnoreCase("request")) {
				 data_reponse.add(mode_friend(e.getUsersentities().getId(), e.getStatus(),e.getCoderoom()));	
			}
		});
		return ResponseEntity.status(200).body(data_reponse);
	}

	
	
	// RETURN LIST FRIEND USER AUTHENTICATED
	@Override
	public ResponseEntity<List<getfriend_reponse>> list_friend() {
		Long id = user_authe().getId();
		List<getfriend_reponse> data_reponse = get_friend(id);
		return ResponseEntity.status(200).body(data_reponse);
	}
	
	
	public List<getfriend_reponse> get_friend(Long id) {
		List<getfriend_reponse> data_reponse = new ArrayList<getfriend_reponse>();
		Users_entity user = user_repo.findById(id).get();
		 user.getFriend_entities().forEach((e) -> {
			   if(e.getStatus().equalsIgnoreCase("friend")) {
				   data_reponse.add(mode_friend(e.getIdfriend(), e.getStatus(), e.getCoderoom()));  
			   }
			});
		    friend_repo.findallbyiduser(id).get().forEach((e) -> {
		    	 if(e.getStatus().equalsIgnoreCase("friend")) {
					   data_reponse.add(mode_friend(e.getUsersentities().getId(), e.getStatus(), e.getCoderoom()));  
				   }
		    });
		   return data_reponse;
	}
	
	int count = 0;
	public int get_count_friend(Long id) {
		Users_entity user = user_repo.findById(id).get();
		count = user.getFriend_entities().size();
		count = count + friend_repo.findallbyiduser(id).get().size();
		   return count;
	}
	

	
	// RETURN LIST NO FRIEND USER AUTHENTICATRED
	@Override
	public ResponseEntity<List<getfriend_reponse>> list_not_friend() {	
		Long id = user_authe().getId();
		List<getfriend_reponse> data_reponse = new ArrayList<getfriend_reponse>();
		Users_entity user = user_repo.findById(id).get();
		
		user_repo.findAll().forEach((e) -> {
			if(checkexist(user.getFriend_entities(), e.getId()) == false) {
				if(mode_friend(e.getId(), "not", "") != null) {
					 data_reponse.add(mode_friend(e.getId(), "not", "")); 	
				}
			  }
		});
		return ResponseEntity.status(200).body(data_reponse);
	}
	
	
	boolean statuss = false;
	public boolean checkexist(Set<Friend_entity> listfriend,Long idfriend) {
		statuss = false;
		listfriend.forEach((e) -> {
			if(e.getIdfriend() == idfriend)  {
				statuss = true;
			}
		});
		return statuss;
	}

	@Override
	public ResponseEntity<List<array_connect_reponse>> array_connect() {
		Long id = user_authe().getId();
		return  ResponseEntity.status(200).body(get_array_connect(id));
	}


	@Override
	public ResponseEntity<List<getfriend_reponse>> getusergroup(Long idgroup) {
		// TODO Auto-generated method stub
		Group_entity group = group_repo.findById(idgroup).get();
		List<getfriend_reponse> datareponse = new ArrayList<getfriend_reponse>();
		group.getUsersentitiesx().forEach((e) -> {
			datareponse.add(convertuser(e, group.getCoderoom()));
		});
		return ResponseEntity.ok(datareponse);
	}
	
	
	public getfriend_reponse convertuser(Users_entity data, String coderrom) {
		cakeinfo_entity cakeinfo_entity = info_repo.findbyiduser(data.getId()).get();
	    getfriend_reponse datas = getfriend_reponse
	    		.builder().coderoom(coderrom)
	    		.fullname(cakeinfo_entity.getFullnames())
	    		.id(data.getId())
	    		.type_img(cakeinfo_entity.getType_avatas())
	    		.images(cakeinfo_entity.getAvatars())
	    		.build();
	 	return datas;
	}

	@Override
	public ResponseEntity<String> get_verify(String gmail) {
		String reponse = "";
		if(user_repo.findbygmail(gmail).get().isVerify() == true) {
			reponse = "verify";
		} else {
			reponse = "not verify";
		}
		return ResponseEntity.ok(reponse);
	}

	@Override
	public void get_verify_send(String gmail) {
		String token = generation_token.generation_token_verify(gmail);
		sendGmail_service.Send_verify_gmail(token, gmail);
	}

	@Override
	public ResponseEntity<token_repoonse> uploadprofile(update_profile_request data) {
		// TODO Auto-generated method stub
		cakeinfo_entity cake = info_repo.findbyiduser(user_authe().getId()).get();
		cake.setAvatars(data.getAvata());
		cake.setBirday(data.getBirday());
		cake.setDescs(data.getDesc());
		cake.setFullnames(data.getFullname());
		cake.setGender(data.getGender());
		cake.setType_avatas(data.getType_img());
		cakeinfo_entity cakenew = info_repo.save(cake);
	    token_repoonse x = token_repoonse.builder()
		.desc(cakenew.getDescs())
		.fullname(cakenew.getFullnames())
		.gender(cakenew.getGender())
		.gmail(user_authe().getGmails())
		.id(user_authe().getId())
		.images(cakenew.getAvatars())
		.type_img(cakenew.getType_avatas())
		.briday(cakenew.getBirday())
		.build();
		return ResponseEntity.ok(x);
	}





}
