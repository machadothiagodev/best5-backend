package com.ranking.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException(String msgTemplate, Object... args) {
		super(String.format(msgTemplate, args));
	}

}
