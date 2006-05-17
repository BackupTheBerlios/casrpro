package ar.com.survey.web.struts.form;

import org.apache.struts.action.ActionForm;

public class RegisterForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** form fields **/
	
	private String email;
	private String emailConfirm;
	private String firstName;
	private String lastName;
	private String state;
	private int postalCode;
	private String birthDay, birthMonth, birthYear;
	private String sex;
	private String married;
	private String isEmployed;
	private String educationLevel;
	private String ocupation;
	private int firstAge, secondAge, thirdAge, forthAge, fifthAge;
	private String fistSex, secondSex, thirdSex, forthSex, fifithSex;
	
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
	public String getFifithSex() {
		return fifithSex;
	}
	public void setFifithSex(String fifithSex) {
		this.fifithSex = fifithSex;
	}
	public int getFifthAge() {
		return fifthAge;
	}
	public void setFifthAge(int fifthAge) {
		this.fifthAge = fifthAge;
	}
	public int getFirstAge() {
		return firstAge;
	}
	public void setFirstAge(int firstAge) {
		this.firstAge = firstAge;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFistSex() {
		return fistSex;
	}
	public void setFistSex(String fistSex) {
		this.fistSex = fistSex;
	}
	public int getForthAge() {
		return forthAge;
	}
	public void setForthAge(int forthAge) {
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
	public int getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}
	public int getSecondAge() {
		return secondAge;
	}
	public void setSecondAge(int secondAge) {
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
	public int getThirdAge() {
		return thirdAge;
	}
	public void setThirdAge(int thirdAge) {
		this.thirdAge = thirdAge;
	}
	public String getThirdSex() {
		return thirdSex;
	}
	public void setThirdSex(String thirdSex) {
		this.thirdSex = thirdSex;
	}
	
	
	
	

}
