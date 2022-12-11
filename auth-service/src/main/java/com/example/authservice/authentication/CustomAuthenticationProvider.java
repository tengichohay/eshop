package com.example.authservice.authentication;

import com.example.authservice.api.model.Account;
import com.example.authservice.api.model.Privilege;
import com.example.authservice.api.model.Role;
import com.example.authservice.api.repository.AccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service(value = "authenticationProvider")
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private final AccountRepository userRepository;

	private final PasswordEncoder passwordEncoder;

	private static final GrantedAuthority PRE_AUTH = new SimpleGrantedAuthority("PRE_AUTH");

	public CustomAuthenticationProvider(AccountRepository userRepository, PasswordEncoder passwordEncoder) {
		super();
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		final String username = auth.getName();
		final String password = auth.getCredentials().toString();

		Account account = userRepository.findByUsername(username.toLowerCase().trim());

		if (account == null) {
			// Not found...
			UnauthorizedUserException e = enhance(new UnauthorizedUserException("User " + username + " not found."));
			e.addAdditionalInformation("message", "IDG-00000033");
			throw e;
		}

		if (account.getRoles() == null || account.getRoles().isEmpty() || !account.isEnabled()) {
			// No Roles  assigned to user...
			UnauthorizedUserException e = enhance(new UnauthorizedUserException("User not authorized. "));
			e.addAdditionalInformation("message", "IDG-00000033");
			throw e;
		}
		if (account.isUsing2FA()) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.singleton(PRE_AUTH));
		}
		MyEncryptPassword myEncryptPassword = new MyEncryptPassword();
		if (myEncryptPassword.matches(password, account.getPassword())
				|| passwordEncoder.matches(password, account.getPassword())) {
			return new UsernamePasswordAuthenticationToken(username, password, getAuthorities(account.getRoles()));
		}
		UnauthorizedUserException e = enhance(new UnauthorizedUserException("Password incorrect. "));
		e.addAdditionalInformation("message", "ERROR-00000033");
		throw e;
	}

	@Override
	public boolean supports(Class<?> auth) {
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}

	// UTIL
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public Collection<? extends GrantedAuthority> getAuthorities(final Collection<Role> roles) {
		return getGrantedAuthorities(getPrivileges(roles));
	}

	private final List<String> getPrivileges(final Collection<Role> roles) {
		final List<String> privileges = new ArrayList<String>();
		final List<Privilege> collection = new ArrayList<Privilege>();
		for (final Role role : roles) {
			collection.addAll(role.getPrivileges());
		}
		for (final Privilege item : collection) {
			privileges.add(item.getName());
		}

		return privileges;
	}

	private final List<GrantedAuthority> getGrantedAuthorities(final List<String> privileges) {
		final List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (final String privilege : privileges) {
			authorities.add(new SimpleGrantedAuthority(privilege));
		}
		return authorities;
	}

	private UnauthorizedUserException enhance(UnauthorizedUserException e) {
		e.addAdditionalInformation("statusCode", HttpStatus.UNAUTHORIZED.toString());
		e.addAdditionalInformation("status", HttpStatus.UNAUTHORIZED.name());
		return e;
	}
}