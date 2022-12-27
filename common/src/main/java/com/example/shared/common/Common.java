/**
 * ****************************************************************************
 * Copyright (c) 2017 ANHTCN.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * *****************************************************************************
 */
package com.example.shared.common;

import java.util.UUID;

public class Common {

	public static boolean isNullOrEmpty(String str) {
		return str == null || str.trim().isEmpty();
	}

	public static String GenerateUUID() {
		return UUID.randomUUID().toString();
	}

	public static java.sql.Timestamp getCurrentTime() {
		return new java.sql.Timestamp(System.currentTimeMillis());
	}

}
