package ar.com.survey.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * 
 * @author cpetronio
 *
 * Java Mail simple implementation, sends a simple text message
 *
 */
public class MailServiceImpl implements IMailService {

	private static final Logger log = Logger.getLogger(MailServiceImpl.class);
	
	private String from;
	private String to;
	private String subject;
	private String txtMessage;
	private String host;

	public MailServiceImpl(){
		
	}
	
	public void setEmailServer(String host) {
		this.host = host;
	}

	public void setEmailSender(String address) {
		this.from = address;

	}

	public void setEmailReceiver(String address) {
		this.to = address;

	}

	public void setEmailSubject(String subject) {
		this.subject = subject;

	}

	public void setEmailText(String text) {
		this.txtMessage = text;
	}

	public void sendEmail() {
		
		// Get system properties
		Properties props = System.getProperties();
		// Setup mail server
		props.put("mail.smtp.host", host);
		// Get session
		Session session = Session.getDefaultInstance(props, null);
		// Define message
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, 
			  new InternetAddress(to));
			message.setSubject(subject);
			message.setText(txtMessage);
			// Send message
			Transport.send(message);
		}
		catch (MessagingException me){
			log.error(me.toString());
		}

	}

}
