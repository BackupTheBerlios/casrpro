package ar.com.survey.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class SearchForm extends BaseForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name, creationDate, status;

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Validate the properties that have been sent from the HTTP request, and
	 * return an ActionErrors object that encapsulates any validation errors
	 * that have been found. If no errors are found, return an empty
	 * ActionErrors object.
	 */
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		
		ActionErrors errors = new ActionErrors();
		
		if(getMethod()!=null && getMethod().equals("search")){

			// Verify that at least one filter value has been posted
			
			if ( getStatus() == null || getStatus().length()<1 
					&& (getName() == null || getName().length()<1)
					&& (getCreationDate() == null || getCreationDate().length()<1) ){
				errors.add("searchFilters", new ActionMessage(
						"ar.com.survey.error.search.noFilterSelected"));
			}
		}
		return errors;
	}

}
