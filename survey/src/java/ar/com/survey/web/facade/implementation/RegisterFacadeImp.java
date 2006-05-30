package ar.com.survey.web.facade.implementation;

import java.util.Calendar;

import ar.com.survey.util.Base64;
import ar.com.survey.util.IDbProps;
import ar.com.survey.util.IMailService;
import ar.com.survey.web.facade.IRegisterFacade;
import ar.com.survey.web.struts.form.RegisterForm;

public class RegisterFacadeImp implements IRegisterFacade {
	
	private IMailService emailService;
	private IDbProps dbProps;

	public boolean isEmailRegistered(String email) {
		
		// TODO: Sebastián aca habria que consultar un dao o similar para ver si ya esta en la base o no...
		
		return false;
	}

	public void registerForm(RegisterForm rf) {
		// Persist register form
		// TODO: Sebastián Implement persistent Person service, fijate que basicamente
		// Hay que persistir los datos del form y a eso sumarle la fecha de registro
		// y el token que te lo genero aca abajo!!
		
		// Generate urlToken
		String urlToken = dbProps.getValue("Url");
		
		StringBuffer token = new StringBuffer();
		Calendar today = Calendar.getInstance();
		
		token.append(today.get(Calendar.DAY_OF_WEEK));
		token.append(today.get(Calendar.MONTH));
		token.append(today.get(Calendar.YEAR));
		token.append(Math.random());
		token.append(rf.getEmail());
		token.append(Math.random());
		
		urlToken += "?token=" + Base64.encodeObject(token.toString());
		
		
		// Send notificacion email
		
		sendNotification(rf.getEmail(), urlToken);
		
	}

	public void sendNotification(String email, String urlToken) {
		String emailText = dbProps.getValue("EmailText");
		int pos = emailText.indexOf("$link");
		emailText = emailText.substring(0, pos) 
			+ urlToken + emailText.substring(pos+5);
		emailService.setEmailReceiver(email);
		emailService.setEmailSender(dbProps.getValue("EmailFrom"));
		emailService.setEmailServer(dbProps.getValue("EmailHost"));
		emailService.setEmailSubject(dbProps.getValue("EmailSubject"));
		emailService.setEmailText(emailText);
		emailService.sendEmail();
	}

	public void setEmailService(IMailService emailService) {
		this.emailService = emailService;
	}

	public void setDbProps(IDbProps dbProps) {
		this.dbProps = dbProps;
	}
	
	
	
	

}
