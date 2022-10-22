package com.ntt.questionados.config.exception;

import com.ntt.questionados.config.exception.runtime.EntityNotFoundException;
import com.ntt.questionados.config.exception.runtime.InvalidCredentialsException;
import com.ntt.questionados.config.exception.runtime.UserAlreadyExistException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ntt.questionados.config.security.common.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = EntityNotFoundException.class)
	protected ResponseEntity<ErrorResponse> handleEntityNotFound(EntityNotFoundException e) {
		ErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, "Entity not found.", e.getMessage());
		return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, "Invalid input data.",
				e.getBindingResult().getFieldErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
						.collect(Collectors.toList()));
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = UserAlreadyExistException.class)
	protected ResponseEntity<ErrorResponse> handleUserAlreadyExistException(UserAlreadyExistException e) {
		ErrorResponse errorResponse = buildErrorResponse(HttpStatus.CONFLICT, e.getMessage(),
				"The server could not complete the user registration because "
						+ "the email address entered is already in use.");
		return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(value = InvalidCredentialsException.class)
	protected ResponseEntity<ErrorResponse> handleInvalidCredentialsException(InvalidCredentialsException e) {
		ErrorResponse errorResponse = buildErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage(),
				"The server cannot return a response due to invalid credentials.");
		return new ResponseEntity<>(errorResponse, HttpStatus.UNAUTHORIZED);

	}

	private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message, List<String> moreInfo) {
		return ErrorResponse.builder().statusCode(httpStatus.value()).message(message).moreInfo(moreInfo).build();
	}

	private ErrorResponse buildErrorResponse(HttpStatus httpStatus, String message, String moreInfo) {
		return buildErrorResponse(httpStatus, message, List.of(moreInfo));
	}

}