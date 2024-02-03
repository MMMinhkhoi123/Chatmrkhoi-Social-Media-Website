package com.chatmrkhoi.chatmrkhoi.reponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class addfeel_reponse {
	private mess_reponse messnew;
	private mess_reponse messupdate;
}
