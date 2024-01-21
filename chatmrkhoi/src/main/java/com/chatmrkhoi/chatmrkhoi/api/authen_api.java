package com.chatmrkhoi.chatmrkhoi.api;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception.exists_Exception;
import com.chatmrkhoi.chatmrkhoi.entity.verifycode_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.SignIn_reponse;
import com.chatmrkhoi.chatmrkhoi.reponse.token_repoonse;
import com.chatmrkhoi.chatmrkhoi.request.SignIn_request;
import com.chatmrkhoi.chatmrkhoi.request.SignUp_request;
import com.chatmrkhoi.chatmrkhoi.service.impl.File_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.SendGmail_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.User_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.verifycode_service;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/authen")
public class authen_api {
	
	@Autowired
	User_service userservice;
	
	@Autowired
	verifycode_service verifycode_service;
	
	@PostMapping("/SignUp")
	public void SignUp(@RequestBody Optional<SignUp_request> data) {
		userservice.saveUser(data.get());
	}
	
	@PostMapping("/SignIn")
	public ResponseEntity<SignIn_reponse> SignIn(@RequestBody Optional<SignIn_request> data) {
		return userservice.SignIn(data.get());
	}

	@GetMapping("/signingoole")
	public Object currentuser(OAuth2AuthenticationToken oAuth2AuthenticationToken) {
		return oAuth2AuthenticationToken.getPrincipal().getAttributes();
	}

	@PostMapping("/verify_gmail")
	public ResponseEntity<String> verify(@Param(value = "gmail") Optional<String> gmail) {
		return userservice.get_verify(gmail.get());
	}
	
	@PostMapping("/verify_code")
	public void verify_code(@Param(value = "gmail") Optional<String> gmail) {
	    verifycode_service.createverify(gmail.get());
	}
	
	
	@PostMapping("/verify_code_check")
	public ResponseEntity<String> verify_code_check(@Param(value = "gmail") Optional<String> gmail,
			@Param(value = "code") Optional<String> code) {
	   return verifycode_service.check_code(gmail.get(),code.get());
	}
	
	
	@PostMapping("/change_pass")
	public void changepass(@Param(value = "gmail") Optional<String> gmail,
			@Param(value = "code") Optional<String> code) {
	   verifycode_service.chanepass(gmail.get(), code.get());
	}
	
	@PostMapping("/verify_gmail_send")
	public void sendverify(@Param(value = "gmail") Optional<String> gmail) {
	   userservice.get_verify_send(gmail.get());
	}

    
	@GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }
}
