package com.example.shared.common.json;

import com.example.shared.common.ConstantString;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils {

	private static final Gson gson = new GsonBuilder().setDateFormat(ConstantString.DDMMYYYY_HHMMSS).serializeNulls().create();

	public static String toJson(Object obj) {
		return gson.toJson(obj);
	}
}
