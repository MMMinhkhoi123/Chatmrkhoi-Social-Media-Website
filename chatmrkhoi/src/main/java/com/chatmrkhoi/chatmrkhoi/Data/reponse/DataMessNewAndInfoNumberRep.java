package com.chatmrkhoi.chatmrkhoi.Data.reponse;

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
public class DataMessNewAndInfoNumberRep {
	private DataMessageRep mess_reponse;
	private DataInfoUserOtherRep getfriend_reponse;
}
