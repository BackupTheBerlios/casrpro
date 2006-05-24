package ar.com.survey.model;

import java.io.Serializable;

/**
 * 
 */
public abstract class PersonAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	private String email;
	private String emailConfirm;
	private String firstName;
	private String lastName;
	private String state;
	private String postalCode;
	private String birthDay, birthMonth, birthYear;
	private String sex;
	private String married;
	private String isEmployed;
	private String educationLevel;
	private String ocupation;
	private String firstAge, secondAge, thirdAge, forthAge, fifthAge;
	private String firstSex, secondSex, thirdSex, forthSex, fifthSex;
	private boolean acceptTerms;
	
	
}


