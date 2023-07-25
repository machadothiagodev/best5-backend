package com.ranking.exception;

public class AuthenticationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AuthenticationException(String msgTemplate, Object... args) {
		super(String.format(msgTemplate, args));
	}

}
