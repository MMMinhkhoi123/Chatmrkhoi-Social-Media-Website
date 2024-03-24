package com.chatmrkhoi.chatmrkhoi.design.state;

import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoActionUserRep;
import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

@Service
@Setter
public class OfflineState implements  IUserState{
    @Autowired SimpMessageSendingOperations messagingTemplate;
    @Autowired IActionRepo ACTION_REPO;
    private String session;
    private Long idUserEn;

    @Override
    public void handleStatus() {
        ACTION_REPO.findBySesId(session).ifPresent((user) -> {
            user.setStatus("offline");
            user.setTimetamp(System.currentTimeMillis());
            ACTION_REPO.save(user);
            DataInfoActionUserRep users = new DataInfoActionUserRep();
            users.setId(user.getId_user());
            users.setTimetamp(System.currentTimeMillis());
            users.setStatus("offline");
            messagingTemplate.convertAndSend("/user/action/run", users);
        });
    }

    @Override
    public String getStatus() {
        return ACTION_REPO.findByIdUser(idUserEn)
                .orElseThrow()
                .getStatus();
    }

    @Override
    public void resetData() {
        idUserEn = null;
    }
}
