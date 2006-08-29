package ar.com.survey.web.component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import ar.com.survey.admin.ISurveyComponent;
import ar.com.survey.admin.SurveyComponent;
import ar.com.survey.model.Question;
import ar.com.survey.model.Quota;
import ar.com.survey.model.Section;
import ar.com.survey.model.Survey;
import ar.com.survey.model.enums.SurveyState;
import ar.com.survey.questions.EmptyQuestion;
import ar.com.survey.questions.list.CheckBoxListQuestion;
import ar.com.survey.questions.list.NumberListQuestion;
import ar.com.survey.questions.list.StringListQuestion;
import ar.com.survey.questions.matrix.RadioMatrixQuestion;
import ar.com.survey.questions.single.StringQuestion;
import ar.com.survey.questions.single.TextAreaQuestion;
import ar.com.survey.util.Transformer;
import ar.com.survey.web.struts.form.SurveyForm;

public class SurveyWebComponent {
	
	private ISurveyComponent surveyComponent;

	public SurveyWebComponent(){
		surveyComponent = new SurveyComponent();
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
		survey.setCreationDate(Calendar.getInstance());
		survey.setStatus(SurveyState.OPEN.getCode());
		session.setAttribute("currentSurvey", survey);
		session.setAttribute("surveyOp", "new");
	}
	
	/**
	 * 
	 * This method removes a persisted survey object 
	 * 
	 * @param form used to remove the persisted survey
	 */
	public void removePersistedSurvey(SurveyForm form){
		Survey survey = new Survey();
		survey.setName(form.getName()); 
		surveyComponent.deleteSurvey(survey);
	}
	
	/**
	 * 
	 * This method gets a persisted survey object 
	 * 
	 * @param form used to retrieve the persisted survey
	 */
	public Survey getPersistedSurvey(SurveyForm form){
		return surveyComponent.getSurvey(form.getName());
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
	public void updatePersistedSurvey(HttpServletRequest request, SurveyForm sform){
		Survey survey;
		HttpSession session = request.getSession();
		if(sform.getSectionName()!=null && !sform.getSectionName().equals(""))
			survey = addSectionToSurvey(request, sform);
		else {
			survey = (Survey) session.getAttribute("currentSurvey");
			survey.setName(sform.getName());
			// TODO: Claudio agregar fechas transformadas a Calendar
		}
		surveyComponent.updateSurvey(survey);
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
	
	public void persistSessionSurvey(HttpServletRequest request, SurveyForm sform){
		HttpSession session = request.getSession();
		Survey survey = addSectionToSurvey(request, sform);
		String surveyOp = (String) session.getAttribute("surveyOp");
		if(surveyOp.equals("new"))
			surveyComponent.createSurvey(survey);
		else
			surveyComponent.updateSurvey(survey);
		session.removeAttribute("currentSection");
		session.removeAttribute("currentSurvey");
	}
	
	public void updateSectionInSurvey(HttpServletRequest request, SurveyForm sform){
		HttpSession session = request.getSession();
		Survey survey = (Survey) session.getAttribute("currentSurvey");
		Section section = (Section) session.getAttribute("currentSection");
		section.setFlowMgmtScript(sform.getFlowScript());
		section.setName(sform.getSectionName());
		section.setQuotaMgmtScript(sform.getQuotaScript());
		section.setSurvey(survey);
		int row = sform.getRow();
		List<Section> sections = survey.getSections();
		sections.set(row, section);
		survey.setSections(sections);
		session.setAttribute("currentSection", section);
		session.setAttribute("currentSurvey", survey);
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
	
	public void removeSectionInSessionSurvey(HttpServletRequest request){
		HttpSession session = request.getSession();
		Survey survey = (Survey) session.getAttribute("currentSurvey");
		int row = Integer.parseInt(request.getParameter("row")) - 1;
		Iterator iter = survey.getSections().iterator();
		int index = 0;
		Section section = null;
		while(iter.hasNext()){
			section = (Section) iter.next();
			if(index==row)
				break;
			else
				index++;
		}
		survey.getSections().remove(section);
		session.setAttribute("currentSurvey", survey);
	}
	
	public void editSectionInSurvey(HttpServletRequest request, SurveyForm form){
		HttpSession session = request.getSession();
		Survey survey = (Survey) session.getAttribute("currentSurvey");
		int row = form.getRow() - 1;
		Section section = survey.getSections().get(row);
		session.setAttribute("currentSection", section);
		request.setAttribute("row", row);
	}
	
	/* Question management methods */
	
	public void addOpenQuestionToSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("quesTxt");
		String answerType = request.getParameter("txtType");
		
		// now parse different params depending on the type
		Question question = null;
		
		if(answerType.equals("textArea"))
			question = new TextAreaQuestion();
		else
			question = new StringQuestion();
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.add(question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
	}
	
	public void updateOpenQuestionInSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("quesTxt");
		String answerType = request.getParameter("txtType");
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		
		// now parse different params depending on the type
		Question question = null;
		
		if(answerType.equals("textArea"))
			question = new TextAreaQuestion();
		else
			question = new StringQuestion();
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.set(rowId, question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
	}
	
	public void addEmptyQuestionToSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("quesTxt");
		
		// now parse different params depending on the type
		Question question = new EmptyQuestion();
				
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.add(question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
	}
	
	public void updateEmptyQuestionInSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("quesTxt");
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		
		// now parse different params depending on the type
		Question question = new EmptyQuestion();
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.set(rowId, question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
	}
	
	public void addNumericQuestionToSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		String validationType = request.getParameter("validationType");
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		String total = request.getParameter("total");
		
		String questionText = request.getParameter("questionTxt");
		
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		
		// now parse different params depending on the type
		NumberListQuestion question = new NumberListQuestion(answers.size());
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setItems(answers);
		
		if(validationType.equals("individual")){
			question.setMin(Integer.parseInt(min));
			question.setMax(Integer.parseInt(max));
		} else if (validationType.equals("total")){
			question.setTotal(Integer.parseInt(total));
		}
		question.setImage(image);


		List<Question> quests = section.getQuestions();
		quests.add(question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
		session.removeAttribute("answers");
	}
	
	public void updateNumericQuestionInSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		String validationType = request.getParameter("validationType");
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		String total = request.getParameter("total");
		
		String questionText = request.getParameter("questionTxt");
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		
		// now parse different params depending on the type
		NumberListQuestion question = new NumberListQuestion(answers.size());
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setItems(answers);
		if(validationType.equals("individual")){
			question.setMin(Integer.parseInt(min));
			question.setMax(Integer.parseInt(max));
		} else if (validationType.equals("total")){
			question.setTotal(Integer.parseInt(total));
		}
		question.setImage(image);


		List<Question> quests = section.getQuestions();
		quests.set(rowId, question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
		session.removeAttribute("answers");
	}
	
	public void addStringListQuestionToSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("questionTxt");
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		
		// now parse different params depending on the type
		StringListQuestion question = new StringListQuestion();
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setItems(answers);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.add(question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
		session.removeAttribute("answers");
	}
	
	public void updateStringListQuestionInSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("questionTxt");
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		
		// now parse different params depending on the type
		StringListQuestion question = new StringListQuestion();
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setItems(answers);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.set(rowId, question);
		
		section.setQuestions(quests);
		session.setAttribute("currentSection", section);
		session.removeAttribute("answers");
	}
	
	public void addCheckBoxQuestionToSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("questionTxt");
		
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		
		// now parse different params depending on the type
		CheckBoxListQuestion question = new CheckBoxListQuestion();
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setItems(answers);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.add(question);
		
		section.setQuestions(quests);
		session.setAttribute("currentSection", section);
		session.removeAttribute("answers");
	}
	
	public void updateCheckBoxQuestionInSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("questionTxt");
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		
		// now parse different params depending on the type
		CheckBoxListQuestion question = new CheckBoxListQuestion();
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setItems(answers);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.set(rowId, question);
		
		section.setQuestions(quests);
		session.setAttribute("currentSection", section);
		session.removeAttribute("answers");
	}
	
	
	public void addMatrixQuestionToSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("questionTxt");
		
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		ArrayList<String> columns = (ArrayList<String>) session.getAttribute("columns");
		
		// now parse different params depending on the type
		RadioMatrixQuestion question = new RadioMatrixQuestion(columns.size(), answers.size());
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setItems(answers);
		question.setColumnsTitles(columns);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.add(question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
		session.removeAttribute("answers");
		session.removeAttribute("columns");
	}
	
	public void updateMatrixQuestionInSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		String name = request.getParameter("name");
		String image = request.getParameter("image");
		
		String questionText = request.getParameter("questionTxt");
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		ArrayList<String> columns = (ArrayList<String>) session.getAttribute("columns");
		
		// now parse different params depending on the type
		RadioMatrixQuestion question = new RadioMatrixQuestion(columns.size(), answers.size());
		
		question.setDescription(questionText);
		question.setSection(section);
		question.setTitle(name);
		question.setItems(answers);
		question.setColumnsTitles(columns);
		question.setImage(image);

		List<Question> quests = section.getQuestions();
		quests.set(rowId, question);
		
		section.setQuestions(quests);
		
		session.setAttribute("currentSection", section);
		session.removeAttribute("answers");
		session.removeAttribute("columns");
	}
	
	
	
	public void removeQuestionInSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		
		List<Question> quests = section.getQuestions();
		quests.remove(rowId);
		
		section.setQuestions(quests);
		session.setAttribute("currentSection", section);
	}
	
	public Question getQuestionFromSection(HttpServletRequest request){
		HttpSession session = request.getSession();
		Section section = (Section) session.getAttribute("currentSection");
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		
		Question question = null;
		List<Question> quests = section.getQuestions();
		question = quests.get(rowId);
		
		return question;
	}
	
	/* answer and columns management methods */
	
	public void addAnswerToSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		String answer = request.getParameter("answer");
		ArrayList<String> answers;
		if(session.getAttribute("answers")==null)
			 answers = new ArrayList<String>();
		else
			 answers = (ArrayList<String>) session.getAttribute("answers");
		answers.add(answer);
		session.setAttribute("answers", answers);
	}
	
	public void removeAnswerFromSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		answers.remove(rowId);
		session.setAttribute("answers", answers);
	}
	
	public void updateAnswerInSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		int rowId = Integer.parseInt(request.getParameter("row"));
		String answer = request.getParameter("answer");
		rowId--;
		ArrayList<String> answers = (ArrayList<String>) session.getAttribute("answers");
		answers.set(rowId, answer);
		session.setAttribute("answers", answers);
	}
	
	public void addColumnToSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		String column = request.getParameter("column");
		ArrayList<String> columns;
		if(session.getAttribute("columns")==null)
			columns = new ArrayList<String>();
		else
			columns = (ArrayList<String>) session.getAttribute("columns");
		columns.add(column);
		session.setAttribute("columns", columns);
	}
	
	public void removeColumnFromSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		int rowId = Integer.parseInt(request.getParameter("row"));
		rowId--;
		ArrayList<String> columns = (ArrayList<String>) session.getAttribute("columns");
		columns.remove(rowId);
		session.setAttribute("columns", columns);
	}
	
	public void updateColumnInSession(HttpServletRequest request){
		HttpSession session = request.getSession();
		int rowId = Integer.parseInt(request.getParameter("row"));
		String column = request.getParameter("column");
		rowId--;
		ArrayList<String> columns = (ArrayList<String>) session.getAttribute("columns");
		columns.set(rowId, column);
		session.setAttribute("columns", columns);
	}
}
