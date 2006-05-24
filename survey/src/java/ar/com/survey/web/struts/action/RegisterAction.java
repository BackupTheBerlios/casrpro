package ar.com.survey.web.struts.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.survey.web.facade.IRegisterFacade;
import ar.com.survey.web.struts.form.RegisterForm;

public class RegisterAction extends DispatchAction {

	private IRegisterFacade registerFacade;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("registerForm");
	}

	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
			RegisterForm rf = (RegisterForm) form;
			registerFacade.registerForm(rf);
		return mapping.findForward("success");
	}

	public void setRegisterFacade(IRegisterFacade registerFacade) {
		this.registerFacade = registerFacade;
	}

}
