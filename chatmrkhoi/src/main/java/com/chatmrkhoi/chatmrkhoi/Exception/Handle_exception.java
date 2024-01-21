
package com.chatmrkhoi.chatmrkhoi.Exception;

import java.sql.SQLIntegrityConstraintViolationException;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception.Notfound_exception;
import com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception.SignIn_exception;

@RestControllerAdvice
public class Handle_exception{
	
	@ExceptionHandler({ SQLIntegrityConstraintViolationException.class })
	public ResponseEntity<String> handleException_SignUp(SQLIntegrityConstraintViolationException e) {
		return ResponseEntity.status(500).body("Email already is used");
	}
	//handle timeout
		@ExceptionHandler({SignIn_exception.class })
		public  ResponseEntity<String>  handleException_timetoken(SignIn_exception e) {
			return ResponseEntity.status(401).body(e.getMess()); 
		}
	
	//handle timeout
	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> handleException_timetokens(Exception e) {
		return ResponseEntity.status(401).body("Minh kHoi");
	}
	
	
	//handle timeout
	@ExceptionHandler({Notfound_exception.class })
	public  ResponseEntity<String> notfound(Notfound_exception e) {
		return ResponseEntity.status(401).body(e.getMess());
	}
	
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<String> handleException_csrf(AccessDeniedException e) {
		return ResponseEntity.status(404).body("Not role");
	}

};
