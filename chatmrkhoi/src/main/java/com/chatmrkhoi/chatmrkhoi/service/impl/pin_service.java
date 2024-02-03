package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.chatmrkhoi.chatmrkhoi.design.Factory.AFactoryActionMess;
import com.chatmrkhoi.chatmrkhoi.design.Factory.FactoryPinMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.pin_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.pin_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.pindata_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.mess_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.pin_repo;
import com.chatmrkhoi.chatmrkhoi.request.addpin_request;
import com.chatmrkhoi.chatmrkhoi.service.pin_inter;

@Service
public class pin_service implements pin_inter {

	@Autowired User_repo user_repo;
	@Autowired mess_repo mess_repo;
	@Autowired mess_service mess_service;
	@Autowired pin_repo pin_repo;

	public Users_entity user_authe() {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Users_entity user = user_repo.findbygmail(userDetails.getUsername()).get();
		return user;
	}

	@Override
	public ResponseEntity<pindata_reponse> AddPingMessenger(addpin_request data) {
		AFactoryActionMess fatorypin = new FactoryPinMessenger();
		fatorypin.messEntity = mess_repo.findById(data.getIdmess()).get();
		fatorypin.usersEntity = user_authe();
		fatorypin.type = data.getType();
		fatorypin.key = "ping";

		pin_entity pin =  pin_repo.save((pin_entity) fatorypin.CreateActionMess());
		Mess_entity messEntity = mess_repo.save(fatorypin.MessNewRespon());

		return ResponseEntity.ok(pindata_reponse
				.builder()
					.pin_chid(getpinchild(pin))
					.mess_reponse(mess_service.convert_reponse(messEntity))
				.build());
	}

	@Override
	public ResponseEntity<pindata_reponse> RemovePingMessenger(Long id) {
		pin_entity pin = pin_repo.findById(id).get();
		AFactoryActionMess fatorypin = new FactoryPinMessenger();
		fatorypin.messEntity = pin.getMessentity();
		fatorypin.usersEntity = pin.getUserEntity();
		fatorypin.type = pin.getType();
		fatorypin.key = "unping";
		Mess_entity messEntity = mess_repo.save(fatorypin.MessNewRespon());
		pin_repo.deleteById(pin.getId());

		return ResponseEntity.ok(pindata_reponse
				.builder()
				.mess_reponse(mess_service.convert_reponse(messEntity))
				.build());
	}



	public  pin_reponse getpinchild(pin_entity pin) {
		String rom  = pin.getMessentity().getGroupmess() != null
				? pin.getMessentity().getGroupmess().getCoderoom()
				: pin.getMessentity().getFriendmess().getCoderoom();
		pin_reponse data =
				pin_reponse.builder()
				.id(pin.getId())
				.idmess(pin.getMessentity().getId())
				.iduser(pin.getUserEntity().getId())
				.time(pin.getTime())
				.coderoom(rom)
				.build();
		return  data;
	}



	@Override
	public ResponseEntity<List<pin_reponse>> GetAllPingMessenger() {
		List<pin_reponse> ping = new ArrayList<pin_reponse>();

		pin_repo.findAll().forEach((e) -> {
			String rom = e.getMessentity().getGroupmess() != null
					? e.getMessentity().getGroupmess().getCoderoom()
					:  e.getMessentity().getFriendmess().getCoderoom();

			pin_reponse data = pin_reponse
					.builder()
						.coderoom(rom)
						.id(e.getId())
						.type(e.getType())
						.idmess(e.getMessentity().getId())
						.iduser(e.getUserEntity().getId())
						.time(e.getTime())
					.build();

			ping.add(data);
		});
		
		return ResponseEntity.ok(ping);
	}
}
