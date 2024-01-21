package com.chatmrkhoi.chatmrkhoi.security.csrf;

import java.util.function.Supplier;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.stereotype.Component;

import com.chatmrkhoi.chatmrkhoi.Exception.cutomer_exception.Notfound_exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class config_readheader  extends CsrfTokenRequestAttributeHandler{

	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response,
			Supplier<CsrfToken> deferredCsrfToken) {
		super.handle(request, response, deferredCsrfToken);
	}

	@Override
	public String resolveCsrfTokenValue(HttpServletRequest request, CsrfToken csrfToken) {
		return super.resolveCsrfTokenValue(request, csrfToken);
	}	
	
	
	
	
}
