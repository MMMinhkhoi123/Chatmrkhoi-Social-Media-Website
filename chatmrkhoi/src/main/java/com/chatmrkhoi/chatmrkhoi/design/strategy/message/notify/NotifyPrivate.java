package com.chatmrkhoi.chatmrkhoi.design.strategy.message.notify;

import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.observer.CenterOb;
import com.chatmrkhoi.chatmrkhoi.design.observer.NotifyEmail;
import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IGroupRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Setter
public class NotifyPrivate implements INotify {
    @Autowired NotifyEmail notifyEmail;
    @Autowired IGroupRepo GROUP_REPO;
    @Autowired Common COMMON;
    @Autowired IActionRepo ACTION_REPO;
    @Autowired IInfoRepo INFO_REPO;
    @Autowired IUserRepo USER_REPO;
    private DataSaveMessReq data;


    @Override
    public void startNotify() {
        notifyEmail.setData(data);
        CenterOb ob = new CenterOb();
        String [] email = new String[1];

        ob.eventsNumber.Subscriber("NotifyNewMess",notifyEmail);
        Optional<ActionEn> dataAction = ACTION_REPO.findByIdUser(data.getId_friend());
        Optional<InfoEn> infoEn = INFO_REPO.findByIdUser(data.getId_friend());
        email[0]= USER_REPO.findById(data.getId_friend()).orElseThrow().getGmails();
        if (!dataAction.orElseThrow().getStatus().equalsIgnoreCase("offline"))
        {
            ob.eventsNumber.UnSubscriber("NotifyNewMess",notifyEmail);
        }
        else
        {
            if(!infoEn.orElseThrow().isNotify())
            {
                ob.eventsNumber.UnSubscriber("NotifyNewMess",notifyEmail);
            }

        }
        notifyEmail.setEmailSend(email);
        ob.Notify();
    }
}
