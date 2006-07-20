package ar.com.survey.web.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class SurveyForm extends BaseForm {
	
	private String name, startDate, endDate;
	private String sectionName, quotaScript, flowScript, questionType;
	private static final long serialVersionUID = 1L;

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
	public String getFlowScript() {
		return flowScript;
	}

	public void setFlowScript(String flowScript) {
		this.flowScript = flowScript;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuotaScript() {
		return quotaScript;
	}

	public void setQuotaScript(String quotaScript) {
		this.quotaScript = quotaScript;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
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
		
		if(getMethod()!=null && getMethod().equals("sections")){

			// Verify that all values have been posted
			
			if ( (getName() == null || getName().length() < 1)
					|| (getStartDate() == null || getStartDate().length()<1)
					|| (getEndDate() == null || getEndDate().length()<1) ){
					
				errors.add("missingValues", new ActionMessage(
						"ar.com.survey.error.survey.missingValues"));
			}
		}
		
		if(getMethod()!=null && getMethod().equals("newSection")){

			// Verify that all values have been posted
			
			if ( (getSectionName() == null || getSectionName().length() < 1)
					|| (getQuotaScript() == null || getQuotaScript().length()<1)
					|| (getFlowScript() == null || getFlowScript().length()<1) ){
					
				errors.add("missingValues", new ActionMessage(
						"ar.com.survey.error.section.missingValues"));
			}
		}
		
		return errors;
	}
	
	/**
	 * Sets default values for this bean's properties
	 *
	 */
	public void reset(){
	
		this.endDate = null;
		this.flowScript = null;
		this.name = null;
		this.questionType = null;
		this.quotaScript = null;
		this.sectionName = null;
		this.startDate = null;
		
	}
	
}
