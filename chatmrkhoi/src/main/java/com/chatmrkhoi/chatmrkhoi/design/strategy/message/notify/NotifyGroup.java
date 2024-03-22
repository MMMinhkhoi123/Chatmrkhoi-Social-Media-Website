package com.chatmrkhoi.chatmrkhoi.design.strategy.message.notify;

import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.observer.CenterOb;
import com.chatmrkhoi.chatmrkhoi.design.observer.NotifyEmail;
import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;
import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.InfoEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IGroupRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IInfoRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Setter
public class NotifyGroup implements INotify {

    @Autowired NotifyEmail notifyEmail;
    @Autowired IGroupRepo GROUP_REPO;
    @Autowired Common COMMON;
    @Autowired IActionRepo ACTION_REPO;
    @Autowired IInfoRepo INFO_REPO;
    private DataSaveMessReq data;
    private  String email = "";
   @Override
    public void startNotify() {
       notifyEmail.setData(data);
       CenterOb ob = new CenterOb();
       ob.eventsNumber.Subscriber("NotifyNewMess",notifyEmail);
       GroupEn groupEn = GROUP_REPO.findById(data.getId_friend()).orElseThrow();

       if (!Objects.equals(groupEn.getUserEntity().getId(), COMMON.getUserAuthentication().getId())) {
           email = email + setup(groupEn.getUserEntity());
       }

       groupEn.getUsersentitiesx().forEach((e) -> {
           if(!Objects.equals(e.getId(), COMMON.getUserAuthentication().getId())) {
               email = email +  setup(e);
           }
       });
       if (email.split(",").length == 0) {
           ob.eventsNumber.UnSubscriber("NotifyNewMess",notifyEmail);
       }
       notifyEmail.setEmailSend(email.split(","));
        ob.Notify();
    }




    private  String setup(UserEn userEn) {
        String keyResult = "";
        Optional<InfoEn> infoEn = INFO_REPO.findByIdUser(userEn.getId());
        Optional<ActionEn> dataAction = ACTION_REPO.findByIdUser(userEn.getId());
        if (dataAction.orElseThrow().getStatus().equalsIgnoreCase("offline")) {
            if(infoEn.orElseThrow().isNotify()) {
                keyResult = keyResult + userEn.getGmails() + ",";
            }
        }
        return  keyResult;
    }
}
