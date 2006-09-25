package ar.com.survey.web.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.survey.client.ClientWebComponent;
import ar.com.survey.client.IFlowManager;
import ar.com.survey.client.dto.FlowManageDTO;
import ar.com.survey.web.struts.form.FillForm;

public class FillAction extends DispatchAction {

	private IFlowManager flowManager;
	private static Logger logger = Logger.getLogger(FillAction.class);
	
	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {

		return null;
	}
	
	public ActionForward fill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ClientWebComponent cwp = new ClientWebComponent();
		if(cwp.isSurveyOpen(request.getParameter("sid"), request.getParameter("token"), request.getSession())){
			FillForm fform = (FillForm) form;
			FlowManageDTO flowDTO = flowManager.getNextStep(fform, request.getSession());
			request.setAttribute("flowDTO", flowDTO);
			return mapping.findForward("answers");
		}
		return mapping.findForward("surveyError");
	}
	
	public ActionForward fillNext(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		FillForm fform = (FillForm) form;
		fform.reset(mapping, request);
		
		// Get Next step to display
		
		FlowManageDTO flowDTO = flowManager.getNextStep(fform, request.getSession());
		ClientWebComponent cwp = new ClientWebComponent();
		cwp.getNextSurveySection(request.getSession(), flowDTO.getSection());
		request.setAttribute("flowDTO", flowDTO);
		return mapping.findForward("answers");
	}

	public void setFlowManager(IFlowManager flowManager) {
		this.flowManager = flowManager;
	}
	
	
	
}
