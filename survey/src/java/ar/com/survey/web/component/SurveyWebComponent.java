package ar.com.survey.web.component;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.com.survey.DummySurveyComponent;
import ar.com.survey.ISurveyComponent;
import ar.com.survey.model.Section;
import ar.com.survey.model.Survey;
import ar.com.survey.util.Transformer;
import ar.com.survey.web.struts.form.SurveyForm;

public class SurveyWebComponent {
	
	private ISurveyComponent surveyComponent;

	public SurveyWebComponent(){
		// TODO Sebastián: Replace the dummy for the real object value
		surveyComponent = new DummySurveyComponent();
	}
	
	/**
	 * 
	 * This method creates an empty session survey object por later completion in the wizard
	 * 
	 * @param request used to retrieve the session and store the survey in it
	 */
	public void createSessionSurvey(HttpServletRequest request){
		HttpSession session = request.getSession(true);
		Survey survey = new Survey();
		session.setAttribute("currentSurvey", survey);
	}
	
	/**
	 * 
	 * Updates the survey object in session, adding sections and dates
	 * 
	 * @param sform
	 */
	public void updateSessionSurvey(HttpServletRequest request, SurveyForm sform){
		
		Survey survey = null;
		HttpSession session = request.getSession();
		if(sform.getFlowScript()!=null && sform.getFlowScript().length()>0){
			// add a new section
			survey = addSectionToSurvey(request, sform);
		}
		else {
			// set survey's values
			survey = updateSessionSurveyValues(request, sform);
		}
		session.setAttribute("currentSurvey", survey);
		Section section = new Section();
		session.setAttribute("currentSection", section);
	}
	
	/**
	 * 
	 * Persists the new survey object to the database
	 * 
	 * @param request
	 * @param sform
	 */
	public void persistSessionSurvey(HttpServletRequest request, SurveyForm sform){
		HttpSession session = request.getSession();
		Survey survey = addSectionToSurvey(request, sform);
		surveyComponent.createSurvey(survey);
		session.removeAttribute("currentSection");
		session.removeAttribute("currentSurvey");
	}
	
	private Survey addSectionToSurvey(HttpServletRequest request, SurveyForm sform){
		HttpSession session = request.getSession();
		Survey survey = (Survey) session.getAttribute("currentSurvey");
		Section section = (Section) session.getAttribute("currentSection");
		section.setFlowMgmtScript(sform.getFlowScript());
		section.setName(sform.getSectionName());
		section.setQuotaMgmtScript(sform.getQuotaScript());
		section.setSurvey(survey);
		survey.addSection(survey.getSections().size(), section);
		return survey;
	}
	
	private Survey updateSessionSurveyValues(HttpServletRequest request, SurveyForm sform){
		HttpSession session = request.getSession();
		Survey survey = (Survey) session.getAttribute("currentSurvey");
		survey.setName(sform.getName());
		survey.setCreationDate(Calendar.getInstance());
		survey.setFinishDate(Transformer.getCalendarFromString(sform.getEndDate()));
		survey.setStartDate(Transformer.getCalendarFromString(sform.getStartDate()));
		return survey;
	}
}
