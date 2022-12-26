package com.example.shared.common.exception;


import com.example.shared.common.ErrorCode;

public class BadRequestException extends RuntimeException {

	private String errorCode = ErrorCode.STORE_0400;

	public BadRequestException(String message) {
		super(message);
	}

	public BadRequestException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}
}
