package com.chatmrkhoi.chatmrkhoi.exception.cutomer;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExistException extends RuntimeException {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;

}