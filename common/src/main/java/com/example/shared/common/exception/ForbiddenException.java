package com.example.shared.common.exception;

public class ForbiddenException extends RuntimeException {

	private static final long serialVersionUID = 2407102742288585069L;

	public ForbiddenException(String message) {
		super(message);
	}

}
