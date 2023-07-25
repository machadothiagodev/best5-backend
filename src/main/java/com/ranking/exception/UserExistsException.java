package com.ranking.exception;

public class UserExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserExistsException(String msgTemplate, Object... args) {
		super(String.format(msgTemplate, args));
	}

}
