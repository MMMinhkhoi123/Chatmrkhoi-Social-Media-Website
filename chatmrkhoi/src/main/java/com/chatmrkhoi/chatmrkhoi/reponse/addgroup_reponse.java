package com.chatmrkhoi.chatmrkhoi.reponse;

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
public class addgroup_reponse {
	private array_connect_reponse connect;
	private get_group_reponse mygroup;
}
