package com.chatmrkhoi.chatmrkhoi.security;

import java.io.Console;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception.Notfound_exception;
import com.chatmrkhoi.chatmrkhoi.entity.Jwt_entity;
import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import com.chatmrkhoi.chatmrkhoi.reponsitory.Jwt_repo;
import com.chatmrkhoi.chatmrkhoi.reponsitory.User_repo;
import com.chatmrkhoi.chatmrkhoi.service.impl.User_service;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class filter_authen  extends OncePerRequestFilter {

	
	@Autowired generation_token generation_token;
	
	@Autowired Jwt_repo jwt_repo;
	
	@Autowired User_repo user_repo;

	
	@Autowired
	private config_userditail userditail;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if(checkpath(request.getServletPath())) {
			filterChain.doFilter(request, response);
			return;
		}

		 String token = getJWTFromRequest(request);
		 if(StringUtils.hasText(token) && generation_token.CheckToken(token)) {
			  String username = generation_token.getUsernameFromJWTverify(token);
			  UserDetails userDetails = userditail.loadUserByUsername(username);
			  UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,null);
			  authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
	           SecurityContextHolder.getContext().setAuthentication(authenticationToken); 
	           filterChain.doFilter(request, response);

	    }else if(StringUtils.hasText(token) && !generation_token.CheckToken(token)) {
			 try {
				 String username = generation_token.getUsernameFromJWTverify(token);
				 Users_entity user = user_repo.findbygmail(username).get();
				 Optional<Jwt_entity> data = jwt_repo.findbyjwt(user.getId());
				 data.ifPresentOrElse((e) -> {
					 if(e.getTime() > new Date().getTime()) {
						 UserDetails userDetails = userditail.loadUserByUsername(username);
						 UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,null
						 );
						 authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						 SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                         try {
                             filterChain.doFilter(request, response);
                         } catch (IOException | ServletException ex) {
                             throw new RuntimeException(ex);
                         }

                     }
				 }, () -> {
					 throw new Notfound_exception("invaelid");
				 });
			 } catch (Exception ex) {
				 response.sendError(500);
			 }
	    } else {
			 System.out.println("JWT INVALID");
		 }
	}
	private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");  
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

	
	boolean check;
	public boolean checkpath(String paths) {
		check = false;
		String [] path = paths.split("/");
		for(int i = 0; i< path.length ; i ++) {
			if(path[i].equalsIgnoreCase("authen") ||
					    path[i].equalsIgnoreCase("gmail") ||
							 path[i].equalsIgnoreCase("get-png") ||
									 path[i].equalsIgnoreCase("geturl-video") ||
											 path[i].equalsIgnoreCase("filedowload") ||
					                              path[i].equalsIgnoreCase("login") ||
													 path[i].equalsIgnoreCase("filedowloadid") ||
					path[i].equalsIgnoreCase("gs-guide-websocket"))  {
				check = true;
			}
		}
		return check;
	}

}
