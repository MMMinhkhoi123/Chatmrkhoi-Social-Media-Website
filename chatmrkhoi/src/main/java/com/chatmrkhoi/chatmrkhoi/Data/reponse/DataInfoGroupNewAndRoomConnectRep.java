package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataInfoGroupNewAndRoomConnectRep {
	private DataInfoRoomRep connect;
	private DataInfoGroupRep mygroup;
}
