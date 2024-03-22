package com.chatmrkhoi.chatmrkhoi.websocket;

import com.chatmrkhoi.chatmrkhoi.entity.ActionEn;
import com.chatmrkhoi.chatmrkhoi.Data.reponse.DataInfoActionUserRep;
import com.chatmrkhoi.chatmrkhoi.repositories.IActionRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Optional;

@Component
public class DisConnectListen {


    @Autowired SimpMessageSendingOperations messagingTemplate;
    @Autowired IUserRepo USER_REPO;
    @Autowired IActionRepo ACTION_REPO;
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        Optional<ActionEn> actionEnd  = ACTION_REPO.findBySesId(headerAccessor.getSessionId());
        actionEnd.ifPresent((e) -> {
            actionEnd.get().setTimetamp(System.currentTimeMillis());
            actionEnd.get().setStatus("offline");
            ACTION_REPO.save(actionEnd.get());
            DataInfoActionUserRep user = new DataInfoActionUserRep();
            user.setId(e.getId_user());
            user.setTimetamp(System.currentTimeMillis());
            user.setStatus("offline");
            messagingTemplate.convertAndSend("/user/action/run", user);
        });
    }
}
