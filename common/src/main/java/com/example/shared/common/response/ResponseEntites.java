package com.example.shared.common.response;

import com.example.shared.common.exception.model.ApiError;
import com.example.shared.common.json.JsonUtils;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
public class ResponseEntites<T> {

	private int status;

	private String message;

	private T data;

	public ResponseEntites() {
		super();
	}

	public ResponseEntites(HttpStatus status, String message, T data) {
		this.status = status.value();
		this.message = message;
		this.data = data;
	}

	public ResponseEntity<?> createSuccessResponse(HttpStatus status) {
		return new ResponseEntity<>(status);
	}

	public ResponseEntity<?> createSuccessResponse(HttpStatus status, T data) {
		return new ResponseEntity<>(data, status);
	}

	public ResponseEntity<?> createErrorResponse(HttpStatus status, T data) {
		log.error("[RESPONSE_ERROR] Error: {}", JsonUtils.toJson(data));
		return new ResponseEntity<>(data, status);
	}

	public ResponseEntity<?> createErrorResponse(HttpStatus status, final String errorMessage) {
		final ApiError apiError = new ApiError(status, errorMessage);
		log.error("[RESPONSE_ERROR] Error: {}", JsonUtils.toJson(apiError));
		return new ResponseEntity<>(apiError, status);
	}

	public ResponseEntity<?> createErrorResponse(final HttpStatus status, final String errorMessage, final String responseData) {
		final ApiError apiError = new ApiError(status, errorMessage, responseData);
		log.error("[RESPONSE_ERROR] Error: {}", JsonUtils.toJson(apiError));
		return new ResponseEntity<>(apiError, status);
	}

}