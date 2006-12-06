package ar.com.survey.web.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.survey.admin.ReportComponent;
import ar.com.survey.web.struts.form.ReportForm;

public class ReportAction extends DispatchAction {
	

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {

		return mapping.findForward("reportsList");
	}
	
	public ActionForward countFS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		return mapping.findForward("countFS");

	}

	public ActionForward countFSReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		ReportComponent rc = new ReportComponent();
		ReportForm rf = (ReportForm) form;
		
		return (rc.printCountFS(rf, response)) ? null : mapping.findForward("reportError");

	}
	
	public ActionForward listCFS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		return mapping.findForward("listCFS");

	}
	
	public ActionForward listUFS(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		return mapping.findForward("listUFS");

	}
	public ActionForward listPersons(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		return mapping.findForward("listPersons");

	}


}
