package com.chatmrkhoi.chatmrkhoi.api;

import java.util.Optional;
import com.chatmrkhoi.chatmrkhoi.service.impl.EmailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataLoginRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataLoginReq;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataRegisterReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.UserSer;
import com.chatmrkhoi.chatmrkhoi.service.impl.CodeSer;

@RestController
@RequestMapping("/account")
public class AccountApi {

	@Autowired private UserSer USER_SER;
	@Autowired private EmailSer EMAIL_SER;
	@Autowired private CodeSer CODE_SER;

	@PostMapping("/register")
	public void SignUp(
			@RequestBody Optional<DataRegisterReq> DataRegister) {
			USER_SER.save(DataRegister.orElseThrow());
	}
	@PostMapping("/login")
	public ResponseEntity<DataLoginRep> SignIn (
			@RequestBody Optional<DataLoginReq> LoginReq) {
			return USER_SER.login(LoginReq.orElseThrow());
	}
	@PostMapping("/verify_gmail")
	public ResponseEntity<String> GetStatusVerify(
			@Param(value = "gmail") Optional<String> gmail) {
		return USER_SER.getStatusVerify(gmail.orElseThrow());
	}

	@PostMapping("/verify_code")
	public void SendEmailCodeVerify(
		@Param(value = "gmail") Optional<String> gmail) {
		EMAIL_SER.SendEmailCodeVerify(String.valueOf(CODE_SER.getCodeVerify(gmail.orElseThrow())), gmail.orElseThrow());
	}


	@PostMapping("/verify_code_check")
	public ResponseEntity<String> CheckCodeVerify(
			@Param(value = "gmail") Optional<String> gmail,
			@Param(value = "code") Optional<String> code) {
	   return CODE_SER.getStatusVerifyCode(gmail.orElseThrow(),code.orElseThrow());
	}


	@PostMapping("/change_pass")
	public void UpdatePassword(
			@Param(value = "gmail") Optional<String> gmail,
			@Param(value = "code") Optional<String> code) {
	   CODE_SER.updatePassword(gmail.orElseThrow(), code.orElseThrow());
	}


	@GetMapping("/csrf")
    public CsrfToken csrf(CsrfToken csrfToken) {
        return csrfToken;
    }
	@PostMapping("/verify_gmail_send")
	public void SendEmailVerify(
			@Param(value = "gmail") Optional<String> email) {
		    EMAIL_SER.SendEmailVerify(email.orElseThrow());
	}

}
