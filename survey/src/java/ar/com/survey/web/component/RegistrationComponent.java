package ar.com.survey.web.component;

import java.util.Calendar;

import ar.com.survey.model.Registration;
import ar.com.survey.web.struts.form.RegisterForm;

public class RegistrationComponent {

	public RegistrationComponent() {
		
	}
	
	public Registration getRegistrationFromForm(RegisterForm rfm){
		
		Registration registration = new Registration();
		//registration.setBirthDate()
		registration.setEducationLevel(rfm.getEducationLevel());
		registration.setEmail(rfm.getEmail());
		//TODO Sebastian: Reemplazar el isEmployed de registracion por un string que sea tipo de employment
		// registration.setEmployed(rfm.getEmploymentType());
		registration.setFifthAge(Short.parseShort(rfm.getFifthAge()));
		registration.setFifthSex(rfm.getFifthAge());
		registration.setFirstAge(Short.parseShort(rfm.getFirstAge()));
		registration.setFirstName(rfm.getFirstName());
		registration.setFirstSex(rfm.getFirstSex());
		registration.setForthAge(Short.parseShort(rfm.getForthAge()));
		registration.setForthSex(rfm.getForthSex());
		registration.setLastName(rfm.getLastName());
		registration.setMaritalStatus(rfm.getMarritalStatus());
		registration.setOcupation(rfm.getOcupation());
		registration.setPostalCode(rfm.getPostalCode());
		registration.setSecondAge(Short.parseShort(rfm.getSecondAge()));
		registration.setSecondSex(rfm.getSecondSex());
		registration.setSex(rfm.getSex());
		registration.setState(rfm.getState());
		registration.setThirdAge(Short.parseShort(rfm.getThirdAge()));
		registration.setThirdSex(rfm.getThirdSex());
		return registration;
	}

}
