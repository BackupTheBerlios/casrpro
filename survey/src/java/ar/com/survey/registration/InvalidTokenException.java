package ar.com.survey.registration;

import ar.com.survey.exceptions.BusinessException;

public class InvalidTokenException extends BusinessException {

	private static final long serialVersionUID = 1L;
	public InvalidTokenException() {
		super();
	}
	public InvalidTokenException(String message) {
		super(message);
	}
	public InvalidTokenException(String message, Throwable cause) {
		super(message, cause);
	}
	public InvalidTokenException(Throwable cause) {
		super(cause);
	}
}
