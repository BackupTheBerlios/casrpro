package ar.com.survey;

import java.util.Iterator;

import org.apache.log4j.Logger;

import ar.com.survey.model.Quota;
import ar.com.survey.model.Section;
import ar.com.survey.model.Survey;

public class DummySurveyComponent implements ISurveyComponent {

	private static Logger logger = Logger.getLogger(DummySurveyComponent.class);
	
	public void createSurvey(Survey s) {
		logger.info("Persist survey to database: ");
		displaySurvey(s);
	}

	public void updateSurvey(Survey s) {
		logger.info("Update database's survey ");
		displaySurvey(s);
	}

	public void deleteSurvey(Survey s) {
		logger.info("delete survey from database");
	}

	private void displaySurvey(Survey s){
		logger.info(s.getName());
		Iterator iter = s.getQuotas().iterator();
		while(iter.hasNext()){
			Quota quota = (Quota) iter.next();
			logger.info(quota.getName() + " " + quota.getLimit());
		}
		iter = s.getSections().iterator();
		while(iter.hasNext()){
			Section section = (Section) iter.next();
			logger.info("new section");
			logger.info(section.getName());
			logger.info(section.getFlowMgmtScript());
			logger.info(section.getQuotaMgmtScript());
		}
	}
}
