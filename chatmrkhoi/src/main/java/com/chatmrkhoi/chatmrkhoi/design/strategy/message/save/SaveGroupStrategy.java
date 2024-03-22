package com.chatmrkhoi.chatmrkhoi.design.strategy.message.save;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataMessageRep;
import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.Util.convert.MessageConverts;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.observer.CenterOb;
import com.chatmrkhoi.chatmrkhoi.design.observer.NotifyEmail;
import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;
import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IFileRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IGroupRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SaveGroupStrategy implements ISaveStrategy{

    @Autowired IGroupRepo GROUP_REPO;
    @Autowired IMessageRepo MESSAGE_REPO;
    @Autowired IFileRepo FILE_REPO;
    @Autowired Common COMMON;
    @Autowired MessageConverts CONVERT;
    @Override
    public void SaveMessage(DataSaveMessReq data, List<DataMessageRep> listMessRep ) {

        MessageEn mess = new MessageEn();
        UserEn user = COMMON.getUserAuthentication();
        List<MessageEn> list = new ArrayList<MessageEn>();

        //  Handle Mess New
        GroupEn group = GROUP_REPO.findById(data.getId_friend()).orElseThrow();
        MESSAGE_REPO.findAll().forEach((e) -> {
            if(e.getGroupmess() != null) {
                if(e.getGroupmess().getId().equals(group.getId())) {
                    list.add(e);
                }
            }
        });

        if (!list.isEmpty()) {
            java.util.Date date_one = new java.util.Date();
            Date date_two = new Date(list.get( list.size() - 1).getTime());
            if(date_one.getDate() != date_two.getDate()) {
                MessageEn messdate = MessageEn.builder()
                        .groupmess(group)
                        .content("")
                        .start_date(true)
                        .usersentity(user)
                        .time(System.currentTimeMillis()).build();
                MessageEn messDate =  MESSAGE_REPO.save(messdate);
                listMessRep.add(CONVERT.convertMessRep(messDate));
            }
        }

        // save mess
        mess = MessageEn.builder()
                .content(data.getContent())
                .time(System.currentTimeMillis())
                .groupmess(group)
                .typereply(data.getTyperep())
                .reply(data.getReply())
                .usersentity(user)
                .build();

        MessageEn MessAfterSave = MESSAGE_REPO.save(mess);



        listMessRep.add(CONVERT.convertMessRep(MessAfterSave));

        // update ingo img if exits
        if(!data.getIdfile().isEmpty()) {
            data.getIdfile().forEach((e) -> {
                FILE_REPO.updateById(MessAfterSave.getId(),e);
            });
        }





    }




}
