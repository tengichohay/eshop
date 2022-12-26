package com.example.shared.common.model;

import lombok.Data;

@Data
public class ApiResult<T> {

	private String message;

	private T object;

	public ApiResult(String message, T object) {
		this.message = message;
		this.object = object;
	}

	public ApiResult() {
	}
}
