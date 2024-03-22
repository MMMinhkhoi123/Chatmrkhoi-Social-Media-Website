package com.chatmrkhoi.chatmrkhoi.controller;

import com.chatmrkhoi.chatmrkhoi.service.impl.EmailSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatmrkhoi.chatmrkhoi.service.impl.UserSer;


@Controller
@RequestMapping("/gmail")
public class EmailController {
	
	@Autowired UserSer user_service;

	@Autowired
	EmailSer emailSer;
	
	@GetMapping("/verify")
	public String verify_email(@RequestParam("code") String code,Model model ) {
		if(user_service.checkValidToken(code)) {
			model.addAttribute("status", "Verify success");
		} else {
			model.addAttribute("status", "code past the deadline ");
		}
		return "verifysuccess";
	}

}
