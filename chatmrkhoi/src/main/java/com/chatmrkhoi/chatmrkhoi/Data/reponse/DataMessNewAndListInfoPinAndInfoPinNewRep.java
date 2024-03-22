package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DataMessNewAndListInfoPinAndInfoPinNewRep {
	DataMessageRep mess_reponse;
	List<DataInfoMessPinRep> pin_reponses;
	DataInfoMessPinRep pin_chid;
}
