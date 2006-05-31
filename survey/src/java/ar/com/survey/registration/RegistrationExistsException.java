package ar.com.survey.registration;

import ar.com.survey.exceptions.BusinessException;

public class RegistrationExistsException extends BusinessException {

	private static final long serialVersionUID = 1L;
	public RegistrationExistsException() {
		super();
	}
	public RegistrationExistsException(String message) {
		super(message);
	}
	public RegistrationExistsException(String message, Throwable cause) {
		super(message, cause);
	}
	public RegistrationExistsException(Throwable cause) {
		super(cause);
	}
}
