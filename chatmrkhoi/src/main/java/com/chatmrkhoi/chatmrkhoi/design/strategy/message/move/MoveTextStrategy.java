package com.chatmrkhoi.chatmrkhoi.design.strategy.message.move;

import com.chatmrkhoi.chatmrkhoi.Data.request.DatatransactionMessReq;
import com.chatmrkhoi.chatmrkhoi.common.Common;
import com.chatmrkhoi.chatmrkhoi.design.strategy.message.move.ITypeMoveStrategy;
import com.chatmrkhoi.chatmrkhoi.entity.FriendEn;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IFriendRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IGroupRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MoveTextStrategy implements ITypeMoveStrategy {

    @Autowired IFriendRepo FRIEND_REPO;
    @Autowired IGroupRepo GROUP_REPO;
    @Autowired IMessageRepo MESSAGE_REPO;
    @Autowired Common COMMON;
    public  MoveTextStrategy() {}
    @Override
    public MessageEn transactionMessage(DatatransactionMessReq data, MessageEn dataMess) {
        dataMess.setFile_entities(null);
        dataMess.setUsersentity(COMMON.getUserAuthentication());
        if(data.getIdfriend() != null) {
            FriendEn x = null;
            if(FRIEND_REPO.findByFriendIdAndUserId(data.getIdfriend(),COMMON.getUserAuthentication().getId() ).isPresent()) {
                x = FRIEND_REPO.findByFriendIdAndUserId(data.getIdfriend(), COMMON.getUserAuthentication().getId()).get();
            } else {
                x = FRIEND_REPO.findByFriendIdAndUserId( COMMON.getUserAuthentication().getId(),data.getIdfriend()).orElseThrow();
            }
            dataMess.setGroupmess(null);
            dataMess.setFriendmess(x);
        } else {
            dataMess.setFriendmess(null);
            dataMess.setGroupmess(GROUP_REPO.findById(data.getIdgroup()).orElseThrow());
        }
        return MESSAGE_REPO.save(dataMess);
    }


}
