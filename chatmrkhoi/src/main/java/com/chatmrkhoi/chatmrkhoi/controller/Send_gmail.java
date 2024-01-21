package com.chatmrkhoi.chatmrkhoi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chatmrkhoi.chatmrkhoi.service.impl.User_service;


@Controller
@RequestMapping("/gmail")
public class Send_gmail {
	
	@Autowired
	User_service user_service;
	
	@GetMapping("/verify")
	public String verify_email(@RequestParam("code") String code,Model model ) {
		if(user_service.check_verify_token(code) == true) {
			model.addAttribute("status", "Veryfy success");
		} else {
			model.addAttribute("status", "code past the deadline ");
		}
		return "verifysuccess";
	}
	
	
	
	@GetMapping("/tesss")
	public String verify_emails(Model model ) {
		model.addAttribute("text", "099s99");
		return "Verify_code";
	}
}
