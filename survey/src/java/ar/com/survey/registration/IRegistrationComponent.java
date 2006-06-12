package ar.com.survey.registration;

import ar.com.survey.model.Person;

public interface IRegistrationComponent {
	void register(Person r) throws RegistrationExistsException, PersonExistsException;
}