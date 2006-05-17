package ar.com.survey.web.facade;

public interface IRegisterFacade {
	
	public boolean isEmailRegistered(String email);
	
	public void registerForm();
	
	public void sendNotification(String email);
	

}
