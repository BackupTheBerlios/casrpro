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

import ar.com.survey.model.Person;
import ar.com.survey.registration.IRegistrationComponent;
import ar.com.survey.registration.PersonExistsException;
import ar.com.survey.registration.RegistrationExistsException;
import ar.com.survey.web.component.RegistrationWebComponent;
import ar.com.survey.web.struts.form.RegisterForm;

public class RegisterAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(RegisterAction.class);
	private IRegistrationComponent registerFacade;

	/**
	 * 
	 * This method persists a user to the database and sends a confirmation token
	 * by email
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		RegisterForm rfm = (RegisterForm) form;
		Person person = new RegistrationWebComponent()
				.getRegistrationFromForm(rfm);
		ActionForward forward = null;
		try {
			registerFacade.register(person);
			forward = mapping.findForward("success");
		} catch (RegistrationExistsException e) {
			forward = mapping.findForward("error");
			// TODO Sebastián aca habría que reenviarle el mail y como decías
			// resetearle la fecha
			// Por otro lado sabes como manejar el tema de las fechas, osea como
			// hacer a partir
			// de una fecha dada como saber si ya pasaron n días o no... te
			// pregunto porque no
			// conozco una forma de hacerlo sin hacer el calculo a mano...
		} catch (PersonExistsException e) {
			forward = mapping.findForward("exists");
		}
		return forward;
	}
	
	/**
	 * This method is used to finalize the registration process
	 * 
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	public ActionForward confirmReg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String token = request.getParameter("token");
		if(token!=null){
			logger.info("confirm: " + token);
			try {
				boolean isConfirmed = registerFacade.confirmRegistration(request.getParameter("token"));
				if(isConfirmed)
					return mapping.findForward("confirmSuccess");
				else
					return mapping.findForward("confirmError");
			}
			catch(PersonExistsException pe){
				return mapping.findForward("confirmExists");
			}
		}
		else {
			return mapping.findForward("confirmError");
		}
	}

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {

		logger.info("Show register form");
		return mapping.findForward("registerForm");
	}

	public void setRegisterFacade(IRegistrationComponent registerFacade) {
		this.registerFacade = registerFacade;
	}

}
