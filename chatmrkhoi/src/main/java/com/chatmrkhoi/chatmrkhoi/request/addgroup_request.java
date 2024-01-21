package com.chatmrkhoi.chatmrkhoi.request;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class addgroup_request {
	private Long idimg;
	private String namegroup;
	private String coderoom;
	private List<Long>  arrayperson;
}
