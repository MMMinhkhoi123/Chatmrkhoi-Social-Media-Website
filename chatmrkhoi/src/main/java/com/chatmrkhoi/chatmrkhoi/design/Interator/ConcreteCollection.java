package com.chatmrkhoi.chatmrkhoi.design.Interator;

import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Service
public class ConcreteCollection implements  IConnection {
    @Autowired private UserSugIterator logicIterator;
    private List<UserEn> listUserConnection = new ArrayList<>();
    @Override
    public IIterator CreateIterator() {
        logicIterator.setData(this);
        return logicIterator;
    }
    public  void  setListUser(List<UserEn> user) {
        this.listUserConnection = user;
    }

    public  int  getSize() {
        return  listUserConnection.size();
    }
    public  UserEn  getUser(int indexPosition) {
        return listUserConnection.get(indexPosition);
    }
}
