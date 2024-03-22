package com.chatmrkhoi.chatmrkhoi.Data.reponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DataLoginRep {
	private String token;
	private String gmail;
	private String fullname;
	private String profile;
}
