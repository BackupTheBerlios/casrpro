package ar.com.survey;

import java.util.Calendar;
import java.util.Collection;

import ar.com.survey.model.Person;
import ar.com.survey.model.Survey;
import ar.com.survey.questions.fields.Field;

public class FillingSurveyStatus {

	public FillingSurveyStatus() {
		super();
	}
	private long filledsurveyID;
    private Calendar initDate;
    private Calendar finishDate;
    private String state;
    private Person person;
    private Survey survey;
    private int currentSection;
	public int getCurrentSection() {
		return currentSection;
	}
	void setCurrentSection(int currentSection) {
		this.currentSection = currentSection;
	}
	
	public long getFilledsurveyID() {
		return filledsurveyID;
	}
	public void setFilledsurveyID(long filledsurveyID) {
		this.filledsurveyID = filledsurveyID;
	}
	public Calendar getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Calendar finishDate) {
		this.finishDate = finishDate;
	}
	public Calendar getInitDate() {
		return initDate;
	}
	public void setInitDate(Calendar initDate) {
		this.initDate = initDate;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public String getState() {
		return state;
	}
	void setState(String state) {
		this.state = state;
	}
	public Survey getSurvey() {
		return survey;
	}
	public void setSurvey(Survey survey) {
		this.survey = survey;
	}

}
