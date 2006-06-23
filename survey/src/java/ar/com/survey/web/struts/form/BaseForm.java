package ar.com.survey.web.struts.form;

import org.apache.struts.action.ActionForm;

public abstract class BaseForm extends ActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Dispatch Action method property
	private String method;

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}
	
	
	
}
