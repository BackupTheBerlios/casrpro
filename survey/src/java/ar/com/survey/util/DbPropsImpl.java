package ar.com.survey.util;

import java.util.Properties;

public class DbPropsImpl implements IDbProps {

	private Properties props;
	
	public DbPropsImpl(){
		String emailText = new String("Para confirmar su registración "
				+ "vaya a este link $link");
		props = new Properties();
		props.setProperty("EmailHost", "localhost");
		props.setProperty("EmailUser", "cpetronio");
		props.setProperty("EmailPass", "carspro");
		props.setProperty("EmailText", emailText);
		props.setProperty("EmailFrom", "cpetronio@carspro.com");
		props.setProperty("EmailSubject", "Envío de confirmación");
		props.setProperty("Url", "http://localhost:8080/survey/registerAction.do");
	}
	
	public String getValue(String key) {
		return props.getProperty(key);
	}

}
