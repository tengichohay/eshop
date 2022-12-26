package com.example.shared.common.context;

import com.example.shared.common.Common;
import com.example.shared.common.DecodedToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class DataContextHelper {

	public void init(Authentication authentication) {

		String accessToken = ((OAuth2AuthenticationDetails) authentication.getDetails()).getTokenValue();
		if (Common.isNullOrEmpty(accessToken)) {
			return;
		}
		DecodedToken decodeToken = DecodedToken.parseProperties(accessToken);
		DataContext dataContext = DataContext.builder()
				.username(decodeToken.getUserName())
				.clientId(decodeToken.getClientId())
				.authorities(decodeToken.getAuthorities())
				.uuidAccountLogin(decodeToken.getUuidAccount())
				.build();
		System.out.printf("user: %s%n", decodeToken);
		DataContextHolder.setDataContext(dataContext);
	}

	public String getUuidAccountLogin() {
		return dataContext().getUuidAccountLogin();
	}

	public String getUserNameAccountLogin() {
		return dataContext().getUsername();
	}

	public Object get(String key) {
		return dataContext().getOtherData().get(key);
	}

	public void put(String key, Object value) {
		dataContext().getOtherData().put(key, value);
	}

	public void clear() {
		DataContextHolder.clear();
	}

	private DataContext dataContext() {
		return DataContextHolder.getDataContext();
	}

	public boolean isHasRole(String role) {
		return dataContext().getAuthorities().contains(role);
	}

	public List<String> getAuthorities() {
		return dataContext().getAuthorities();
	}

}
