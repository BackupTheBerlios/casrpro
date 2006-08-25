package ar.com.survey.web.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.survey.exceptions.DuplicateEntityException;
import ar.com.survey.model.Question;
import ar.com.survey.questions.EmptyQuestion;
import ar.com.survey.questions.list.CheckBoxListQuestion;
import ar.com.survey.questions.list.NumberListQuestion;
import ar.com.survey.questions.list.StringListQuestion;
import ar.com.survey.questions.matrix.RadioMatrixQuestion;
import ar.com.survey.questions.single.StringQuestion;
import ar.com.survey.questions.single.TextAreaQuestion;
import ar.com.survey.web.component.SurveyWebComponent;
import ar.com.survey.web.struts.form.SurveyForm;

public class SurveyAction extends DispatchAction {

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {

		// create a default session survey
		new SurveyWebComponent().createSessionSurvey(request);
		return mapping.findForward("newSurvey");
	}
	
	public ActionForward sections(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		SurveyForm sform = (SurveyForm) form;
		new SurveyWebComponent().updateSessionSurvey(request, sform);
		return mapping.findForward("newSection");
	}
	
	public ActionForward addSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		SurveyForm sform = (SurveyForm) form;
		new SurveyWebComponent().updateSessionSurvey(request, sform);
		sform.reset();
		return mapping.findForward("newSection");
	}
	
	public ActionForward persistSurvey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		SurveyForm sform = (SurveyForm) form;
		ActionForward forward = mapping.findForward("persistOk"); 
		try{
			new SurveyWebComponent().persistSessionSurvey(request, sform);
		} catch(DuplicateEntityException dee){
			forward = mapping.findForward("persistDuplicated");
		}
		return forward;
	}
	
	public ActionForward removePersistedSurvey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionForward forward = mapping.findForward("removeOk");
		SurveyForm sform = (SurveyForm) form;
		new SurveyWebComponent().removePersistedSurvey(sform);
		return forward;
	}
	
	
	public ActionForward popup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		return mapping.findForward("popOpen");
	}
	
	/* Quota management methods */
	
	public ActionForward addQuotaToSessionSurvey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().addQuotaToSessionSurvey(request);
		return null;
	}
	
	public ActionForward updateQuotaInSessionSurvey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().updateQuotaInSessionSurvey(request);
		return null;
	}
	
	public ActionForward removeQuotaInSessionSurvey(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().removeQuotaInSessionSurvey(request);
		return null;
	}
	
	/* Question management methods */
	
	public ActionForward addOpenQuestionToSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().addOpenQuestionToSection(request);
		return null;
	}
	
	public ActionForward updateOpenQuestionInSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().updateOpenQuestionInSection(request);
		return null;
	}
	
	public ActionForward addEmptyQuestionToSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().addEmptyQuestionToSection(request);
		return null;
	}
	
	public ActionForward updateEmptyQuestionInSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().updateEmptyQuestionInSection(request);
		return null;
	}
	
	public ActionForward addStringListQuestionToSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().addStringListQuestionToSection(request);
		return null;
	}
	
	public ActionForward updateStringListQuestionInSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().updateStringListQuestionInSection(request);
		return null;
	}
	
	public ActionForward addCheckBoxListQuestionToSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().addCheckBoxQuestionToSection(request);
		return null;
	}
	
	public ActionForward updateCheckBoxListQuestionInSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().updateCheckBoxQuestionInSection(request);
		return null;
	}
	
	public ActionForward addNumericQuestionToSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().addNumericQuestionToSection(request);
		return null;
	}
	
	public ActionForward updateNumericQuestionInSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().updateNumericQuestionInSection(request);
		return null;
	}
	
	public ActionForward addMatrixQuestionToSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().addMatrixQuestionToSection(request);
		return null;
	}
	
	public ActionForward updateMatrixQuestionInSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().updateMatrixQuestionInSection(request);
		return null;
	}
	
	
	public ActionForward getQuestionFromSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
				
			ActionForward forward = null;
			HttpSession session = request.getSession();
			Question question = new SurveyWebComponent().getQuestionFromSection(request);
			if(question instanceof StringQuestion){
				StringQuestion quest = (StringQuestion) question;
				request.setAttribute("Question", quest);
				forward = mapping.findForward("editOpenQuestion");
			} else if (question instanceof TextAreaQuestion){
				TextAreaQuestion quest = (TextAreaQuestion) question;
				request.setAttribute("Question", quest);
				forward = mapping.findForward("editOpenQuestion");
			} else if (question instanceof EmptyQuestion){
				EmptyQuestion quest = (EmptyQuestion) question;
				request.setAttribute("Question", quest);
				forward = mapping.findForward("editEmptyQuestion");
			} else if (question instanceof NumberListQuestion){
				NumberListQuestion quest = (NumberListQuestion) question;
				request.setAttribute("Question", quest);
				session.setAttribute("answers", quest.getItems());
				forward = mapping.findForward("editNumberListQuestion");
			} else if (question instanceof StringListQuestion){
				StringListQuestion quest = (StringListQuestion) question;
				request.setAttribute("Question", quest);
				session.setAttribute("answers", quest.getItems());
				forward = mapping.findForward("editStringListQuestion");
			} else if (question instanceof CheckBoxListQuestion){
				CheckBoxListQuestion quest = (CheckBoxListQuestion) question;
				request.setAttribute("Question", quest);
				session.setAttribute("answers", quest.getItems());
				forward = mapping.findForward("editCheckBoxListQuestion");
			} else {
				// defaults to matrix
				RadioMatrixQuestion quest = (RadioMatrixQuestion) question;
				request.setAttribute("Question", quest);
				session.setAttribute("answers", quest.getItems());
				session.setAttribute("columns", quest.getColumnsTitles());
				forward = mapping.findForward("editRadioMatrixQuestion");
			}
			
			return forward;
	}
	
	public ActionForward removeQuestionInSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().removeQuestionInSection(request);
		return null;
	}
	
	
	/* answer and columns management methods */
	
	public ActionForward addAnswerToSession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		new SurveyWebComponent().addAnswerToSession(request);
		return null;
	}
	
	public ActionForward removeAnswerFromSession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		new SurveyWebComponent().removeAnswerFromSession(request);
		return null;
	}
	
	public ActionForward updateAnswerInSession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		new SurveyWebComponent().updateAnswerInSession(request);
		return null;
	}
	
	public ActionForward addColumnToSession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		new SurveyWebComponent().addColumnToSession(request);
		return null;
	}
	
	public ActionForward updateColumnInSession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		new SurveyWebComponent().updateColumnInSession(request);
		return null;
	}
	
	public ActionForward removeColumnFromSession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response) 
			throws IOException, ServletException {
		new SurveyWebComponent().removeColumnFromSession(request);
		return null;
	}
}
