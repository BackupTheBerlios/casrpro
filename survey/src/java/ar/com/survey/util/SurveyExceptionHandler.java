package ar.com.survey.util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ExceptionHandler;
import org.apache.struts.config.ExceptionConfig;

public class SurveyExceptionHandler extends ExceptionHandler {

	private static final Logger log = Logger.getLogger(ExceptionHandler.class);

	@Override
	public ActionForward execute(Exception exception,
			ExceptionConfig exceptionConfig, ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws ServletException {

		exception.printStackTrace();
		log.error(exception);
		return super.execute(exception, exceptionConfig, mapping, form, request, response);
	}

}
