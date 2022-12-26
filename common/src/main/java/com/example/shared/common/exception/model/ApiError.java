package com.example.shared.common.exception.model;

import com.example.shared.common.json.JsonUtils;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Data
public class ApiError {
	private List<MessageObject> messageObjects;
	private List<MessageField> messageFields;
	private Integer statusCode;
	private HttpStatus status;
	private String message;
	private String error;

	public ApiError() {
		super();
	}

	public ApiError(List<MessageField> messageFields, List<MessageObject> messageObjects, HttpStatus status) {
		super();
		this.messageObjects = messageObjects;
		this.messageFields = messageFields;
		this.message = "";
		this.status = status;
		this.statusCode = status.value();
		this.error = "";
	}

	public ApiError(HttpStatus status, String message, List<MessageField> messageFields,
					List<MessageObject> messageObjects) {
		super();
		this.messageObjects = messageObjects;
		this.messageFields = messageFields;
		this.message = message;
		this.statusCode = status.value();
		this.status = status;
		this.error = "";
	}

	public ApiError(HttpStatus status, String message) {
		super();
		this.messageObjects = new ArrayList<>();
		this.messageFields = new ArrayList<>();
		this.message = message;
		this.statusCode = status.value();
		this.status = status;
		this.error = "";
	}

	public ApiError(HttpStatus status, String message, String error) {
		super();
		this.messageObjects = new ArrayList<>();
		this.messageFields = new ArrayList<>();
		this.message = message;
		this.statusCode = status.value();
		this.status = status;
		this.error = error;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
