package ar.com.survey.admin;

import java.util.ArrayList;
import java.util.Collection;

import ar.com.survey.model.Survey;
import ar.com.survey.model.SurveyDAO;

public class SearchComponent implements ISearchComponent {
	
	private SurveyDAO surveyDAO;

	public Collection getSurveysByName(String name) {
		Survey surv = new Survey();
		surv.setName(name);
		ArrayList<Survey> list = new ArrayList<Survey>();
		surv = surveyDAO.findBySurrogateKey(surv);
		if(surv != null)
			list.add(surv);
		return list;
	}

	public Collection getSurveysByCreationDate(String date) {
		return null;
	}

	public Collection getSurveysByStatus(String statusCode) {
		return null;
	}

	public Collection getSurveysByFullDescription(String name, String date,
			String statusCode) {
		return null;
	}

	public Collection getSurveysByNameAndStatus(String name, String status) {
		return null;
	}

	public Collection getSurveysByNameAndCreationDate(String name,
			String creationDate) {
		return null;
	}

	public Collection getSurveysByCreationDateAndStatus(String creationDate,
			String status) {
		return null;
	}

	public void setSurveyDAO(SurveyDAO sdao) {
		surveyDAO = sdao;
	}
	
	

}
