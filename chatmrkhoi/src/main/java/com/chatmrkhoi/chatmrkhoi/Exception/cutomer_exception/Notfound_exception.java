package com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Notfound_exception extends RuntimeException {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mess;
}
