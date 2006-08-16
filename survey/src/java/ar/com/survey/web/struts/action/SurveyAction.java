package ar.com.survey.web.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.survey.model.Question;
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
		new SurveyWebComponent().persistSessionSurvey(request, sform);
		return mapping.findForward("persistOk");
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
	
	public ActionForward getQuestionFromSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
			ActionForward forward = null;
			Question question = new SurveyWebComponent().getQuestionFromSection(request);
			if(question instanceof StringQuestion){
				StringQuestion quest = (StringQuestion) question;
				request.setAttribute("Question", quest);
				forward = mapping.findForward("editOpenQuestion");
			} else if (question instanceof TextAreaQuestion){
				TextAreaQuestion quest = (TextAreaQuestion) question;
				request.setAttribute("Question", quest);
				forward = mapping.findForward("editOpenQuestion");
			}
			return forward;
	}
	
	public ActionForward updateOpenQuestionInSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().updateOpenQuestionInSection(request);
		return null;
	}
	
	/*
	public ActionForward addQuestionToSessionSection(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			new SurveyWebComponent().addQuestionToSessionSection(request);
		return null;
	}
	*/
	
	
}
