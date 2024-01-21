package com.chatmrkhoi.chatmrkhoi.api;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.chatmrkhoi.chatmrkhoi.reponse.action_user;
import com.chatmrkhoi.chatmrkhoi.reponse.addfeel_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.addgroup_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.addperson_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.datadetail_response;
import com.chatmrkhoi.chatmrkhoi.reponse.filedetail_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.get_group_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.getfriend_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pin_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pindata_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.token_repoonse;
import com.chatmrkhoi.chatmrkhoi.request.Friend_request;
import com.chatmrkhoi.chatmrkhoi.request.addgroup_request;
import com.chatmrkhoi.chatmrkhoi.request.addpin_request;
import com.chatmrkhoi.chatmrkhoi.request.adperson_request;
import com.chatmrkhoi.chatmrkhoi.request.destroy_request;
import com.chatmrkhoi.chatmrkhoi.request.file_reuqest;
import com.chatmrkhoi.chatmrkhoi.request.finduser_request;
import com.chatmrkhoi.chatmrkhoi.request.getfriend_request;
import com.chatmrkhoi.chatmrkhoi.request.namegroup_request;
import com.chatmrkhoi.chatmrkhoi.request.sendmess_request;
import com.chatmrkhoi.chatmrkhoi.request.sendmove_request;
import com.chatmrkhoi.chatmrkhoi.request.unmess_request;
import com.chatmrkhoi.chatmrkhoi.request.update_profile_request;
import com.chatmrkhoi.chatmrkhoi.request.updatefeel_request;
import com.chatmrkhoi.chatmrkhoi.request.upinfo_full_request;
import com.chatmrkhoi.chatmrkhoi.request.watch_request;
import com.chatmrkhoi.chatmrkhoi.service.impl.File_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.Friend_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.Group_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.Info_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.User_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.action_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.feel_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.mess_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.pin_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.revoke_vervice;
import com.chatmrkhoi.chatmrkhoi.service.impl.watch_service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@CrossOrigin(origins = { "http://localhost:5173" }, methods = { 
		RequestMethod.POST, RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT
} )
@RestController
@RequestMapping("/user")
public class user_api {
	
	@Autowired
	watch_service watch_service;
	
	@Autowired
	User_service user_service;
	
	@Autowired
	Friend_service friend_service;
	
	@Autowired
	mess_service mess_service;
	
	@Autowired
	Group_service group_service;
	
	@Autowired
	feel_service feel_service;
	
	@Autowired
	pin_service pin_service;
	
	@Autowired
	Info_service info_service;
	
	@Autowired
	File_service file_service;
	
	@Autowired
	revoke_vervice revoke_vervice;
	
	@Autowired
	action_service action_service;
	
	
	@PostMapping("/jwt")
    public ResponseEntity<token_repoonse> token(HttpServletRequest request) {
    	  String token = "";
    	  String bearerToken = request.getHeader("Authorization");
    	 if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
    		 token = bearerToken.substring(7, bearerToken.length());
         }
    	return user_service.getinfo(token);
    }
    @PostMapping("/watch")
	public ResponseEntity<mess_reponse> addwatch(@RequestBody Optional<watch_request> data) {
	  return watch_service.watchmess(data.get());
	}
    
	@PostMapping("/sendmess")
	public ResponseEntity<List<mess_reponse>> sendmess(@RequestBody Optional<sendmess_request> data) {
	  return mess_service.sendmess(data.get());
	}
	
	@GetMapping("/actionall")
	public ResponseEntity<List<action_user>> action() {
	  return action_service.getalls();
	}
	
	@GetMapping("/messall")
	public ResponseEntity<List<mess_reponse>> getmess() {
		return mess_service.getmess();
	}
	
	@PostMapping("/group")
	public ResponseEntity<addgroup_reponse> addgroup(@RequestBody Optional<addgroup_request> data) {
	   return group_service.addgroup(data.get());
	}
	
	@DeleteMapping("/group/{id}")
	public void deletegroup(@PathVariable(value = "id") Optional<Long> id) {
	   group_service.deletegrop(id.get());
	}
	
	@PostMapping("/out-group/{id}/{code}")
	public ResponseEntity<mess_reponse> outgroup(@PathVariable(value = "id") Optional<Long> id,@PathVariable(value = "code") Optional<String> code) {
		return group_service.outgroup(id.get(), code.get());
	}
	
	@GetMapping("/data-detail/{room}")
	public ResponseEntity<datadetail_response> detail(@PathVariable(value = "room") Optional<String> room) {
		return file_service.data_detail(room.get());
	}
	
	@GetMapping("/data-detail-zoom/{room}")
	public ResponseEntity<List<filedetail_reponse>> detailzoom(@PathVariable(value = "room") Optional<String> room) {
		return file_service.data_detail_zoom(room.get());
	}
	
	@GetMapping("/mygroup")
	public ResponseEntity<List<get_group_reponse>> get_group() {
		return group_service.group();
	}
	
	@GetMapping("/groupid/{id}")
	public ResponseEntity<List<getfriend_reponse>> getgroupid(@PathVariable(value = "id") Optional<Long> id) {
		return user_service.getusergroup(id.get());
	}
	
	@PostMapping("/feel")
	public ResponseEntity<addfeel_reponse> addfeel(@RequestBody Optional<updatefeel_request> data) {
	  return feel_service.add_feel(data.get());
	}
	@DeleteMapping("/feel/{id}/{type}")
	public ResponseEntity<mess_reponse> deletefeel(@PathVariable("id") Optional<Long> id,@PathVariable("type") Optional<String> type) {
	  return feel_service.delete_feel(id.get(), type.get());
	}
	
	@PostMapping("/unmess")
	public ResponseEntity<mess_reponse> unmess(@RequestBody Optional<unmess_request> data) {
	  return revoke_vervice.addunmess(data.get());
	}
	
	@PostMapping("/pin")
	public ResponseEntity<pindata_reponse> addpin(@RequestBody Optional<addpin_request> data) {
	  return pin_service.addping(data.get());
	}
	
	
	@DeleteMapping("/pin-del/{id}")
	public ResponseEntity<pindata_reponse> getpin(@PathVariable("id") Optional<Long> id) {
	  return pin_service.deletepin(id.get());
	}
	

	@PostMapping("/move")
	public ResponseEntity<mess_reponse> addmove(@RequestBody Optional<sendmove_request> data) {
		return mess_service.movemess(data.get());
	}
	
	@PostMapping("/update-info")
	public void updateprofile(@RequestBody Optional<upinfo_full_request> datas) {
		info_service.update_info_user_full(datas.get());
	}
	
	@PutMapping("/group-name")
	public ResponseEntity<get_group_reponse> groupname(@RequestBody Optional<namegroup_request> data) {
		return group_service.updatenamegroup(data.get());
	}
	
	@PostMapping("/group-person")
	public ResponseEntity<addperson_reponse> addperson(@RequestBody Optional<adperson_request> data) {
		return group_service.addperson(data.get());
	}
	
	@PostMapping("/group-person-kich")
	public ResponseEntity<addperson_reponse> kickperson(@RequestBody Optional<adperson_request> data) {
	   return group_service.kickperson(data.get());
	}
	
	
	@PutMapping("/profile")
	public ResponseEntity<token_repoonse> update_profile(@RequestBody Optional<update_profile_request> data) {
		return user_service.uploadprofile(data.get());
	}
}
