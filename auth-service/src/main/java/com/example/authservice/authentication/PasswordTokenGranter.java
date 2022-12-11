package com.example.authservice.authentication;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;

import java.util.HashMap;
import java.util.Map;

public class PasswordTokenGranter extends AbstractTokenGranter {

	private final AuthenticationManager authenticationManager;

	public PasswordTokenGranter(AuthorizationServerEndpointsConfigurer endpoints, AuthenticationManager authenticationManager) {
		super(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(), "password");
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {
		Map<String, String> params = new HashMap<>(tokenRequest.getRequestParameters());
		String username = params.get("username");
		String password = params.get("password");

		OAuth2Request auth2Request = this.getRequestFactory().createOAuth2Request(client, tokenRequest);

		Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		params.remove("password");
		((AbstractAuthenticationToken) authenticationToken).setDetails(params);

		authenticationToken = authenticationManager.authenticate(authenticationToken);

		OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(auth2Request, authenticationToken);

		return oAuth2Authentication;
	}


}
