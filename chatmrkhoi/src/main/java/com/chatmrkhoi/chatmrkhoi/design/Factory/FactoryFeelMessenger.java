package com.chatmrkhoi.chatmrkhoi.design.Factory;
import com.chatmrkhoi.chatmrkhoi.entity.feel_entity;
public class FactoryFeelMessenger extends AFactoryActionMess {
    @Override
    public IActionMessenger CreateActionMess() {
        return feel_entity.builder()
                .time(System.currentTimeMillis())
                .type(type)
                .mess(key)
                .messentity(messEntity)
                .iduser(usersEntity)
                .build();
    }
}
