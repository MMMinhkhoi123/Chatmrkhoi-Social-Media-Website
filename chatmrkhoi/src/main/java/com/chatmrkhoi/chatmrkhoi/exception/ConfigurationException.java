
package com.chatmrkhoi.chatmrkhoi.exception;

import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.chatmrkhoi.chatmrkhoi.exception.cutomer.NotfoundException;
import com.chatmrkhoi.chatmrkhoi.exception.cutomer.LoginException;

@RestControllerAdvice
public class ConfigurationException {
	
	@ExceptionHandler({ SQLIntegrityConstraintViolationException.class })
	public ResponseEntity<String> getStatusEmailExist(SQLIntegrityConstraintViolationException e) {
		return ResponseEntity.status(500).body("Email already is used");
	}
	@ExceptionHandler({LoginException.class })
	public  ResponseEntity<String> getStatusLogin(LoginException e) {
		return ResponseEntity.status(401).body(e.getMess());
	}
	@ExceptionHandler({ AuthenticationException.class })
	public ResponseEntity<String> getInvalidToken(Exception e) {
		return ResponseEntity.status(401).body("Time invalid");
	}
	@ExceptionHandler({NotfoundException.class })
	public  ResponseEntity<String> notfound(NotfoundException e) {
		return ResponseEntity.status(401).body(e.getMess());
	}
	@ExceptionHandler({ AccessDeniedException.class })
	public ResponseEntity<String> getStatusNotNull(AccessDeniedException e) {
		return ResponseEntity.status(404).body("Not role");
	}

};
