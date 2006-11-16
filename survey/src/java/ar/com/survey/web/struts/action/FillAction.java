package ar.com.survey.web.struts.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.survey.ISurveyComponent;
import ar.com.survey.client.ClientSessionManager;
import ar.com.survey.client.ClientWebComponent;
import ar.com.survey.client.IFlowManager;
import ar.com.survey.client.dto.FlowManageDTO;
import ar.com.survey.model.Section;
import ar.com.survey.web.struts.form.FillForm;

public class FillAction extends DispatchAction {

	private IFlowManager flowManager;

	private ISurveyComponent surveyComponent;

	public ActionForward fill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		// if (session.getAttribute("CurrentClientSurvey") != null)
		// return mapping.findForward("concurrentSurvey");

		ClientWebComponent cwp = new ClientWebComponent();
		ClientSessionManager csm = new ClientSessionManager();
		ActionForward mapp = cwp.getSurveyPage(request.getParameter("sid"),
				request.getParameter("token"), csm, mapping);
		ArrayList answers = new ArrayList();
		if (mapp.equals(mapping.findForward("answers"))) {
			FillForm fform = (FillForm) form;
			FlowManageDTO flowDTO = flowManager.getNextStep(fform, csm);
			request.setAttribute("flowDTO", flowDTO);
			csm.removeAttribute("answers");
			csm.setAttribute("answers", answers);
		}
		session.setAttribute("csm", csm);
		request.setAttribute("CurrentClientSection", csm.getAttribute("CurrentClientSection"));
		return mapp;
	}

	public ActionForward fillNext(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		FillForm fform = (FillForm) form;
		HttpSession session = request.getSession();

		if (session.getAttribute("csm") == null)
			return mapping.findForward("invalidSession");

		ClientWebComponent cwp = new ClientWebComponent();
		ClientSessionManager csm = (ClientSessionManager) session
				.getAttribute("csm");

		// First verify that this section has not been voted before
		// this could happen if the user press refresh or if the flow script is
		// wrong

		ArrayList sectionsFilled = csm.containsAttribute("sectionsFilled") ? (ArrayList) csm
				.getAttribute("sectionsFilled")
				: new ArrayList();

		Section section = (Section) csm.getAttribute("CurrentClientSection");

		if (!sectionsFilled.contains(section.getName())) {
			ArrayList answers = (ArrayList) csm.getAttribute("answers");
			cwp.addAnswersToSession(answers, section, fform);
			csm.setAttribute("sectionsFilled", sectionsFilled);
		}

		// Get Next step to display

		FlowManageDTO flowDTO = flowManager.getNextStep(fform, csm);

		cwp.getNextSurveySection(csm, flowDTO.getSection());
		request.setAttribute("flowDTO", flowDTO);

		// add results to session
		fform.reset(mapping, request);

		session.setAttribute("csm", csm);
		request.setAttribute("CurrentClientSection", csm.getAttribute("CurrentClientSection"));

		return mapping.findForward("answers");
	}

	public ActionForward finish(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ActionForward forward = fillNext(mapping, form, request, response);

		ClientWebComponent cwp = new ClientWebComponent();
		HttpSession session = request.getSession();
		ClientSessionManager csm = (ClientSessionManager) session
				.getAttribute("csm");
		if (forward.getName().equals("answers")){
			forward = mapping.findForward("finish");
			request.setAttribute("CurrentClientSection", csm.getAttribute("CurrentClientSection"));
		}
		cwp.persistAnswers(csm);
		session.setAttribute("csm", csm);
		return forward;
	}

	public ActionForward clientError(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		return mapping.findForward("clientGeneralError");

	}
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {

		return null;
	}

	public void setFlowManager(IFlowManager flowManager) {
		this.flowManager = flowManager;
	}

	public void setSurveyComponent(ISurveyComponent surveyComponent) {
		this.surveyComponent = surveyComponent;
	}

}
