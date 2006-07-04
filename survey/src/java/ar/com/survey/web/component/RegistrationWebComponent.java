package ar.com.survey.web.component;

import java.util.Calendar;

import ar.com.survey.model.Person;
import ar.com.survey.web.struts.form.RegisterForm;

public class RegistrationWebComponent {

	public RegistrationWebComponent() {
		
	}
	
	public Person getRegistrationFromForm(RegisterForm rfm){
		
		Person person = new Person();
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(rfm.getBirthDay()));
		calendar.set(Calendar.MONTH, Integer.parseInt(rfm.getBirthMonth()));
		calendar.set(Calendar.YEAR, Integer.parseInt(rfm.getBirthYear()));
		person.setFirstName(rfm.getFirstName());
		person.setLastName(rfm.getLastName());
		person.setBirthDate(calendar);
		person.setEducationLevel(rfm.getEducationLevel());
		person.setEmail(rfm.getEmail());
		person.setMaritalStatus(rfm.getMaritalStatus());
		person.setOcupation(rfm.getOcupation());
		person.setPostalCode(rfm.getPostalCode());
		person.setSex(rfm.getSex());
		person.setState(rfm.getState());
		//TODO Sebastián: Reemplazar el isEmployed de registracion por un string que sea tipo de employment
		// registration.setEmployed(rfm.getEmploymentType());
		
		// check for the different kids, if they exist persist each
		
		// TODO Sebastián: Remover secondName, thirdName, forthName, FifthName
		// ya que el form ultimo no los pide
		
		if(rfm.getFirstAge()!=null && !rfm.getFirstAge().equals("")){
			person.setFirstAge(Short.parseShort(rfm.getFirstAge()));
			person.setFirstSex(rfm.getFirstSex());
		}
		
		if(rfm.getSecondAge()!=null && !rfm.getSecondAge().equals("")){
			person.setSecondAge(Short.parseShort(rfm.getSecondAge()));
			person.setSecondSex(rfm.getSecondSex());	
		}
		
		if(rfm.getThirdAge()!=null && !rfm.getThirdAge().equals("")){
			person.setThirdAge(Short.parseShort(rfm.getThirdAge()));
			person.setThirdSex(rfm.getThirdSex());	
		}
		
		if(rfm.getForthAge()!=null && !rfm.getForthAge().equals("")){
			person.setForthAge(Short.parseShort(rfm.getForthAge()));
			person.setForthSex(rfm.getForthSex());
		}
		
		if(rfm.getFifthAge()!=null && !rfm.getFifthAge().equals("")){
			person.setFifthAge(Short.parseShort(rfm.getFifthAge()));
			person.setFifthSex(rfm.getFifthAge());
		}
		
		return person;
	}

}
