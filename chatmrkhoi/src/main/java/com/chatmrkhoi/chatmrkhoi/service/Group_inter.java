package com.chatmrkhoi.chatmrkhoi.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.chatmrkhoi.chatmrkhoi.reponse.addgroup_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.addperson_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.array_connect_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.get_group_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.request.addgroup_request;
import com.chatmrkhoi.chatmrkhoi.request.adperson_request;
import com.chatmrkhoi.chatmrkhoi.request.namegroup_request;
import com.chatmrkhoi.chatmrkhoi.request.update_img_request;

public interface Group_inter {
	
	public ResponseEntity<addgroup_reponse> addgroup(addgroup_request data);
	
	public ResponseEntity<get_group_reponse> updatenamegroup(namegroup_request data);
	
	public ResponseEntity<get_group_reponse> updateimggroup(update_img_request data);
	
	public ResponseEntity<List<get_group_reponse>> group();
	
	public void deletegrop(Long id);

	public ResponseEntity<mess_reponse> outgroup(Long idgroup, String coderoom);
	
	public ResponseEntity<addperson_reponse> addperson(adperson_request data);
	
	public ResponseEntity<addperson_reponse> kickperson(adperson_request data);
}
