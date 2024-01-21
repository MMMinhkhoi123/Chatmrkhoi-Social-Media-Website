package com.chatmrkhoi.chatmrkhoi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.chatmrkhoi.chatmrkhoi.service.SendGmail_inter;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SendGmail_service implements SendGmail_inter {

	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private TemplateEngine tyEngine;
	
	@Override
	public void Send_verify_gmail(String jwtoken, String to) {
		
		   Context ctx = new Context();
		  
	        ctx.setVariable("codeverify", jwtoken);
	        MimeMessage mess = mailsender.createMimeMessage();
	        try {      	
				MimeMessageHelper hepler = new MimeMessageHelper(mess, false, "UTF-8");	
				hepler.setTo(to);
				hepler.setSubject("Verify now");
				hepler.setText("Verify email now", false);
		        String htmlContent = tyEngine.process("Verify", ctx);
		        mess.setContent(htmlContent, "text/html; charset=utf-8");
		        hepler.setText(htmlContent, true); // true = isHtml
				mailsender.send(mess);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	}


	@Override
	public void Send_verify_gmail_code(String code, String to) {
		 Context ctx = new Context();
	        ctx.setVariable("text", code );
	        MimeMessage mess = mailsender.createMimeMessage();
	        try {      	
				MimeMessageHelper hepler = new MimeMessageHelper(mess, false, "UTF-8");	
				hepler.setTo(to);
				hepler.setSubject("code verify");
				hepler.setText("code verify", false);
		        String htmlContent = tyEngine.process("Verify_code", ctx);
		        mess.setContent(htmlContent, "text/html; charset=utf-8");
		        hepler.setText(htmlContent, true); // true = isHtml
				mailsender.send(mess);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	}

}
