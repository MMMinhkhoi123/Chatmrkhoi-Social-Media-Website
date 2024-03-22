package com.chatmrkhoi.chatmrkhoi.design.observer;

import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IGroupRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import lombok.Data;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.*;

@Service
@Setter
public class NotifyEmail implements ISubscriber{

    @Autowired private JavaMailSender mailSender;
    @Autowired private TemplateEngine templateEngine;
    @Autowired private IInfoRepo INFO_REPO;
    @Autowired private Common COMMON_REPO;
    @Autowired private IGroupRepo GROUP_REPO;
    @Autowired private IUserRepo USER_REPO;
    private DataSaveMessReq data;
    private String[] emailSend;

    @Override
    public void startNotify() {
        Optional<InfoEn> dataInfo = INFO_REPO.findByIdUser(COMMON_REPO.getUserAuthentication().getId());
        String email = USER_REPO.findById(data.getId_friend()).orElseThrow().getGmails();
        String nameShort = dataInfo.orElseThrow().getFullnames().split(" ")[dataInfo.orElseThrow().getFullnames().split(" ").length - 1];
        Date date = new Date();
        String dateUse = date.getDate() + " Tháng " + date.getMonth() + " lúc " + date.getHours() +":"+ date.getMinutes();
        Context ctx = new Context();

        if(data.getStatus().equals("group")) {
            GroupEn groupEn =  GROUP_REPO.findById(data.getId_friend()).orElseThrow();
            ctx.setVariable("nameGroup", groupEn.getName());
            ctx.setVariable("IdGroup",groupEn.getId());
        } else {
            ctx.setVariable("IdUser",COMMON_REPO.getUserAuthentication().getId());
        }

        ctx.setVariable("email", email);
        ctx.setVariable("img", dataInfo.orElseThrow().getAvatars());
        ctx.setVariable("nameFull", dataInfo.orElseThrow().getFullnames());
        ctx.setVariable("date", dateUse);
        ctx.setVariable("status", data.getStatus());
        MimeMessage mess = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mess, false, "UTF-8");
            helper.setTo(emailSend);
            helper.setSubject(dataInfo.orElseThrow().getFullnames() + " Messenger");
            helper.setText( nameShort + " đã gửi cho bạn một tin nhắn.", false);
            String htmlContent = templateEngine.process("NewMess", ctx);
            mess.setContent(htmlContent, "text/html; charset=utf-8");
            helper.setText(htmlContent, true);
            mailSender.send(mess);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }
    }
}
