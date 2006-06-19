package ar.com.survey.registration;

import ar.com.survey.model.Person;

public interface IRegistrationComponent {
	/**
	 * Registers a person. An email will be sent.
	 * Preconditions: all required fields in R (primary key + not nulls i DB) are filled.
	 * Post: these fields will be filled by this componente: Token, RegistrationDate 
	 * 
	 * @param r required not null
	 * @throws RegistrationExistsException if someone already posted the registration, but did not confirmed it yet.
	 * @throws PersonExistsException if someone posted and confirmed the registration.
	 */
	void register(Person r) throws RegistrationExistsException, PersonExistsException;

	/** Confirms a registration.
	 * @param token required not null
	 * @throws InvalidTokenException if the token is not present in the DB 
	 * @throws PersonExistsException if the token is present, but already confirmed (duplicate confirmation, maybe)
	 */
	void confirmRegistration(String token) throws InvalidTokenException, PersonExistsException;
}