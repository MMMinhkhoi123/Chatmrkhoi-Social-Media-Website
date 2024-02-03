package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.chatmrkhoi.chatmrkhoi.entity.Group_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.cakeinfo_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.addgroup_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.addperson_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.get_group_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.getfriend_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Watch_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.feel_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.file_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.group_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.info_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.mess_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.pin_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.revoke_repo;
import com.chatmrkhoi.chatmrkhoi.request.addgroup_request;
import com.chatmrkhoi.chatmrkhoi.request.adperson_request;
import com.chatmrkhoi.chatmrkhoi.request.namegroup_request;
import com.chatmrkhoi.chatmrkhoi.request.update_img_request;
import com.chatmrkhoi.chatmrkhoi.service.Group_inter;


@Service
public class Group_service implements Group_inter {

	@Autowired
	file_repo file_repo;
	
	@Autowired
	User_repo user_repo;
	
	@Autowired
	group_repo group_repo;
	
	@Autowired
	mess_repo mess_repo;
	
	@Autowired
	mess_service mess_service;
	
	@Autowired
	info_repo info_repo;
	
	@Autowired
	feel_repo feel_repo;
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
	public ResponseEntity<addgroup_reponse> addgroup(addgroup_request data) {
		String img = null;
		if(data.getIdimg() != null) {
		img = file_repo.findById(data.getIdimg()).get().getNamefile();
		}
		Group_entity datax = Group_entity.builder().avata(img)
				.name(data.getNamegroup())
				.userEntity(user_authe())
				.usersentitiesx(getusergroup(data.getArrayperson()))
				.coderoom(data.getCoderoom())
				.time(new Date().getTime())
				.build();
		 Group_entity datacx = group_repo.save(datax);
		 datacx.setCoderoom(datacx.getId() + "GR" + datacx.getCoderoom());
		 group_repo.save(datacx);
		 
	 array_connect_reponse datanini = array_connect_reponse.builder()
			 .idgroup(datacx.getId())
			 .coderoom(datacx.getCoderoom())
			 .time(datax.getTime())
			 .build();
	 
	 get_group_reponse mygroup = get_group_reponse.builder()
			 .coderoom(datanini.getCoderoom())
			 .id(datacx.getId())
			 .img(datacx.getAvata())
			 .master(datacx.getUserEntity().getId())
			 .name(datacx.getName()).build();
	 addgroup_reponse data_reponse = addgroup_reponse.builder().connect(datanini).mygroup(mygroup).build();
	  return ResponseEntity.ok(data_reponse);
	}
	
	
	
	
	
	public Set<Users_entity> getusergroup(List<Long> array) {
		Set<Users_entity> arrays = new HashSet<Users_entity>();
		array.forEach((e) -> {
			arrays.add(user_repo.findById(e).get());
		});
		return arrays;
	}

	@Override
	public ResponseEntity<List<get_group_reponse>> group() {
		
	  Users_entity user = user_repo.findById(user_authe().getId()).get();
	  List<get_group_reponse> array_reponse = new ArrayList<get_group_reponse>();
	  user.getGrouarraymaster().forEach((e) -> {
		  get_group_reponse data = get_group_reponse.builder().count(countgroup(e)).id(e.getId()).img(e.getAvata()).coderoom(e.getCoderoom()).name(e.getName()).master(e.getUserEntity().getId()).build();
		  array_reponse.add(data);
	  });
	  user.getGroup_entities().forEach((e) -> {
		  get_group_reponse data = get_group_reponse.builder().count(countgroup(e)).id(e.getId()).img(e.getAvata()).coderoom(e.getCoderoom()).name(e.getName()).master(e.getUserEntity().getId()).build();
		  array_reponse.add(data);
	  });;
		return ResponseEntity.status(200).body(array_reponse);
	}
	
	public int countgroup(Group_entity group) {
		List<getfriend_reponse> datareponse = new ArrayList<getfriend_reponse>();
		group.getUsersentitiesx().forEach((e) -> {
			datareponse.add(convertuser(e, group.getCoderoom()));
		});
		return datareponse.size();
	}


	@Override
	public ResponseEntity<get_group_reponse> updatenamegroup(namegroup_request data) {
		Group_entity groupnew = group_repo.findById(data.getId()).get();
		groupnew.setName(data.getName());
		Group_entity gropnews = group_repo.save(groupnew);
		get_group_reponse datam = get_group_reponse.builder()
				.coderoom(gropnews.getCoderoom())
				.id(gropnews.getId())
				.img(gropnews.getAvata())
				.master(gropnews.getUserEntity().getId())
				.name(gropnews.getName())
				.build();
		return ResponseEntity.ok(datam);
	}


	String name ;
	@Override
	public ResponseEntity<get_group_reponse> updateimggroup(update_img_request data) {
		// TODO Auto-generated method stub
	     Group_entity groupnew = group_repo.findById(data.getId()).get();
			     if(data.getFile() != null) {
			    	 File file_one = new File("");
					 String currentDirectory_img = file_one.getAbsolutePath() + convertpath("\\target\\classes\\static\\img\\");	
					  String  namex =  user_authe().getId().toString() + "&3&" + data.getFile().getOriginalFilename();
				       name = convertfilename(currentDirectory_img, namex);
				       Path  paths = Paths.get(currentDirectory_img + name);
				       Path  paths2 = Paths.get(currentDirectory_img + groupnew.getAvata());   
					     try {
//					    	  Files.delete(paths2);
							  Files.copy(data.getFile().getInputStream(),paths,StandardCopyOption.REPLACE_EXISTING);
							} catch (IOException e) {
								// TODO Auto-generated catch block
							  e.printStackTrace();
							}
							groupnew.setAvata(name);
			     }
			     if(data.getName() != null) {
			    	 groupnew.setName(data.getName());
			     }
				Group_entity gropnews = group_repo.save(groupnew);
				get_group_reponse datam = get_group_reponse.builder()
						.coderoom(gropnews.getCoderoom())
						.id(gropnews.getId())
						.img(gropnews.getAvata())
						.master(gropnews.getUserEntity().getId())
						.name(gropnews.getName())
						.build();
		return ResponseEntity.ok(datam);
	}

	int i;
	public String convertfilename(String url, String name) {
		i = 0;
		File file  = new File(url + i +  "&3&" + name );
		while (file.exists() == true) {
			i++;
			file  = new File(url + i + "&3&" + name);
		}
		return   i + "&3&" + name;
	}
	
	// convert url 
	String vulue = "";
	public String convertpath(String path) {
		vulue = "";
		char [] arrays = path.toCharArray();
		for (int i = 0; i < arrays.length ; i++) {
			if(arrays[i] == '/') {
				vulue = vulue +  '/';
			}
			vulue = vulue + arrays[i];
		}
		return vulue;
	}
	
	
	
	@Override
	public ResponseEntity<addperson_reponse> addperson(adperson_request data) {
		Group_entity group_entity = group_repo.findById(data.getId()).get();
		
		group_entity.setCoderoom(group_entity.getCoderoom()+ "&"+ data.getIdfriend());
		Set<Users_entity> user = group_entity.getUsersentitiesx();
		user.add(user_repo.findById(data.getIdfriend()).get());
		group_repo.save(group_entity);
		// return mess add person
		Mess_entity mess = Mess_entity.builder()
				.usersentity(user_authe())
				.content("")
				.time(System.currentTimeMillis())
				.groupmess(group_repo.findById(data.getId()).get())
				.addgroup( "add&" + data.getIdfriend())
				.build();
		Mess_entity m =  mess_repo.save(mess);
		
		 mess_reponse datax = mess_service.convert_reponse(m);
		 getfriend_reponse datay = convertuser(user_repo.findById(data.getIdfriend()).get(), group_entity.getCoderoom());
		// return array person forgroup
		 addperson_reponse mata = addperson_reponse.builder().mess_reponse(datax).getfriend_reponse(datay).build();
		 return ResponseEntity.ok(mata);
	}
	
	
	public getfriend_reponse convertuser(Users_entity data, String coderrom) {
	   cakeinfo_entity cake = info_repo.findbyiduser(data.getId()).get();
	    getfriend_reponse datas = getfriend_reponse
	    		.builder().coderoom(coderrom)
	    		.fullname(cake.getFullnames())
	    		.id(data.getId())
	    		.images(cake.getAvatars())
	    		.type_img(cake.getType_avatas())
	    		.build();
	 	return datas;
	}

	@Override
	public ResponseEntity<addperson_reponse> kickperson(adperson_request data) {
		// delete person out group
		Set<Users_entity> datax = new HashSet<Users_entity>();
		group_repo.findById(data.getId()).get().getUsersentitiesx().forEach((e) -> {
			if(e.getId() != data.getIdfriend()) {
				datax.add(e);
			}
		} );
		Group_entity group =  group_repo.findById(data.getId()).get();
		group.setUsersentitiesx(datax);
		// Update code group
		group.setCoderoom(data.getCodenew());
		group_repo.save(group);
		
		// Add mess kich 
		Mess_entity mess = Mess_entity.builder()
				.usersentity(user_authe())
				.content("")
				.time(System.currentTimeMillis())
				.groupmess(group_repo.findById(data.getId()).get())
				.addgroup( "kich&" + data.getIdfriend())
				.build();
		mess_repo.save(mess);
		
	    // Return mess new
		 mess_reponse messnew = mess_service.convert_reponse(mess);
		 addperson_reponse mata = addperson_reponse.builder().mess_reponse(messnew).build();

		 return ResponseEntity.ok(mata);
	}

	@Override
	public void deletegrop(Long id) {
		Group_entity group = group_repo.findById(id).get();
		group.getMess_entity().forEach((e) -> {
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
		group.setUsersentitiesx(null);
//		file_service.deletefile(group.getAvata(),"img");
	   group_repo.deleteById(id);
	}

	
	@Override
	public ResponseEntity<mess_reponse> outgroup(Long idgroup, String code) {
		// TODO Auto-generated method stub
		
		Group_entity group = group_repo.findById(idgroup).get();
		Set<Users_entity> array = new HashSet<Users_entity>();
		group.getUsersentitiesx().forEach((e) -> {
			if(e.getId() != user_authe().getId()) {
				array.add(e);
			}
		});
		group.setCoderoom(code);
		group.setUsersentitiesx(array);
		group_repo.save(group);
		
		Mess_entity mess = Mess_entity.builder()
				.addgroup("out&" + user_authe().getId().toString())
				.time(System.currentTimeMillis())
				.start_date(false)
				.groupmess(group)
				.content("")
				.usersentity(user_authe())
				.status(false)
				.build();
		Mess_entity messx = mess_repo.save(mess);
		mess_reponse messrp = mess_reponse.builder()
				.room(code).id(messx.getId())
				.id_user(messx.getUsersentity().getId())
				.id_group(messx.getGroupmess().getId())
				.time(messx.getTime())
				.group_status(messx.getAddgroup()).build();
		return ResponseEntity.ok(messrp);
	}

}
