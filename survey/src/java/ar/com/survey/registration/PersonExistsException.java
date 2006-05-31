package ar.com.survey.registration;

import ar.com.survey.exceptions.BusinessException;

public class PersonExistsException extends BusinessException {

	private static final long serialVersionUID = 1L;
	public PersonExistsException() {
		super();
	}
	public PersonExistsException(String message) {
		super(message);
	}
	public PersonExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	public PersonExistsException(Throwable cause) {
		super(cause);
	}
}
