package com.chatmrkhoi.chatmrkhoi;

import com.chatmrkhoi.chatmrkhoi.reponse.SignIn_reponse;
import com.chatmrkhoi.chatmrkhoi.request.SignIn_request;
import com.chatmrkhoi.chatmrkhoi.service.impl.User_service;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest // TẠO RA MỘT CONTEXT RIÊN ĐỂ CHỨA CÁC BEAN
//@SpringBootTest // LOAD TÌM CÁC CLASS CÓ CHỨA @SPRINGBOOTAPPLICATION TỪ ĐÓ TÌM VÀ NẠP TOÀN BỘ BEAN VÀO CONTEXT RIEN (NẶNG)
class ChatmrkhoiApplicationTests {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {
		@Bean
		public User_service employeeService() {
			return new User_service();
		}
	}

	private User_service employeeService;

	@Test
	void LoginAccount() {
		SignIn_request login = new SignIn_request();
		login.setGmail("khoinguyenminh188@gmail.com");
		login.setPassword("Minhkhoi@188");
		ResponseEntity<SignIn_reponse> data = employeeService.SignIn(login);
	}



}
