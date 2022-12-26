package com.example.shared.common;

import com.example.shared.common.json.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.codec.binary.Base64;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Data
public class DecodedToken {

	@SerializedName("user_name")
	private String userName;

	@SerializedName("client_id")
	private String clientId;

	@SerializedName("authorities")
	private List<String> authorities;

	@SerializedName("uuid_account")
	private String uuidAccount;

	private DecodedToken() {
	}

	public static DecodedToken parseProperties(String encodedToken) {
		String[] pieces = encodedToken.split("\\.");
		String b64payload = pieces[1];
		String jsonString = new String(Base64.decodeBase64(b64payload), StandardCharsets.UTF_8);
		return new Gson().fromJson(jsonString, DecodedToken.class);
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
