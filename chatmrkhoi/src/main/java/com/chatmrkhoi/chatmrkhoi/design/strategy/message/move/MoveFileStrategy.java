package com.chatmrkhoi.chatmrkhoi.design.strategy.message.move;

import com.chatmrkhoi.chatmrkhoi.Data.request.DatatransactionMessReq;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.entity.FileEn;
import com.chatmrkhoi.chatmrkhoi.entity.FriendEn;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IFileRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IGroupRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import com.chatmrkhoi.chatmrkhoi.service.impl.FileSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoveFileStrategy implements ITypeMoveStrategy {
    @Autowired IFriendRepo FRIEND_REPO;
    @Autowired IGroupRepo GROUP_REPO;
    @Autowired IMessageRepo MESSAGE_REPO;
    @Autowired IFileRepo FILE_REPO;
    @Autowired FileSer FILE_SER;
    @Autowired Common COMMON;
    private MessageEn MessRep = new MessageEn();
    @Override
    public MessageEn transactionMessage(DatatransactionMessReq data, MessageEn DataMess) {


        DataMess.setContent("");
        DataMess.setUsersentity(COMMON.getUserAuthentication());
        if(data.getIdfriend() != null) {
            DataMess.setGroupmess(null);
            FriendEn x = null;
            if(FRIEND_REPO.findByFriendIdAndUserId(data.getIdfriend(),COMMON.getUserAuthentication().getId()).isPresent()) {
                x = FRIEND_REPO.findByFriendIdAndUserId(data.getIdfriend(), COMMON.getUserAuthentication().getId()).get();
            } else {
                x = FRIEND_REPO.findByFriendIdAndUserId(COMMON.getUserAuthentication().getId(),data.getIdfriend()).orElseThrow();
            }
            DataMess.setFriendmess(x);
        } else {
            DataMess.setFriendmess(null);
            DataMess.setGroupmess(GROUP_REPO.findById(data.getIdgroup()).orElseThrow());
        }


        DataMess.setFile_entities(null);


        MessRep = MESSAGE_REPO.save(DataMess);

        FILE_REPO.getAllByIdMess(data.getIdmess()).orElseThrow().forEach((e) -> {
            FileEn filenew = FILE_SER.save(e.getNamefile(), e.getTypefile(), e.getSize());
            FILE_REPO.updateById(MessRep.getId(),filenew.getId());
        });

        return  MessRep;
    }
}
