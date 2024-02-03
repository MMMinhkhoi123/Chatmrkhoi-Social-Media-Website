package com.chatmrkhoi.chatmrkhoi.websocket;

import java.io.Console;
import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;
import org.springframework.web.socket.messaging.SessionUnsubscribeEvent;

import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.entity.action_entity;
import com.chatmrkhoi.chatmrkhoi.reponse.action_user;
import com.chatmrkhoi.chatmrkhoi.reponse.mess_reponse;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.action_repo;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class websocket_listen {
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	@Autowired
	User_repo user_repo;
	@Autowired
	action_repo action_repos;
	@EventListener
	    public void handleWebSocketConnectListener(SessionConnectEvent sessionConnectEvent) {
	        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionConnectEvent.getMessage());
		System.out.println(headerAccessor.getSessionId());
	        if(headerAccessor.getLogin() != null) {
				System.out.println("login");
	        	if(!headerAccessor.getLogin().equalsIgnoreCase("undefined")) {
					Optional<action_entity> action = action_repos.findbyiduser(Long.valueOf(headerAccessor.getLogin()));
	   		        action.ifPresentOrElse((e) -> {
	   			        	action.get().setSesstionid(headerAccessor.getSessionId());
	   			        	action.get().setStatus("online");
	   			        	action.get().setTimetamp(System.currentTimeMillis());
	   			        	action_repos.save(action.get());
	   		        }, () -> {
	   		        	action_entity acEntity = action_entity.builder()
	   		        			.sesstionid(headerAccessor.getSessionId())
	   		        			.id_user(Long.valueOf(headerAccessor.getLogin()))
	   		        			.status("online")
	   		        			.timetamp(System.currentTimeMillis())
	   		        			.build();
	   		        	action_repos.save(acEntity);
	   		        });
	   		        // send mess
	   		        action_user useraction = new action_user();
	   		        useraction.setId(Long.valueOf(headerAccessor.getLogin()));
	   		        useraction.setTimetamp(System.currentTimeMillis());
	   		        useraction.setStatus("online");
	   		        messagingTemplate.convertAndSend("/user/action/run", useraction);
	        	}
	        }
	    }
	   
	   
	@EventListener
	    public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
	        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
		System.out.println("logout");
		System.out.println(headerAccessor.getSessionId());
	        Optional<action_entity>  actionEnd  = action_repos.findbyidss(headerAccessor.getSessionId());
	        actionEnd.ifPresent((e) -> {
	        	actionEnd.get().setTimetamp(System.currentTimeMillis());
	        	actionEnd.get().setStatus("offline");
	        	action_repos.save(actionEnd.get());
	            action_user useraction = new action_user();
		        useraction.setId(e.getId_user());
		        useraction.setTimetamp(System.currentTimeMillis());
		        useraction.setStatus("offline");
		        messagingTemplate.convertAndSend("/user/action/run", useraction);
	        });
	    }
}
