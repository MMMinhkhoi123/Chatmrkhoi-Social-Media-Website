package com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignIn_exception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mess;
	private String type;

}
