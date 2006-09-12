package ar.com.survey.admin;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import ar.com.survey.admin.db.CustomSurveyDAO;
import ar.com.survey.model.Survey;
import ar.com.survey.util.Transformer;

public class SearchComponent implements ISearchComponent {

	private CustomSurveyDAO surveyDAO;

	public Collection getSurveysByName(String name) {
		Survey surv = new Survey();
		surv.setName(name);
		ArrayList<Survey> list = new ArrayList<Survey>();
		surv = surveyDAO.findBySurrogateKey(surv);
		if (surv != null)
			list.add(surv);
		return list;
	}

	public Collection getSurveysByCreationDate(String date) {
		Survey surv = new Survey();
		Calendar creationDate = Transformer.getCalendarFromString(date);
		surv.setCreationDate(creationDate);
		return surveyDAO.findByCreationDate(surv);
	}

	public Collection getSurveysByStatus(String statusCode) {
		Survey survey = new Survey();
		survey.setStatus(statusCode);
		return surveyDAO.findByStatus(survey);
	}

	public Collection getSurveysByFullDescription(String name, String date,
			String statusCode) {
		Survey survey = new Survey();
		Calendar creationDate = Transformer.getCalendarFromString(date);
		survey.setName(name);
		survey.setStatus(statusCode);
		survey.setCreationDate(creationDate);
		return surveyDAO.findByNameAndCreationDateAndStatus(survey);
	}

	public Collection getSurveysByNameAndStatus(String name, String status) {
		Survey survey = new Survey();
		survey.setName(name);
		survey.setStatus(status);
		return surveyDAO.findByNameAndStatus(survey);
	}

	public Collection getSurveysByNameAndCreationDate(String name,
			String creationDate) {
		Survey surv = new Survey();
		surv.setName(name);
		surv.setCreationDate(Transformer.getCalendarFromString(creationDate));
		return surveyDAO.findByNameAndCreationDate(surv);
	}

	public Collection getSurveysByCreationDateAndStatus(String date,
			String status) {
		Survey survey = new Survey();
		Calendar creationDate = Transformer.getCalendarFromString(date);
		survey.setCreationDate(creationDate);
		survey.setStatus(status);
		return surveyDAO.findByStatusAndCreationDate(survey);
	}

	public void setSurveyDAO(CustomSurveyDAO surveyDAO) {
		this.surveyDAO = surveyDAO;
	}

}
