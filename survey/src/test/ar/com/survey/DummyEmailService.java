package ar.com.survey;

import ar.com.survey.util.IMailService;

public class DummyEmailService implements IMailService {

	public DummyEmailService() {
		super();

	}

	public void setEmailServer(String host) {
	}

	public void setEmailSender(String address) {
	}

	public void setEmailReceiver(String address) {
	}

	public void setEmailSubject(String subject) {
	}

	public void setEmailText(String text) {
	}

	public void sendEmail() {
	}

	public void sendEmailWithAuth() {
		
	}

	public void setAuthValues(String username, String password) {
		
	}

}
