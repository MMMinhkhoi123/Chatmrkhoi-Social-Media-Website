package com.chatmrkhoi.chatmrkhoi.reponse;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignIn_reponse {
	private String token;
	private String gmail;
	private String fullname;
	private String profile;
}
