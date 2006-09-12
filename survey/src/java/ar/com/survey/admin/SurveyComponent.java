package ar.com.survey.admin;

import ar.com.survey.admin.db.CustomSurveyDAO;
import ar.com.survey.model.Survey;

public class SurveyComponent implements ISurveyComponent {
	
	private CustomSurveyDAO sDAO;
	
	public SurveyComponent() {
		super();
		sDAO = new CustomSurveyDAO();
	}
	
	public void createSurvey(Survey s) {
		sDAO.createNew(s);
	}
	
	public void updateSurvey(Survey s) {
		sDAO.createOrUpdate(s);
	}
	
	public void deleteSurvey(Survey s) {
			sDAO.deleteBySurrogate(s);
	}

	public Survey getSurvey(String name) {
		Survey surv = new Survey();
		surv.setName(name);
		surv = sDAO.findBySurrogateKey(surv);
		return surv;
	}

}
