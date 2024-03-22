package com.chatmrkhoi.chatmrkhoi.exception.cutomer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NotfoundException extends RuntimeException {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mess;
}
