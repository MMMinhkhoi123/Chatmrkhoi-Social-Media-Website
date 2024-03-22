package com.chatmrkhoi.chatmrkhoi.security;

import java.io.IOException;
import java.util.Date;
import java.util.Optional;

import io.micrometer.common.lang.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chatmrkhoi.chatmrkhoi.exception.cutomer.NotfoundException;
import com.chatmrkhoi.chatmrkhoi.entity.JwtEn;
import com.chatmrkhoi.chatmrkhoi.entity.UserEn;
import com.chatmrkhoi.chatmrkhoi.repositories.IJwtRepo;
import com.chatmrkhoi.chatmrkhoi.repositories.IUserRepo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FilterAuthentication extends OncePerRequestFilter {

	@Autowired private JwtToken TOKEN_CENTER;
	@Autowired private IJwtRepo JWT_REPO;
	@Autowired private IUserRepo user_repo;
	@Autowired private ConfigUserDetail USER_DETAIL;
	@Override
	protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

		if(checkPaths(request.getServletPath())) {
			filterChain.doFilter(request, response);
			return;
		}

		String token = getJWTFromRequest(request);

		if (StringUtils.hasText(token)) {
			String username = TOKEN_CENTER.getUsernameFromToken(token);
			UserDetails userDetails = USER_DETAIL.loadUserByUsername(username);
			if(TOKEN_CENTER.checkToken(token)) {
				setAuthenticated(userDetails, request);
				filterChain.doFilter(request, response);
			} else {
				UserEn user = user_repo.findByEmail(username).orElseThrow();
				Optional<JwtEn> data = JWT_REPO.findByUserId(user.getId());
				data.ifPresentOrElse((e) -> {
					if(e.getTime() > new Date().getTime()) {
						setAuthenticated(userDetails,request);
						try {

							filterChain.doFilter(request, response);

						} catch (IOException | ServletException ex) {

							throw new NotfoundException("Invalid");
						}
					}
				}, () -> {
					throw new NotfoundException("Invalid");
				});
			}
		}
	}

	private void setAuthenticated(UserDetails userDetails, HttpServletRequest request ) {
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null,null);
		authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
	}


	private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");  
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7, bearerToken.length());
        }
        return null;
    }

	
	boolean check;
	public boolean checkPaths(String paths) {
		check = false;
		String [] path = paths.split("/");
        for (String s : path) {
            if (s.equalsIgnoreCase("account") ||
                    s.equalsIgnoreCase("gmail") ||
                    s.equalsIgnoreCase("get-png") ||
                    s.equalsIgnoreCase("geturl-video") ||
                    s.equalsIgnoreCase("filedowload") ||
                    s.equalsIgnoreCase("login") ||
                    s.equalsIgnoreCase("filedowloadid") ||
                    s.equalsIgnoreCase("gs-guide-websocket")) {
                check = true;
                break;
            }
        }
		return check;
	}

}
