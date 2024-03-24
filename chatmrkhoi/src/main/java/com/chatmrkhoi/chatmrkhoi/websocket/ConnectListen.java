package com.chatmrkhoi.chatmrkhoi.websocket;


import com.chatmrkhoi.chatmrkhoi.design.state.ContextUser;
import com.chatmrkhoi.chatmrkhoi.design.state.OfflineState;
import com.chatmrkhoi.chatmrkhoi.design.state.OnlineState;
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

    @Autowired IUserRepo USER_REPO;
    @Autowired IActionRepo ACTION_REPO;
    @Autowired OnlineState ONLINE;

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectEvent sessionConnectEvent) {
        ContextUser contextAction = new ContextUser();
        contextAction.ChangeState(ONLINE);
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
        if(headerAccessor.getLogin() != null) {
            if(!headerAccessor.getLogin().equalsIgnoreCase("undefined")) {
                ONLINE.setSession(headerAccessor.getSessionId());
                ONLINE.setIdLogin(Long.valueOf(headerAccessor.getLogin()));
                contextAction.ChangeState(ONLINE);
                contextAction.handleStatus();
            }
        }
    }

}
