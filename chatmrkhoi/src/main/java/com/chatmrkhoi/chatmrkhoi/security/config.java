package com.chatmrkhoi.chatmrkhoi.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@Configuration
@EnableWebSecurity
@EnableWebMvc
public class config {

//	@Bean
//	CorsConfigurationSource configurationSource() {
//		List<String> methods = new ArrayList<String>();
//		methods.add("GET");
//		methods.add("POST");
//		methods.add("DELETE");
//		methods.add("PUT");
//		List<String> Origins = new ArrayList<String>();
//		Origins.add("http://localhost:5173");
//		CorsConfiguration configuration = new CorsConfiguration();
//		configuration.setAllowedMethods(methods);
//		configuration.setAllowedOrigins(Origins);
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", configuration);
//		return source;
//	}
	
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf((csrf) -> csrf.disable());
		http.cors((e) -> e.setBuilder(http));

		http.oauth2Login((e) ->
				e.authorizationEndpoint(
						x -> x.baseUri("/login/oauth2/authorization"))
						.failureUrl("/authen/signingoole")

		);

//		http.cors((e) -> e.configurationSource(configurationSource()) );
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
	
}
