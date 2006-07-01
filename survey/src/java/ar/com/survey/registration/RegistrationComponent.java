package ar.com.survey.registration;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.List;

import ar.com.survey.model.Person;
import ar.com.survey.model.PersonDAO;
import ar.com.survey.util.Base64;
import ar.com.survey.util.IDbProps;
import ar.com.survey.util.IMailService;

public class RegistrationComponent implements IRegistrationComponent {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(RegistrationComponent.class);

	
	private IMailService emailService;
	private IDbProps dbProps;

	public void register(Person r) throws RegistrationExistsException, PersonExistsException {
		PersonDAO personDAO = new PersonDAO();
		
		Person personInDB = personDAO.findBySurrogateKey(r);
		
		if (personInDB != null) {
			if (personInDB.getRegistrationConfirmedDate() != null) {				
				throw new PersonExistsException();				
			} else {
				throw new RegistrationExistsException();
			}			
		}
		r.setRegistrationDate(Calendar.getInstance());
		r.setRegistrationConfirmedDate(null);
		r.setToken(generateToken(r));
		personDAO.createOrUpdate(r);
		sendNotification(r);		
	}
	public boolean confirmRegistration(String token) {
		if ((token == null) || (token.trim().length() == 0)) {
			return false;
		}
		PersonDAO personDAO = new PersonDAO();
		Person p = new Person();
		p.setToken(token);
		List<Person> foundList = personDAO.findByExample(p);
		if (foundList.size() == 0) {
			//token not found
			return false;
		} else if (foundList.size() > 1) {
			//more that one token!!
			//remove duplicates and log error
			logger.error("confirmRegistration(String) Illegal data: more than one token detected");
			while (foundList.size() != 1) {
				logger.error("deleting Person: "+p);
				personDAO.delete(foundList.get(0));
				foundList.remove(0);
			}
		}
		p = foundList.get(0);
		if (p.getRegistrationConfirmedDate() != null) {
			throw new PersonExistsException();
		} else {
			p.setRegistrationConfirmedDate(Calendar.getInstance());
			return true;
		}
		
		
	
	}
	private void sendNotification(Person r) {
		String emailText = dbProps.getValue("EmailText");
		int pos = emailText.indexOf("$link");
		emailText = emailText.substring(0, pos)
			+ dbProps.getValue("Url")+"&token="+r.getToken()
			+ emailText.substring(pos+5);
		emailService.setEmailReceiver(r.getEmail());
		emailService.setEmailSender(dbProps.getValue("EmailFrom"));
		emailService.setEmailServer(dbProps.getValue("EmailHost"));
		emailService.setEmailSubject(dbProps.getValue("EmailSubject"));
		emailService.setEmailText(emailText);
		emailService.sendEmail();
	}

	private String generateToken(Person r) {
		
		StringBuilder token = new StringBuilder();
//		Calendar today = Calendar.getInstance();
//		token.append(today.get(Calendar.DAY_OF_WEEK));
//		token.append(today.get(Calendar.MONTH));
//		token.append(today.get(Calendar.YEAR));
		token.append(Math.random());
		token.append(r.getEmail().hashCode());
		token.append(System.currentTimeMillis());
		
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
