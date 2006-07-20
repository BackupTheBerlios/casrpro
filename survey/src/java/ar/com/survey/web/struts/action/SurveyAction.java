package ar.com.survey.web.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

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
	
}