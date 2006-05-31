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

import ar.com.survey.model.Registration;
import ar.com.survey.registration.IRegistrationComponent;
import ar.com.survey.registration.PersonExistsException;
import ar.com.survey.registration.RegistrationExistsException;

public class RegisterAction extends DispatchAction {

	private static Logger logger = Logger.getLogger("RegisterAction.class");

	// TODO Clauio: creo que voy entendiendo cómo Spring la inyetca los services
	// a cada action
	// pero... esta variable es de clase y struts reutiliza los actions, los
	// cuales deben ser thread-safe ...
	// entonces, este service también deberia ser thread safe????
	// si pasa eso con todos, puede llegar a ser una cagad... se puede hacer distinto?

	private IRegistrationComponent registerFacade;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("registerForm");
	}

	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		Registration registration = new Registration();
		// TODO Claudio: acá faltaría ahora entonces el mapping entre el form
		// (view layer) y el bean (bussines layer)

		try {
			this.registerFacade.register(registration);
		} catch (RegistrationExistsException e) {
			// TODO Claudio forward al error con el msg correspondiente, le
			// damos la opcion de hacer un resend de la notificación?..
			// si si, entonces:
			// implementare un metodo mas en este componente
			// (resendNotificacion(email)
			// y reseteamos la fecha de regsitracion para que dure N dias más
		} catch (PersonExistsException e) {
			// TODO Claudio forward al error con el msg correspondiente
		}

		return mapping.findForward("success");
	}

	public void setRegisterFacade(IRegistrationComponent registerFacade) {
		this.registerFacade = registerFacade;
	}

}
