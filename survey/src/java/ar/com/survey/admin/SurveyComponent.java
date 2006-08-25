package ar.com.survey.admin;

import ar.com.survey.model.Survey;
import ar.com.survey.model.SurveyDAO;

public class SurveyComponent implements ISurveyComponent {
	
	private SurveyDAO sDAO;
	
	public SurveyComponent() {
		super();
		sDAO = new SurveyDAO();
	}
	
	public void createSurvey(Survey s) {
		sDAO.createNew(s);
	}
	
	public void updateSurvey(Survey s) {
		sDAO.createOrUpdate(s);
	}
	
	public void deleteSurvey(Survey s) {
		Survey surv = sDAO.findBySurrogateKey(s);
		if(surv!=null)
			sDAO.delete(s);
	}

}
