package ar.com.survey.client;

import javax.servlet.http.HttpSession;

import ar.com.survey.admin.db.CustomSurveyDAO;
import ar.com.survey.model.Survey;
import ar.com.survey.model.enums.SurveyState;

public class ClientWebComponent {

	private CustomSurveyDAO sDAO;
	
	public ClientWebComponent(){
		sDAO = new CustomSurveyDAO();
	}
	
	/**
	 * This method checks if survey is open and if so registers the survey in session
	 * and returns true, else returns false
	 * 
	 * @return boolean true or false
	 * @param sname String indicating survey name
	 * @param token client id
	 * @param session
	 * 
	 */
	public boolean isSurveyOpen(String sname, String token, HttpSession session){
		Survey survey = new Survey();
		survey.setName(sname);
		survey = sDAO.findBySurrogateKey(survey);
		if(survey==null)
			return false;
		else if(!survey.getStatus().equals(SurveyState.OPEN.getCode()))
			return false;
		else {
			session.setAttribute("CurrentSurvey", survey);
			session.setAttribute("CurrentSection", survey.getSection(0));
		}
		return true;
	}
	
	public boolean getNextSurveySection(HttpSession session, int sectionId){
		boolean retVal = true;
		Survey surv = (Survey) session.getAttribute("CurrentSurvey");
		if(surv.getSections().size()>=sectionId)
			session.setAttribute("CurrentSection", surv.getSection(sectionId-1));
		else retVal = false;
		return retVal;
	}
	
}
