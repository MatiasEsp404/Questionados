package com.ntt.questionados.config.exception.runtime;

@SuppressWarnings("serial")
public class UserAlreadyExistException extends RuntimeException {

	public UserAlreadyExistException(String message) {
		super(message);
	}

}
