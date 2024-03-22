package com.chatmrkhoi.chatmrkhoi.design.factorymethod;

import com.chatmrkhoi.chatmrkhoi.Data.request.DataKickOrAddAnNumberReq;
import com.chatmrkhoi.chatmrkhoi.entity.GroupEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IGroupRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Setter
@Component
public class ConcreteGroupMessenger extends ACenterActivityMess {

    @Autowired IGroupRepo GROUP_REPO;
    @Autowired IUserRepo USER_REPO;
    private DataKickOrAddAnNumberReq data;

    @Override
    public IActivityMessenger setupObject() {
        GroupEn group_entity = GROUP_REPO.findById(data.getId()).orElseThrow();
        group_entity.setCoderoom(group_entity.getCoderoom() + "&"+ data.getIdfriend());
        List<UserEn> user = group_entity.getUsersentitiesx();
        user.add(USER_REPO.findById(data.getIdfriend()).orElseThrow());
        group_entity.setUsersentitiesx(user);
        return GROUP_REPO.save(group_entity);
    }
}
