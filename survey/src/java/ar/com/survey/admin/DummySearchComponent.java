package ar.com.survey.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import ar.com.survey.model.Survey;
import ar.com.survey.model.enums.SurveyState;

public class DummySearchComponent implements ISearchComponent {

	public Collection getSurveysByID(String id) {
		ArrayList<Survey> al = new ArrayList<Survey>();
		Survey survey = new Survey();
		survey.setCreationDate(Calendar.getInstance());
		survey.setFinishDate(Calendar.getInstance());
		survey.setName("Survey inicial");
		survey.setStatus(SurveyState.OPEN.getCode());
		al.add(survey);
		
		survey = new Survey();
		survey.setCreationDate(Calendar.getInstance());
		survey.setFinishDate(Calendar.getInstance());
		survey.setName("Survey segundo");
		survey.setStatus(SurveyState.OPEN.getCode());
		al.add(survey);
		
		survey = new Survey();
		survey.setCreationDate(Calendar.getInstance());
		survey.setFinishDate(Calendar.getInstance());
		survey.setName("Survey tercero");
		survey.setStatus(SurveyState.CLOSED.getCode());
		al.add(survey);
		
		return al;
	}

	public Collection getSurveysByName(String name) {
		return getSurveysByID("123");
	}

	public Collection getSurveysByCreationDate(String date) {
		return getSurveysByID("123");
	}

	public Collection getSurveysByStatus(String statusCode) {
		return getSurveysByID("123");
	}

	public Collection getSurveysByFullDescription(String name,
			String date, String statusCode) {
		return getSurveysByID("123");
	}

	public Collection getSurveysByNameAndStatus(String name, String status) {
		return getSurveysByID("123");
	}

	public Collection getSurveysByNameAndCreationDate(String name, String creationDate) {
		return getSurveysByID("123");
	}

	public Collection getSurveysByCreationDateAndStatus(String creationDate, String status) {
		return getSurveysByID("123");
	}

}
