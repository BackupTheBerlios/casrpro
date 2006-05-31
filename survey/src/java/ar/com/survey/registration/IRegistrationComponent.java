package ar.com.survey.registration;

import ar.com.survey.model.Registration;

public interface IRegistrationComponent {
	void register(Registration r) throws RegistrationExistsException, PersonExistsException;
}