package com.example.shared.common.exception;

import com.example.shared.common.ErrorCode;
import lombok.Getter;


public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2407102742288585067L;

	@Getter
	private String errorCode = ErrorCode.STORE_0404;

	public NotFoundException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public NotFoundException(String message) {
		super(message);
	}

}
