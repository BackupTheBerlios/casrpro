package ar.com.survey.model;

import java.io.Serializable;
import java.util.Calendar;

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
	private Calendar regDate;
	private String token;
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public boolean isAcceptTerms() {
		return acceptTerms;
	}
	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}
	public String getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(String birthDay) {
		this.birthDay = birthDay;
	}
	public String getBirthMonth() {
		return birthMonth;
	}
	public void setBirthMonth(String birthMonth) {
		this.birthMonth = birthMonth;
	}
	public String getBirthYear() {
		return birthYear;
	}
	public void setBirthYear(String birthYear) {
		this.birthYear = birthYear;
	}
	public String getEducationLevel() {
		return educationLevel;
	}
	public void setEducationLevel(String educationLevel) {
		this.educationLevel = educationLevel;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailConfirm() {
		return emailConfirm;
	}
	public void setEmailConfirm(String emailConfirm) {
		this.emailConfirm = emailConfirm;
	}
	public String getFifthAge() {
		return fifthAge;
	}
	public void setFifthAge(String fifthAge) {
		this.fifthAge = fifthAge;
	}
	public String getFifthSex() {
		return fifthSex;
	}
	public void setFifthSex(String fifthSex) {
		this.fifthSex = fifthSex;
	}
	public String getFirstAge() {
		return firstAge;
	}
	public void setFirstAge(String firstAge) {
		this.firstAge = firstAge;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstSex() {
		return firstSex;
	}
	public void setFirstSex(String firstSex) {
		this.firstSex = firstSex;
	}
	public String getForthAge() {
		return forthAge;
	}
	public void setForthAge(String forthAge) {
		this.forthAge = forthAge;
	}
	public String getForthSex() {
		return forthSex;
	}
	public void setForthSex(String forthSex) {
		this.forthSex = forthSex;
	}
	public String getIsEmployed() {
		return isEmployed;
	}
	public void setIsEmployed(String isEmployed) {
		this.isEmployed = isEmployed;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMarried() {
		return married;
	}
	public void setMarried(String married) {
		this.married = married;
	}
	public String getOcupation() {
		return ocupation;
	}
	public void setOcupation(String ocupation) {
		this.ocupation = ocupation;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public Calendar getRegDate() {
		return regDate;
	}
	public void setRegDate(Calendar regDate) {
		this.regDate = regDate;
	}
	public String getSecondAge() {
		return secondAge;
	}
	public void setSecondAge(String secondAge) {
		this.secondAge = secondAge;
	}
	public String getSecondSex() {
		return secondSex;
	}
	public void setSecondSex(String secondSex) {
		this.secondSex = secondSex;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getThirdAge() {
		return thirdAge;
	}
	public void setThirdAge(String thirdAge) {
		this.thirdAge = thirdAge;
	}
	public String getThirdSex() {
		return thirdSex;
	}
	public void setThirdSex(String thirdSex) {
		this.thirdSex = thirdSex;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	
}


