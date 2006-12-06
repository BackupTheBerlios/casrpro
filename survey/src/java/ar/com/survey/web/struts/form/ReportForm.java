package ar.com.survey.web.struts.form;

public class ReportForm extends BaseForm {
	
	private String surveyName, startDate, endDate, fstatus;

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getFstatus() {
		return fstatus;
	}

	public void setFstatus(String status) {
		fstatus = status;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSurveyName() {
		return surveyName;
	}

	public void setSurveyName(String surveyName) {
		this.surveyName = surveyName;
	}
	
	

}
