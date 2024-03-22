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
import org.springframework.web.socket.messaging.SessionConnectEvent;
import java.util.Optional;

@Component
public class ConnectListen {

    @Autowired SimpMessageSendingOperations messagingTemplate;
    @Autowired IUserRepo USER_REPO;
    @Autowired IActionRepo ACTION_REPO;
    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent sessionConnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
        if(headerAccessor.getLogin() != null) {
            if(!headerAccessor.getLogin().equalsIgnoreCase("undefined")) {
                Optional<ActionEn> action = ACTION_REPO.findByIdUser(Long.valueOf(headerAccessor.getLogin()));
                action.ifPresentOrElse((e) -> {
                    action.orElseThrow().setSesstionid(headerAccessor.getSessionId());
                    action.orElseThrow().setStatus("online");
                    action.orElseThrow().setTimetamp(System.currentTimeMillis());
                    ACTION_REPO.save(action.get());
                }, () -> {
                    ActionEn acEntity = ActionEn.builder()
                            .sesstionid(headerAccessor.getSessionId())
                            .id_user(Long.valueOf(headerAccessor.getLogin()))
                            .status("online")
                            .timetamp(System.currentTimeMillis())
                            .build();
                    ACTION_REPO.save(acEntity);
                });
                DataInfoActionUserRep user = new DataInfoActionUserRep();
                user.setId(Long.valueOf(headerAccessor.getLogin()));
                user.setTimetamp(System.currentTimeMillis());
                user.setStatus("online");
                messagingTemplate.convertAndSend("/user/action/run", user);
            }
        }
    }

}
