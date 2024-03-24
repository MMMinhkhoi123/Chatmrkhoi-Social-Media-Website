package com.chatmrkhoi.chatmrkhoi.design.state;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoActionUserRep;
import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Setter
public class OnlineState implements IUserState {
    @Autowired SimpMessageSendingOperations messagingTemplate;
    @Autowired IActionRepo ACTION_REPO;
    private String session ;
    private Long idLogin;

    @Override
    public void handleStatus() {
        Optional<ActionEn> actions = ACTION_REPO.findByIdUser(idLogin);
        actions.ifPresentOrElse((user) -> {
            user.setStatus("online");
            user.setSesstionid(session);
            user.setTimetamp(System.currentTimeMillis());
            ACTION_REPO.save(user);
        }, () -> {
            ActionEn action = new ActionEn();
            action.setStatus("online");
            action.setId_user(idLogin);
            action.setSesstionid(session);
            action.setTimetamp(System.currentTimeMillis());
            ACTION_REPO.save(action);
        });
        DataInfoActionUserRep user = new DataInfoActionUserRep();
        user.setId(idLogin);
        user.setTimetamp(System.currentTimeMillis());
        user.setStatus("online");
        messagingTemplate.convertAndSend("/user/action/run", user);
    }

    @Override
    public String getStatus() {
        return ACTION_REPO.findByIdUser(idLogin)
                .orElseThrow()
                .getStatus();
    }

    @Override
    public void resetData() {
        session = null;
        idLogin = null;
    }
}
