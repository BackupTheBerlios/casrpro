package ar.com.survey.client;

import ar.com.survey.client.dto.FlowManageDTO;
import ar.com.survey.web.struts.form.FillForm;

public interface IFlowManager {
	
	/**
	 * 
	 * This methods calculates quota and flow scripts to the specified form values
	 * and returns a dto which contains the next section id to display, the text
	 * value of the button to display and the javascript text action to execute
	 * when the button is pressed.
	 * 
	 * @param fillForm
	 * @param session
	 * @return
	 */
	public FlowManageDTO getNextStep(FillForm fillForm, ClientSessionManager csm);
	
	public static final String NEXT = "Siguiente";
	public static final String FINISH = "Finalizar";
	public static final String SUBMIT = "isValidForm(true);";
	public static final String CLOSE = "isValidForm(false);";

}
