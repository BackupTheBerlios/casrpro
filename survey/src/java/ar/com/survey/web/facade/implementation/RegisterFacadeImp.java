package ar.com.survey.web.facade.implementation;

import ar.com.survey.util.IDbProps;
import ar.com.survey.util.IMailService;
import ar.com.survey.web.facade.IRegisterFacade;
import ar.com.survey.web.struts.form.RegisterForm;

public class RegisterFacadeImp implements IRegisterFacade {
	
	private IMailService emailService;
	private IDbProps dbProps;

	public boolean isEmailRegistered(String email) {
		return false;
	}

	public void registerForm(RegisterForm rf) {
		// Persist register form
		// TODO: Implement persistent Person service
		
		// Generate urlToken
		String urlToken = dbProps.getValue("Url");
		// TODO: Implement random and base64 logic here
		
		// Send notificacion email
		
		sendNotification(rf.getEmail(), urlToken);
		
	}

	public void sendNotification(String email, String urlToken) {
		emailService.setEmailReceiver(email);
		emailService.setEmailSender(dbProps.getValue("EmailFrom"));
		emailService.setEmailServer(dbProps.getValue("EmailHost"));
		emailService.setEmailSubject(dbProps.getValue("EmailSubject"));
		emailService.setEmailText(dbProps.getValue("EmailText").replaceAll("$link", urlToken));
		emailService.sendEmail();
	}

	public void setEmailService(IMailService emailService) {
		this.emailService = emailService;
	}

	public void setDbProps(IDbProps dbProps) {
		this.dbProps = dbProps;
	}
	
	
	
	

}
