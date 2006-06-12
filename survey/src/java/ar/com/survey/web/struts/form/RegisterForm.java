package ar.com.survey.web.struts.form;

import org.apache.struts.action.ActionForm;

public class RegisterForm extends ActionForm {

	/**
	 * Required by Eclipse
	 */
	private static final long serialVersionUID = 1L;
	
	/** form fields **/
	
	private String email;
	private String emailConfirm;
	private String firstName;
	private String lastName;
	private String state;
	private String postalCode;
	private String birthDay, birthMonth, birthYear;
	private String sex;
	private String maritalStatus;
	private String employmentType;
	private String educationLevel;
	private String ocupation;
	private String firstAge, secondAge, thirdAge, forthAge, fifthAge;
	private String firstSex, secondSex, thirdSex, forthSex, fifthSex;
	private boolean acceptTerms;
	
	// Dispatch Action method property
	private String method;
	
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
	public String getFifthSex() {
		return fifthSex;
	}
	public void setFifthSex(String fifithSex) {
		this.fifthSex = fifithSex;
	}
	public String getFifthAge() {
		return fifthAge;
	}
	public void setFifthAge(String fifthAge) {
		this.fifthAge = fifthAge;
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
	public void setFirstSex(String fistSex) {
		this.firstSex = fistSex;
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
	public String getEmploymentType() {
		return employmentType;
	}
	public void setEmploymentType(String isEmployed) {
		this.employmentType = isEmployed;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
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
	public boolean isAcceptTerms() {
		return acceptTerms;
	}
	public void setAcceptTerms(boolean acceptTerms) {
		this.acceptTerms = acceptTerms;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	
	
	
	
	

}
