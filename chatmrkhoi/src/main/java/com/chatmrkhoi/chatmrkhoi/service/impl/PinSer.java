package com.chatmrkhoi.chatmrkhoi.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.chatmrkhoi.chatmrkhoi.Util.convert.MessageConverts;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.factorymethod.ACenterActivityMess;
import com.chatmrkhoi.chatmrkhoi.design.factorymethod.ConcretePinMessenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.entity.PinEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoMessPinRep;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessNewAndListInfoPinAndInfoPinNewRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IPinRepo;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSavePinReq;
import com.chatmrkhoi.chatmrkhoi.service.IPin;

@Service
public class PinSer implements IPin {

	@Autowired IUserRepo USER_REPO;
	@Autowired IMessageRepo MESSAGE_REPO;
	@Autowired MessageSer MESSAGE_SER;
	@Autowired IPinRepo PIN_REPO;
	@Autowired Common COMMON;
	@Autowired MessageConverts CONVERT;
    @Autowired
	ConcretePinMessenger factoryPinMessenger;

	@Override
	public ResponseEntity<DataMessNewAndListInfoPinAndInfoPinNewRep> save(DataSavePinReq data) {
		factoryPinMessenger.setData(data);
		factoryPinMessenger.setKey("ping");
		ACenterActivityMess factoryPing = factoryPinMessenger;
		PinEn pin = PIN_REPO.save((PinEn) factoryPing.setupObject());
		MessageEn messEntity = MESSAGE_REPO.save(factoryPing.getMessageNew());
		return ResponseEntity.ok(DataMessNewAndListInfoPinAndInfoPinNewRep
				.builder()
					.pin_chid(getConvertData(pin))
					.mess_reponse(CONVERT.convertMessRep(messEntity))
				.build());
	}


	@Override
	public ResponseEntity<DataMessNewAndListInfoPinAndInfoPinNewRep> delete(Long id) {
		factoryPinMessenger.setKey("unping");
		ACenterActivityMess factoryPing = factoryPinMessenger;
		MessageEn messEntity = MESSAGE_REPO.save(factoryPing.getMessageNew());
		PinEn pin = PIN_REPO.findById(id).orElseThrow();
		PIN_REPO.deleteById(pin.getId());
		return ResponseEntity.ok(DataMessNewAndListInfoPinAndInfoPinNewRep
				.builder()
				.mess_reponse(CONVERT.convertMessRep(messEntity))
				.build());
	}



	public DataInfoMessPinRep getConvertData(PinEn pin) {
		String rom  = pin.getMessentity().getGroupmess() != null
				? pin.getMessentity().getGroupmess().getCoderoom()
				: pin.getMessentity().getFriendmess().getCoderoom();
		return  DataInfoMessPinRep.builder()
				.id(pin.getId())
				.idmess(pin.getMessentity().getId())
				.iduser(pin.getUserEntity().getId())
				.time(pin.getTime())
				.coderoom(rom)
				.build();
	}



	@Override
	public ResponseEntity<List<DataInfoMessPinRep>> getAll() {
		List<DataInfoMessPinRep> ping = new ArrayList<DataInfoMessPinRep>();

		PIN_REPO.findAll().forEach((e) -> {
			String rom = e.getMessentity().getGroupmess() != null
					? e.getMessentity().getGroupmess().getCoderoom()
					:  e.getMessentity().getFriendmess().getCoderoom();

			DataInfoMessPinRep data = DataInfoMessPinRep
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
