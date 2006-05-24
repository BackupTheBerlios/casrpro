package ar.com.survey.util;

/**
 * 
 * @author cpetronio
 * 
 * This is a java mail interface, supposed to have multiples implementations
 *
 */
public interface IMailService {
	
	public void setEmailServer(String host);
	
	public void setEmailSender(String address);
	
	public void setEmailReceiver(String address);
	
	public void setEmailSubject(String subject);
	
	public void setEmailText(String text);
	
	public void sendEmail();
	

}
