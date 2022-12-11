/*******************************************************************************
 * Copyright (c) 2017 ANHTCN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/

package com.example.authservice.authentication;

import com.example.authservice.common.Common;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.util.Arrays;

public class MyEncryptPassword implements PasswordEncoder {

	@Override
	public String encode(CharSequence rawPassword) {

		return Common.encryptPassword(rawPassword.toString());
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		try {
			byte[] encoded = MessageDigest.getInstance("SHA-1").digest(rawPassword.toString().getBytes());
			byte[] hash = DatatypeConverter.parseHexBinary(encodedPassword);
			return Arrays.equals(hash, encoded);
		} catch (Exception e) {
			return false;
		}

	}

}
