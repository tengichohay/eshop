package com.example.authservice.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Common {

	public static String encryptPassword(String passwordToHash) {
		BCryptPasswordEncoder br = new BCryptPasswordEncoder();
		return br.encode(passwordToHash);
	}
}
