package com.chatmrkhoi.chatmrkhoi.reponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class addperson_reponse {
	private mess_reponse mess_reponse;
	private getfriend_reponse getfriend_reponse;
}
