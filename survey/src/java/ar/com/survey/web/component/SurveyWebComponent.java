package ar.com.survey.web.component;

import java.util.Calendar;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.com.survey.DummySurveyComponent;
import ar.com.survey.ISurveyComponent;
import ar.com.survey.model.Question;
import ar.com.survey.model.Quota;
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
	
	/* Quota management methods */
	
	public void addQuotaToSessionSurvey(HttpServletRequest request){
		HttpSession session = request.getSession();
		Survey survey = (Survey) session.getAttribute("currentSurvey");
		String name = request.getParameter("name");
		int limit = Integer.parseInt(request.getParameter("value"));
		Quota quota = new Quota(name, survey, limit, 0);
		Set<Quota> quotas = survey.getQuotas();
		quotas.add(quota);
		survey.setQuotas(quotas);
		session.setAttribute("currentSurvey", survey);
	}
	
	public void updateQuotaInSessionSurvey(HttpServletRequest request){
		HttpSession session = request.getSession();
		Survey survey = (Survey) session.getAttribute("currentSurvey");
		String name = request.getParameter("name");
		int limit = Integer.parseInt(request.getParameter("value"));
		int row = Integer.parseInt(request.getParameter("row")) - 1;
		Iterator iter = survey.getQuotas().iterator();
		int index = 0;
		while(iter.hasNext()){
			Quota quota = (Quota) iter.next();
			if(index==row){
				quota.setName(name);
				quota.setLimit(limit);
				break;
			}
			else
				index++;
		}
		session.setAttribute("currentSurvey", survey);
	}
	
	public void removeQuotaInSessionSurvey(HttpServletRequest request){
		HttpSession session = request.getSession();
		Survey survey = (Survey) session.getAttribute("currentSurvey");
		int row = Integer.parseInt(request.getParameter("row")) - 1;
		Iterator iter = survey.getQuotas().iterator();
		int index = 0;
		Quota quota = null;
		while(iter.hasNext()){
			quota = (Quota) iter.next();
			if(index==row)
				break;
			else
				index++;
		}
		survey.getQuotas().remove(quota);
		session.setAttribute("currentSurvey", survey);
	}
	
	public void addQuestionToSessionSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		String questionText = request.getParameter("question");
		String questionType = request.getParameter("type");
		
		// now parse different params depending on the type
		
		// Question question = new Question(section);
		
		session.setAttribute("currentSection", section);
	}
	
}
