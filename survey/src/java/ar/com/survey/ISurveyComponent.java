package ar.com.survey;

import ar.com.survey.model.Survey;

public interface ISurveyComponent {
	
	public void createSurvey(Survey s);
	
	public void updateSurvey(Survey s);
	
	public void deleteSurvey(Survey s);

}