package ar.com.survey.web.facade;

import ar.com.survey.web.struts.form.RegisterForm;

public interface IRegisterFacade {
	
	/**
	 * 
	 * @param email pk to search for
	 * @return true if found, false else
	 * 
	 * This method looks up the email in the database
	 * to verify if it is already registered or not
	 * 
	 */
	public boolean isEmailRegistered(String email);
	
	/**
	 * 
	 * @param rf struts RegisterForm
	 * 
	 * This method persists the required form to the
	 * database
	 */
	public void registerForm(RegisterForm rf);
	
	/**
	 * 
	 * @param email address to send register message
	 * 
	 */
	public void sendNotification(String email, String urlToken);
	

}
