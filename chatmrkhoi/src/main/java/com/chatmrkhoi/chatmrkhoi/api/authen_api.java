package com.chatmrkhoi.chatmrkhoi.api;

import java.util.Optional;

import com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception.Notfound_exception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import com.chatmrkhoi.chatmrkhoi.reponse.SignIn_reponse;
import com.chatmrkhoi.chatmrkhoi.request.SignIn_request;
import com.chatmrkhoi.chatmrkhoi.request.SignUp_request;
import com.chatmrkhoi.chatmrkhoi.service.impl.User_service;
import com.chatmrkhoi.chatmrkhoi.service.impl.verifycode_service;

@RestController
@RequestMapping("/authen")
public class authen_api {
	
	@Autowired User_service userservice;
	
	@Autowired verifycode_service verifycode_service;

	private static void NullData() {
		throw new Notfound_exception("Data is null");
	}


	@PostMapping("/SignUp")
	public void SignUp(
			@RequestBody Optional<SignUp_request> data) {
		data.ifPresentOrElse((DataValid) -> {
			userservice.saveUser(DataValid);
		} , authen_api::NullData);
	}

	@PostMapping("/SignIn")
	public ResponseEntity<SignIn_reponse> SignIn(
			@RequestBody Optional<SignIn_request> data) {
		return  userservice.SignIn(data.get());
	}

	@PostMapping("/verify_gmail")
	public ResponseEntity<String> GetStatusVerify(
			@Param(value = "gmail") Optional<String> gmail) {
		return userservice.get_verify(gmail.get());
	}
	@PostMapping("/verify_code")
	public void CreateCodeVerify(
			@Param(value = "gmail") Optional<String> gmail) {
	    verifycode_service.createverify(gmail.get());
	}
	@PostMapping("/verify_code_check")
	public ResponseEntity<String> CheckCodeVerify(
			@Param(value = "gmail") Optional<String> gmail,
			@Param(value = "code") Optional<String> code) {
	   return verifycode_service.check_code(gmail.get(),code.get());
	}
	@PostMapping("/change_pass")
	public void UpdatePassword(
			@Param(value = "gmail") Optional<String> gmail,
			@Param(value = "code") Optional<String> code) {
	   verifycode_service.chanepass(gmail.get(), code.get());
	}
	@PostMapping("/verify_gmail_send")
	public void SendEmailVerify(
			@Param(value = "gmail") Optional<String> gmail) {
		gmail.ifPresentOrElse((DataValid) -> {
			userservice.get_verify_send(DataValid);
		} , authen_api::NullData);
	}
	@GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }
}
