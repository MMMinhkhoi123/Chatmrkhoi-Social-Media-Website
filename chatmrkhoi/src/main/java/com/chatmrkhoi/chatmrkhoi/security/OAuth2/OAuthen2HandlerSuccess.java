package com.chatmrkhoi.chatmrkhoi.security.OAuth2;

import com.chatmrkhoi.chatmrkhoi.entity.Users_entity;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import com.chatmrkhoi.chatmrkhoi.security.config_userditail;
import java.beans.Customizer;
import java.io.IOException;

@Component
public class OAuthen2HandlerSuccess extends SavedRequestAwareAuthenticationSuccessHandler {


    @Autowired config_userditail config_userditaill;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
            CustomerOAuthen2User customerOAuthen2User = (CustomerOAuthen2User) authentication.getPrincipal();
            UserDetails UserDetails = config_userditaill.loadUserByUsername(customerOAuthen2User.getAttribute("gmail"));
            if (UserDetails != null) {
                System.out.println("Acount exit !");
            } else  {
                System.out.println("Acount not exit !");
            }
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
