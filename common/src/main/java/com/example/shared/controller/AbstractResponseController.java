package com.example.shared.controller;

import com.example.shared.common.CallbackFunction;
import com.example.shared.common.exception.BadRequestException;
import com.example.shared.common.exception.ForbiddenException;
import com.example.shared.common.exception.NotFoundException;
import com.example.shared.common.exception.UnauthorizedException;
import com.example.shared.common.response.ResponseEntites;
import com.example.shared.common.ConstantString;
import com.example.shared.common.ErrorCode;
import com.example.shared.common.context.DataContextHelper;
import com.example.shared.common.model.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ForkJoinPool;

public abstract class AbstractResponseController {

	private final ResponseEntites<Object> responseEntites;
	private final DataContextHelper dataContextHelper;

	@Autowired
	protected AbstractResponseController(ResponseEntites<Object> responseEntites, DataContextHelper dataContextHelper) {
		this.responseEntites = responseEntites;
		this.dataContextHelper = dataContextHelper;
	}

	public DeferredResult<ResponseEntity<?>> responseEntityDeferredResult(CallbackFunction<?> callbackFunction) {
		DeferredResult<ResponseEntity<?>> deferredResult = new DeferredResult<>(ConstantString.TIMEOUT_NONBLOCK);
		deferredResult.onTimeout(() -> deferredResult.setErrorResult(
				responseEntites.createErrorResponse(HttpStatus.REQUEST_TIMEOUT, ErrorCode.STORE_0408)));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ForkJoinPool.commonPool().submit(() -> {
			try {
				SecurityContextHolder.getContext().setAuthentication(authentication);
				dataContextHelper.init(authentication);
				deferredResult.setResult(responseEntites.createSuccessResponse(HttpStatus.OK,
						new ApiResult<>(ErrorCode.STORE_0000, callbackFunction.execute())));
			} catch (NotFoundException ex) {
				deferredResult.setResult(responseEntites.createErrorResponse(HttpStatus.NOT_FOUND,
						ex.getErrorCode(), ex.getMessage().trim()));
			} catch (BadRequestException ex) {
				deferredResult.setResult(responseEntites.createErrorResponse(HttpStatus.BAD_REQUEST,
						ex.getErrorCode(), ex.getMessage().trim()));
			} catch (UnauthorizedException ex) {
				deferredResult.setResult(responseEntites.createErrorResponse(HttpStatus.UNAUTHORIZED,
						ErrorCode.STORE_0401, ex.getMessage().trim()));
			} catch (ForbiddenException ex) {
				deferredResult.setResult(responseEntites.createErrorResponse(HttpStatus.FORBIDDEN,
						ErrorCode.STORE_0403, ex.getMessage().trim()));
			} catch (IllegalArgumentException ex) {
				ex.printStackTrace();
				deferredResult.setErrorResult(responseEntites.createErrorResponse(HttpStatus.BAD_REQUEST,
						ErrorCode.STORE_0400, ex.getMessage().trim()));
			} catch (Exception e) {
				e.printStackTrace();
				deferredResult.setErrorResult(responseEntites.createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR,
						ErrorCode.STORE_0500, e.getMessage()));
			} finally {
				dataContextHelper.clear();
			}
		});
		return deferredResult;
	}

}
