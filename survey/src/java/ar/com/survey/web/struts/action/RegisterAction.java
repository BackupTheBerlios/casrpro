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

	// TODO Clauio: creo que voy entendiendo cómo Spring la inyetca los services
	// a cada action
	// pero... esta variable es de clase y struts reutiliza los actions, los
	// cuales deben ser thread-safe ...
	// entonces, este service también deberia ser thread safe????
	// si pasa eso con todos, puede llegar a ser una cagad... se puede hacer
	// distinto?
	
	// TODO Sebastián: che si es verdad que no es thread safe, pero si los metodos
	// del componente los hacemos lo mas stateless posible no deberiamos tener
	// drama o no?? vos que pensas?

	private IRegistrationComponent registerFacade;

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
	
	public ActionForward confirmReg(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.info("registered!! " + request.getParameter("token"));
		return null;
		
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
