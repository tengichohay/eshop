package com.example.shared.common.exception;

import com.example.shared.common.ErrorCode;
import com.example.shared.common.exception.model.ApiError;
import com.example.shared.common.exception.model.MessageField;
import com.example.shared.common.exception.model.MessageObject;
import com.example.shared.service.DBErrorMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {

	private final MessageSource msgSource;
	private final DBErrorMessage dbErrorMessage;

	public ExceptionController(MessageSource msgSource, DBErrorMessage dbErrorMessage) {
		this.msgSource = msgSource;
		this.dbErrorMessage = dbErrorMessage;
	}

	@Override
	@NonNull
	protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, @NonNull final HttpHeaders headers,
																  @NonNull final HttpStatus status, @NonNull final WebRequest request) {
		ApiError apiError;
		List<MessageField> messageFields = new ArrayList<>();
		List<MessageObject> msgObjects = new ArrayList<>();
		Locale currentLocale = LocaleContextHolder.getLocale();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			String fieldName = error.getField();
			String msgError;
			try {
				String messageExpression = error.unwrap(ConstraintViolation.class).getMessageTemplate().trim().split("[{}]")[1];
				msgError = dbErrorMessage.resolveMessage(messageExpression, request.getLocale()).toString();
			} catch (Exception e) {
				msgError = error.getDefaultMessage();
			}
			MessageField msg = new MessageField(fieldName, msgError);
			messageFields.add(msg);

		}
		for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {

			String fieldName = error.getObjectName();
			String msgError = "";
			try {
				msgError = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
			} catch (Exception e) {
				msgError = error.getDefaultMessage();
			}
			MessageObject msg = new MessageObject(fieldName, msgError);
			msgObjects.add(msg);
		}
		apiError = new ApiError(messageFields, msgObjects, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
														 final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		ApiError apiError = null;
		List<MessageField> messageFields = new ArrayList<>();
		List<MessageObject> msgObjecs = new ArrayList<>();
		Locale currentLocale = LocaleContextHolder.getLocale();
		for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
			String fieldName = error.getField();
			String msgError = "";
			try {
				msgError = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
			} catch (Exception e) {
				msgError = error.getDefaultMessage();
			}
			MessageField msg = new MessageField(fieldName, msgError);
			messageFields.add(msg);

		}
		for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
			String fieldName = error.getObjectName();
			String msgError = "";
			try {
				msgError = msgSource.getMessage(error.getDefaultMessage(), null, currentLocale);
			} catch (Exception e) {
				msgError = error.getDefaultMessage();
			}
			MessageObject msg = new MessageObject(fieldName, msgError);
			msgObjecs.add(msg);
		}
		apiError = new ApiError(messageFields, msgObjecs, HttpStatus.BAD_REQUEST);

		return new ResponseEntity<Object>(apiError, HttpStatus.BAD_REQUEST);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
														final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorCode.STORE_0400, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
																	 final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		final String error = ex.getClass().getName() + " :" + ex.getRequestPartName() + " part is missing";
		logger.error(error);
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorCode.STORE_0400, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(final MissingServletRequestParameterException ex,
																		  final HttpHeaders headers, final HttpStatus status,
																		  final WebRequest request) {
		final String error = ex.getClass().getName() + " :" + ex.getParameterName() + " parameter is missing";
		logger.error(error);
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorCode.STORE_0400, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}


	@ExceptionHandler({MethodArgumentTypeMismatchException.class})
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
																   final WebRequest request) {
		final String error = ex.getClass().getName() + " :" + ex.getName() + " should be of type " + ex.getRequiredType().getName();
		logger.error(error);
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorCode.STORE_0400, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex, final WebRequest request) {
		final List<MessageField> messageFields = new ArrayList<>();
		for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
			for (Path.Node path : violation.getPropertyPath()) {
				if (javax.validation.ElementKind.PARAMETER == path.getKind()) {
					messageFields.add(new MessageField(path.getName(), violation.getMessage()));
				}
			}
		}
		final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ErrorCode.STORE_0600, messageFields, new ArrayList<>());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 404
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
																   final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
		final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ErrorCode.STORE_0404, error);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 405
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
																		 final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append(" method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ErrorCode.STORE_0405, builder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 415
	@Override
	protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
																	 final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
		logger.info(ex.getClass().getName());
		final StringBuilder builder = new StringBuilder();
		builder.append(ex.getContentType());
		builder.append(" media type is not supported. Supported media types are ");
		ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));
		final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ErrorCode.STORE_0415, builder.toString());
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 403
	@ExceptionHandler({AccessDeniedException.class})
	public ResponseEntity<Object> handleAccessDeniedException(final AccessDeniedException ex,
															  final WebRequest request) {
		final String message = ex.getMessage();
		logger.error(message);
		final ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, ErrorCode.STORE_0403, message);
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	// 500
	@ExceptionHandler({Exception.class})
	public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
		final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.STORE_0500,
				ex.getLocalizedMessage().substring(0, Math.min(ex.getLocalizedMessage().length(), 100)));
		return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}

	@ExceptionHandler(NotFoundException.class)
	void handleIllegalArgumentException(NotFoundException e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.NOT_FOUND.value());
	}

	@ExceptionHandler({BadRequestException.class})
	protected ResponseEntity<Object> handleServletRequestBindingException(final ServletRequestBindingException ex, final HttpHeaders headers,
																		  final HttpStatus status, final WebRequest request) {
		final ApiError apiError = new ApiError(status, ErrorCode.STORE_0400, ex.getMessage());
		return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
	}
}
