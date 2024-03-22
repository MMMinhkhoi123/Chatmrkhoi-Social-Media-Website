package com.chatmrkhoi.chatmrkhoi;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataLoginRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataLoginReq;
import com.chatmrkhoi.chatmrkhoi.service.impl.UserSer;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;

@SpringBootTest // TẠO RA MỘT CONTEXT RIÊN ĐỂ CHỨA CÁC BEAN
//@SpringBootTest // LOAD TÌM CÁC CLASS CÓ CHỨA @SPRINGBOOTAPPLICATION TỪ ĐÓ TÌM VÀ NẠP TOÀN BỘ BEAN VÀO CONTEXT RIEN (NẶNG)
class ChatmrkhoiApplicationTests {

	@TestConfiguration
	static class EmployeeServiceImplTestContextConfiguration {
		@Bean
		public UserSer employeeService() {
			return new UserSer();
		}
	}

	private UserSer employeeService;

	@Test
	void LoginAccount() {
		DataLoginReq login = new DataLoginReq();
		login.setGmail("khoinguyenminh188@gmail.com");
		login.setPassword("Minhkhoi@188");
		ResponseEntity<DataLoginRep> data = employeeService.login(login);
	}



}
