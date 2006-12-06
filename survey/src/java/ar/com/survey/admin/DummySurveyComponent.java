package ar.com.survey.admin;

import java.util.Iterator;

import org.apache.log4j.Logger;

import ar.com.survey.model.Question;
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
			logger.info("preguntas: ");
			Iterator questions = section.getQuestions().iterator();
			while(questions.hasNext()){
				Question quest = (Question) questions.next();
				logger.info("question: " + quest.getTitle());
				logger.info("type: " + quest.getClass().getName());
			}
		}
	}

	public Survey getSurvey(String name) {
		return null;
	}
}
