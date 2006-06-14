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

	private String username, password;

	public MailServiceImpl() {

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
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
			message.setText(txtMessage);
			// Send message
			Transport.send(message);
			log.info("Message sent to: " + to);
		} catch (MessagingException me) {
			log.error(me.toString());
		}

	}

	public void sendEmailWithAuth() {

		// Get system properties
		Properties props = System.getProperties();
		// Setup mail server
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");

		// Get session
		Session session = Session.getDefaultInstance(props, null);
		// Define message
		MimeMessage message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject(subject);
			message.setText(txtMessage);

			// Send message with smtp auth
			Transport transport = session.getTransport("smtp");
			transport.connect(host, username, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			log.info("Message sent to: " + to);
		} catch (MessagingException me) {
			log.error(me.toString());
		}

	}

	public void setAuthValues(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
