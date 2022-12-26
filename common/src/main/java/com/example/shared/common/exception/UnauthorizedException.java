package com.example.shared.common.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2407102742288585068L;

	public UnauthorizedException(String message) {
		super(message);
	}
}
