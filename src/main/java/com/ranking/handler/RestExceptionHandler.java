package com.ranking.handler;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ranking.exception.AuthenticationException;
import com.ranking.exception.UserExistsException;

@ControllerAdvice
public class RestExceptionHandler {

	@ResponseBody
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(HttpServletRequest req, MethodArgumentNotValidException ex) {
		String mensagem = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
		return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
	}

	@ResponseBody
	@ExceptionHandler(value = AuthenticationException.class)
	public ResponseEntity<MessageError> handleAuthenticationException(HttpServletRequest req, AuthenticationException ex) {
		return new ResponseEntity<>(new MessageError(ex.getLocalizedMessage()), HttpStatus.UNAUTHORIZED);
	}

	@ResponseBody
	@ExceptionHandler(value = UserExistsException.class)
	public ResponseEntity<MessageError> handleUserExistsException(HttpServletRequest req, UserExistsException ex) {
		return new ResponseEntity<>(new MessageError(ex.getLocalizedMessage()), HttpStatus.BAD_REQUEST);
	}

	private class MessageError {
		private String message;

		public MessageError(String message) {
			this.message = message;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}
	}

}