package com.ranking.exception;

public class OneTimePasswordException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public OneTimePasswordException(String msgTemplate, Object... args) {
		super(String.format(msgTemplate, args));
	}

}
