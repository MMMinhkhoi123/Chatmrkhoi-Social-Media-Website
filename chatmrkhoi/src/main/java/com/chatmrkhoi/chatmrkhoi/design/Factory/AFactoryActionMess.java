package com.chatmrkhoi.chatmrkhoi.design.Factory;

import com.chatmrkhoi.chatmrkhoi.entity.Mess_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AFactoryActionMess {

    public Users_entity usersEntity;
    public String type;
    public Mess_entity messEntity;
    public String key;

    public Mess_entity MessNewRespon() {
        return this.CreateActionMess().createMess();
    }

    public  abstract  IActionMessenger CreateActionMess();
}
