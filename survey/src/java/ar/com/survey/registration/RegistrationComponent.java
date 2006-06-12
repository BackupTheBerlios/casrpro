package ar.com.survey.registration;

import java.util.Calendar;

import ar.com.survey.model.Person;
import ar.com.survey.model.PersonDAO;
import ar.com.survey.util.Base64;
import ar.com.survey.util.IDbProps;
import ar.com.survey.util.IMailService;

public class RegistrationComponent implements IRegistrationComponent {

	//TODO Seba: implementar el limpiador de regsitraciones vencidad con un TimerThread.
	
	private IMailService emailService;
	private IDbProps dbProps;

	public void register(Person r) throws RegistrationExistsException, PersonExistsException {
		PersonDAO personDAO = new PersonDAO();
		
//		if (personDAO.findBySurrogateKey(r) != null) throw new PersonExistsException();
//		if (registrationDAO.findBySurrogateKey(r) != null) throw new RegistrationExistsException();
		String token = generateToken(r);
		r.setRegistrationDate(Calendar.getInstance());
		r.setToken(token);
		personDAO.createOrUpdate(r);
		sendNotification(r);		
	}
	private void sendNotification(Person r) {
		String emailText = dbProps.getValue("EmailText");
		emailText = emailText.replaceFirst("$link",dbProps.getValue("Url")+"?token="+r.getToken());
		emailService.setEmailReceiver(r.getEmail());
		emailService.setEmailSender(dbProps.getValue("EmailFrom"));
		emailService.setEmailServer(dbProps.getValue("EmailHost"));
		emailService.setEmailSubject(dbProps.getValue("EmailSubject"));
		emailService.setEmailText(emailText);
		emailService.sendEmail();
	}

	private String generateToken(Person r) {
		//String urlToken = dbProps.getValue("Url");
		
		StringBuilder token = new StringBuilder();
		Calendar today = Calendar.getInstance();
		
		token.append(today.get(Calendar.DAY_OF_WEEK));
		token.append(today.get(Calendar.MONTH));
		token.append(today.get(Calendar.YEAR));
		token.append(Math.random());
		token.append(r.getEmail());
		token.append(Math.random());
		
		//urlToken += "?token=" + Base64.encodeObject(token.toString());
		return Base64.encodeObject(token.toString());
		
	}

	public IDbProps getDbProps() {
		return dbProps;
	}

	public void setDbProps(IDbProps dbProps) {
		this.dbProps = dbProps;
	}

	public IMailService getEmailService() {
		return emailService;
	}

	public void setEmailService(IMailService emailService) {
		this.emailService = emailService;
	}
	

}
