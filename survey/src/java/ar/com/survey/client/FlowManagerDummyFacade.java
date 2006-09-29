package ar.com.survey.client;

import javax.servlet.http.HttpSession;

import ar.com.survey.client.dto.FlowManageDTO;
import ar.com.survey.model.Survey;
import ar.com.survey.web.struts.form.FillForm;

public class FlowManagerDummyFacade implements IFlowManager {

	private static final String NEXT = "Siguiente";
	private static final String FINISH = "Finalizar";
	private static final String SUBMIT = "isValidForm(true);";
	private static final String CLOSE = "isValidForm(false);window.close();";
	
	public FlowManageDTO getNextStep(FillForm fillForm, HttpSession session) {
		
		FlowManageDTO flowDTO = null;
		Survey survey = (Survey) session.getAttribute("CurrentSurvey");
		int pos = (fillForm.getNextPos()==null) ? 0 : Integer.parseInt(fillForm.getNextPos());
		int size = survey.getSections().size();
		int result = size - pos;
		if(result>1){
			flowDTO = new FlowManageDTO(NEXT, SUBMIT, pos+1);
		} else {
			flowDTO = new FlowManageDTO(FINISH, CLOSE, pos+1);
		}
		return flowDTO;
	}

}
