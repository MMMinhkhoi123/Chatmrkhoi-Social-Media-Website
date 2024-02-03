package com.chatmrkhoi.chatmrkhoi.security;

import java.util.*;

import com.chatmrkhoi.chatmrkhoi.security.OAuth2.CustomerOAthe2userServer;
import com.chatmrkhoi.chatmrkhoi.security.OAuth2.OAuthen2HandlerSuccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.CookieClearingLogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class config {
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable);
		http.cors((e) -> e.setBuilder(http));
		http.oauth2Login((e) -> e.userInfoEndpoint(c -> c.userService(oAthe2userServer))
						.successHandler(oAuthen2HandlerSuccess)
		).logout(logout ->
				logout.logoutUrl("/logout")
						.invalidateHttpSession(true)
						.clearAuthentication(true)
						.deleteCookies("JSESSIONID")
						.logoutSuccessUrl("/logoutsuccess")
		);

		http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(
			AuthenticationConfiguration authenticationConfiguration) throws Exception {
	        return authenticationConfiguration.getAuthenticationManager();
	 }
	@Bean
    public filter_authen jwtAuthenticationFilter() {
        return new filter_authen();
    }
	
	@Bean
    public jw_authenexception authenexeipton() {
        return new jw_authenexception();
    }


	@Autowired
	private CustomerOAthe2userServer oAthe2userServer;

	@Autowired
	private OAuthen2HandlerSuccess oAuthen2HandlerSuccess;

}
