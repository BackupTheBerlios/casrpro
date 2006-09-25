package ar.com.survey.client;

import javax.servlet.http.HttpSession;

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
	public FlowManageDTO getNextStep(FillForm fillForm, HttpSession session);

}
