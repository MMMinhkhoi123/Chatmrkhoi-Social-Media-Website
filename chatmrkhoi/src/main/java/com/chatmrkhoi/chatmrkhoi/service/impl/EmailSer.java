package com.chatmrkhoi.chatmrkhoi.service.impl;

import com.chatmrkhoi.chatmrkhoi.security.JwtToken;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.chatmrkhoi.chatmrkhoi.service.IEmail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSer implements IEmail {

	@Autowired private JavaMailSender mailSender;
	
	@Autowired private TemplateEngine templateEngine;

	@Autowired private JwtToken centerToken;
	
	@Override
	public void SendEmailVerify(String to) {
	    String Token = centerToken.getToken(to);
	    Context ctx = new Context();
		ctx.setVariable("token", Token);
		MimeMessage mess = mailSender.createMimeMessage();
		try {
			MimeMessageHelper Helper = new MimeMessageHelper(mess, false, "UTF-8");
			Helper.setTo(to);
			Helper.setSubject("VERIFY YOUR ACCOUNT");
			Helper.setText("VERIFY YOUR ACCOUNT", false);
			String htmlContent = templateEngine.process("Verify", ctx);
			mess.setContent(htmlContent, "text/html; charset=utf-8");
			Helper.setText(htmlContent, true);
			mailSender.send(mess);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}
	}


	@Override
	public void SendEmailCodeVerify(String code, String to) {
		Context ctx = new Context();
		ctx.setVariable("text", code );
		MimeMessage mess = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(mess, false, "UTF-8");
			helper.setTo(to);
			helper.setSubject("OBTAIN CODE VERIFY");
			helper.setText("OBTAIN CODE VERIFY", false);
			String htmlContent = templateEngine.process("Verify_code", ctx);
			mess.setContent(htmlContent, "text/html; charset=utf-8");
			helper.setText(htmlContent, true);
			mailSender.send(mess);
		} catch (MessagingException e) {
			System.out.println(e.getMessage());
		}
	}




}
