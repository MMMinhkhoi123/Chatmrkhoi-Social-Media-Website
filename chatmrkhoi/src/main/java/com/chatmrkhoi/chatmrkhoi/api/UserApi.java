package com.chatmrkhoi.chatmrkhoi.api;

import java.util.List;
import java.util.Optional;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoRoomRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoUserOtherRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoAuthenRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataInfoUpdateReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.UserSer;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user-center")
public class UserApi {

	@Autowired UserSer USER_SER;

	@PostMapping("/jwt")
    public ResponseEntity<DataInfoAuthenRep> token(HttpServletRequest request) {
		  String token = "";
    	  String bearerToken = request.getHeader("Authorization");
    	 if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
    		 token = bearerToken.substring(7, bearerToken.length());
         }
    	return USER_SER.getInfo(token);
    }
	@GetMapping("/list-suggest")
	public ResponseEntity<List<DataInfoUserOtherRep>> getListUserSugFriend() {
		return USER_SER.getListSugFiend();
	}

	@GetMapping("/array-connect")
	public ResponseEntity<List<DataInfoRoomRep>> getArrayRoomConnect() {
		return USER_SER.getListRoomChat();
	}
	@GetMapping("/groupid/{id}")
	public ResponseEntity<List<DataInfoUserOtherRep>> getUserFromGroupId(@PathVariable(value = "id") Optional<Long> id) {
		return USER_SER.getusergroup(id.orElseThrow());
	}
	@PutMapping("/profile")
	public ResponseEntity<DataInfoAuthenRep> update_profile(@RequestBody Optional<DataInfoUpdateReq> data) {
		return USER_SER.updateInfo(data.orElseThrow());
	}

	@GetMapping("/list-initial/{type}")
	public ResponseEntity<List<DataInfoUserOtherRep>> getListNotFriend(@PathVariable(value = "type") Optional<String> type) {
		return USER_SER.getAllDataInitialByType(type.orElseThrow());
	}

	@GetMapping("/find-user/{key}/{data}")
	public ResponseEntity<List<DataInfoUserOtherRep>> getUserByEmail(
			@PathVariable(value = "key") Optional<String> key,
			@PathVariable(value = "data") Optional<String> data) {
		return USER_SER.findAllByType(key.orElseThrow(), data.orElseThrow());
	}
}
