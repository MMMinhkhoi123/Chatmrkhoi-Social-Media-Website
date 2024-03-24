package com.chatmrkhoi.chatmrkhoi.websocket;

import com.chatmrkhoi.chatmrkhoi.design.state.ContextUser;
import com.chatmrkhoi.chatmrkhoi.design.state.OfflineState;
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
    @Autowired OfflineState OFFLINE;
    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
        OFFLINE.setSession(headerAccessor.getSessionId());
        ContextUser context = new ContextUser();
        context.setState(OFFLINE);
        context.handleStatus();
    }
}
