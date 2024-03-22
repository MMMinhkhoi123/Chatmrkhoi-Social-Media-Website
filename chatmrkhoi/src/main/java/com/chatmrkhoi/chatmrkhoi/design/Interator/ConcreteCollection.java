package com.chatmrkhoi.chatmrkhoi.design.Interator;

import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class ConcreteCollection implements  IConnection {
    @Autowired private UserSugIterator logicIterator;
    private List<UserEn> userConnection = new ArrayList<>();
    @Override
    public IListIterator CreateIterator() {
        logicIterator.setData(this);
        return logicIterator;
    }
    public  void  setListUser(List<UserEn> user) {
        this.userConnection = user;
    }

    public  int  getSize() {
        return  userConnection.size();
    }
    public  UserEn  getUser(int indexPosition) {
        return userConnection.get(indexPosition);
    }
}
