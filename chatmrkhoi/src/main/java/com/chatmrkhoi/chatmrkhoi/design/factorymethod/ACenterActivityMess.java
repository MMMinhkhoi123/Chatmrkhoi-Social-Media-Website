package com.chatmrkhoi.chatmrkhoi.design.factorymethod;

import com.chatmrkhoi.chatmrkhoi.entity.MessageEn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class ACenterActivityMess {
    public MessageEn getMessageNew() {
        return this.setupObject().createMessage();
    }
    public  abstract IActivityMessenger setupObject();
}
