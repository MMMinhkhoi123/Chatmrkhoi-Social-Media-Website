package com.chatmrkhoi.chatmrkhoi.design.strategy.message.move;

import com.chatmrkhoi.chatmrkhoi.Data.request.DatatransactionMessReq;
import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IMessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContextMoveStrategy {

    @Autowired IMessageRepo MESSAGE_REPO;
    private ITypeMoveStrategy routeMoveStrategy;
    public  void setMoveStrategy(ITypeMoveStrategy ITypeMoveStrategy) {
        this.routeMoveStrategy = ITypeMoveStrategy;
    }

    public MessageEn executeStrategy(DatatransactionMessReq data) {
        MessageEn mess = MESSAGE_REPO.findById(data.getIdmess()).orElseThrow();
        return routeMoveStrategy.transactionMessage(data, getMessageEn(mess));
    }

    private MessageEn getMessageEn(MessageEn ness) {
        MessageEn mess_new = new MessageEn();
        mess_new.setContent(ness.getContent());
        mess_new.setFile_entities(ness.getFile_entities());
        mess_new.setFriendmess(ness.getFriendmess());
        mess_new.setGroupmess(ness.getGroupmess());
        mess_new.setStatus(ness.isStatus());
        mess_new.setTime(ness.getTime());
        mess_new.setType(ness.getType());
        mess_new.setUsersentity(ness.getUsersentity());
        return mess_new;
    }
}
