package com.routing.skillservice.client.auth;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class AuthRequestFilter implements ClientRequestFilter {

	private static final String AUTHORIZATION_KEY = "Authorization";
	private final StringBuilder sb;

	public AuthRequestFilter(final String jwt) {
		sb = new StringBuilder().append("Bearer ").append(jwt);
	}

	@Override
	public void filter(final ClientRequestContext content) throws IOException {
		content.getHeaders().add(AUTHORIZATION_KEY, this.sb.toString());
	}

}
