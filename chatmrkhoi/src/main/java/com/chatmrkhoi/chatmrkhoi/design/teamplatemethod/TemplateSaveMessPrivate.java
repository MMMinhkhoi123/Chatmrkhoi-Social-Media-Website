package com.chatmrkhoi.chatmrkhoi.design.teamplatemethod;

import com.chatmrkhoi.chatmrkhoi.Data.request.DataSaveMessReq;
import com.chatmrkhoi.chatmrkhoi.Util.convert.MessageConverts;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.FriendEn;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.Optional;

@Service
public class TemplateSaveMessPrivate extends ATemplateSaveMess {

    @Autowired IMessageRepo MESSAGE_REPO;
    @Autowired IFriendRepo FRIEND_REPO;
    @Autowired IUserRepo USER_REPO;
    @Autowired MessageConverts CONVERT;
    @Autowired IFileRepo FILE_REPO;
    @Autowired Common COMMON;
    private FriendEn dataFriend = new FriendEn();

    @Override
    public void saveMessNewDate(DataSaveMessReq data) {
        UserEn user = COMMON.getUserAuthentication();
        Optional<FriendEn> friend = FRIEND_REPO.findByFriendIdAndUserId(data.getId_friend(), user.getId());
        friend.ifPresentOrElse((e) -> {
            dataFriend = e;
            },
                () -> {
            dataFriend = FRIEND_REPO.findByFriendIdAndUserId(user.getId(), data.getId_friend()).orElseThrow();
        });

        MESSAGE_REPO.findAll().forEach((e) -> {
            if(e.getFriendmess() !=  null) {
                if(e.getFriendmess().getId().equals(dataFriend.getId())) {
                    list.add(e);
                }
            }
        });

        if (!list.isEmpty()) {
            java.util.Date date_one = new java.util.Date();
            Date date_two = new Date(list.get( list.size() - 1).getTime());
            if(date_one.getDate() != date_two.getDate()) {
                MessageEn messDate = MessageEn.builder()
                        .friendmess(dataFriend)
                        .content("")
                        .start_date(true)
                        .usersentity(USER_REPO.findById(user.getId()).orElseThrow())
                        .time(System.currentTimeMillis()).build();
                MessageEn messDateSave =  MESSAGE_REPO.save(messDate);
                listMessRep.add(CONVERT.convertMessRep(messDateSave));
            }
        }
    }

    @Override
    public MessageEn saveMess(DataSaveMessReq data) {
        UserEn user = COMMON.getUserAuthentication();
        MessageEn mess = new MessageEn();
        mess = MessageEn.builder()
                .content(data.getContent())
                .time(System.currentTimeMillis())
                .friendmess(dataFriend)
                .typereply(data.getTyperep())
                .reply(data.getReply())
                .usersentity(USER_REPO.findById(user.getId()).orElseThrow())
                .build();
        MessageEn MessAfterSave = MESSAGE_REPO.save(mess);
        listMessRep.add(CONVERT.convertMessRep(MessAfterSave));
        return MessAfterSave;
    }

    @Override
    public void updateInfoImage(DataSaveMessReq data, MessageEn MessAfterSave) {
        if(!data.getIdfile().isEmpty()) {
            data.getIdfile().forEach((e) -> {
                FILE_REPO.updateById(MessAfterSave.getId(),e);
            });
        }
    }
}
