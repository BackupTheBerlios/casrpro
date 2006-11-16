package ar.com.survey.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import ar.com.survey.admin.db.CustomSurveyDAO;
import ar.com.survey.client.dto.FlowManageDTO;
import ar.com.survey.model.Question;
import ar.com.survey.model.Quota;
import ar.com.survey.model.Section;
import ar.com.survey.model.Survey;
import ar.com.survey.questions.list.CheckBoxListQuestion;
import ar.com.survey.questions.list.StringListQuestion;
import ar.com.survey.questions.matrix.CheckBoxMatrixQuestion;
import ar.com.survey.questions.single.StringQuestion;
import ar.com.survey.questions.single.TextAreaQuestion;
import ar.com.survey.util.LineParser;
import ar.com.survey.web.struts.form.FillForm;

public class FlowManagerFacadeImpl implements IFlowManager {

	private static final Logger logger = Logger
			.getLogger(FlowManagerFacadeImpl.class);

	public FlowManageDTO getNextStep(FillForm fillForm, ClientSessionManager csm) {

		FlowManageDTO flowDTO = null;
		Survey survey = (Survey) csm.getAttribute("CurrentClientSurvey");

		Section section = (Section) csm
				.getAttribute("CurrentClientSection");
		String quotaScript = section.getQuotaMgmtScript();
		String flowScript = section.getFlowMgmtScript();
		String nextSection = null;

		try {
			if (quotaScript != null && !quotaScript.trim().equals("")) {
				ArrayList<String> commands = new ArrayList<String>();
				StringTokenizer st = new StringTokenizer(quotaScript, ";");
				while (st.hasMoreElements()) {
					String temp = st.nextToken();
					if (temp.indexOf("\r\n") != -1)
						temp = temp.substring(temp.indexOf("\r\n") + 2);
					commands.add(temp);
				}
				nextSection = findMatch(fillForm, csm, commands);
			}

			if (nextSection == null && flowScript != null
					&& !flowScript.trim().equals("")) {
				ArrayList<String> commands = new ArrayList<String>();
				StringTokenizer st = new StringTokenizer(flowScript, ";");
				while (st.hasMoreElements()) {
					String temp = st.nextToken();
					if (temp.indexOf("\r\n") != -1)
						temp = temp.substring(temp.indexOf("\r\n") + 2);
					commands.add(temp);
				}
				nextSection = findMatch(fillForm, csm, commands);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			nextSection = null;
		}

		int pos;

		if (nextSection == null)
			pos = (fillForm.getNextPos() == null) ? 0 : Integer
					.parseInt(fillForm.getNextPos());
		else
			pos = Integer.parseInt(nextSection) - 1;

		int size = survey.getSections().size();
		int result = size - pos;
		if (result > 1) {
			if (nextSection == null)
				flowDTO = new FlowManageDTO(NEXT, SUBMIT, pos + 1);
			else
				flowDTO = new FlowManageDTO(NEXT, SUBMIT, pos + 1);
		} else {
			if (nextSection == null)
				flowDTO = new FlowManageDTO(FINISH, CLOSE, pos + 1);
			else
				flowDTO = new FlowManageDTO(FINISH, CLOSE, pos + 1);
		}
		return flowDTO;
	}

	/*
	 * Each command consist of a question number, a condition and an operation
	 * to execute if the if compares a quota (q) command, then the operation
	 * should be a ++ or a -- if the if compares a question (p) command, then
	 * the operation should be a number indicating the next section to jump to
	 */
	private String findMatch(FillForm fillForm, ClientSessionManager csm,
			ArrayList<String> commands) {

		String nextPos = null;
		Iterator iter = commands.iterator();
		Section section = (Section) csm
				.getAttribute("CurrentClientSection");
		while (iter.hasNext() && nextPos == null) {
			LineParser st = new LineParser((String) iter.next(), ' ');
			try {
				int questionIndex = 0;
				if (st.countTokens() == 3)
					questionIndex = Integer.parseInt(st.nextToken());
				String condition = st.nextToken();
				String operation = st.nextToken();
			
				// Before parsing check if its a jump condition

				if (condition.equals("Jump")) {
					nextPos = operation;
					break;
				}

				// parse condition to see if it's a q or a p
				// a condition should be some like p1==asdajds 11

				// 11 p1==asda 11
				String operator = null;
				if (condition.indexOf("==") != -1) {
					int internalPos = condition.indexOf("==");
					operator = condition.substring(0, internalPos);

					if (operator.charAt(0) == 'p') {
						// if (answer.equals(condition.substring(internalPos +
						// 2))) {
						if (isEqualTo(questionIndex, condition
								.substring(internalPos + 2), section, fillForm)) {
							try {
								Integer.parseInt(operation);
								nextPos = operation;
							} catch (NumberFormatException nfe) {
								ArrayList<String> quotas = null;
								if (csm.getAttribute("quotaUpdates") != null) {
									quotas = (ArrayList<String>) csm
											.getAttribute("quotaUpdates");
								} else {
									quotas = new ArrayList<String>();
								}
								if (!quotas.contains(operation))
									quotas.add(operation);
								csm.setAttribute("quotaUpdates", quotas);
							}
						}
					} else if (operator.charAt(0) == 'q') {

						int qIndex = Integer.parseInt(operator.substring(1)) - 1;
						Survey s = (Survey) csm
								.getAttribute("CurrentClientSurvey");
						s = new CustomSurveyDAO().findBySurrogateKey(s);
						Iterator<Quota> qIter = s.getQuotas().iterator();
						int index = 0;
						while (qIter.hasNext()) {
							Quota q = qIter.next();
							if (qIndex == index) {
								if (q.getCompleted() == Integer
										.parseInt(condition
												.substring(internalPos + 2))) {
									// operation should be a string
									// representation of an int index of a
									// section
									nextPos = operation;
								}
								break;
							}
							index++;
						}
					}

				} else if (condition.indexOf(">=") != -1) {

					int internalPos = condition.indexOf(">=");
					operator = condition.substring(0, internalPos);
					if (operator.charAt(0) == 'p') {
						// int ans = Integer.parseInt(answer);
						// int res = Integer.parseInt();
						if (this.isGreaterOrEqual(questionIndex, condition
								.substring(internalPos + 2), section, fillForm)) {
							try {
								Integer.parseInt(operation);
								nextPos = operation;
							} catch (NumberFormatException nfe) {
								ArrayList<String> quotas = null;
								if (csm.getAttribute("quotaUpdates") != null) {
									quotas = (ArrayList<String>) csm
											.getAttribute("quotaUpdates");
								} else {
									quotas = new ArrayList<String>();
								}
								if (!quotas.contains(operation))
									quotas.add(operation);
								csm.setAttribute("quotaUpdates", quotas);
							}
						}
					} else if (operator.charAt(0) == 'q') {

						int qIndex = Integer.parseInt(operator.substring(1)) - 1;
						Survey s = (Survey) csm
								.getAttribute("CurrentClientSurvey");
						s = new CustomSurveyDAO().findBySurrogateKey(s);
						Iterator<Quota> qIter = s.getQuotas().iterator();
						int index = 0;
						while (qIter.hasNext()) {
							Quota q = qIter.next();
							if (qIndex == index) {
								if (q.getCompleted() >= Integer
										.parseInt(condition
												.substring(internalPos + 2))) {
									// operation should be a string
									// representation of an int index of a
									// section
									nextPos = operation;
								}
								break;
							}
							index++;
						}
					}

				} else if (condition.indexOf("<=") != -1) {

					int internalPos = condition.indexOf("<=");
					operator = condition.substring(0, internalPos);
					if (operator.charAt(0) == 'p') {
						// int ans = Integer.parseInt(answer);
						// int res = Integer.parseInt(condition
						// .substring(internalPos + 2));
						if (this.isLessOrEqual(questionIndex, condition
								.substring(internalPos + 2), section, fillForm)) {
							try {
								Integer.parseInt(operation);
								nextPos = operation;
							} catch (NumberFormatException nfe) {
								ArrayList<String> quotas = null;
								if (csm.getAttribute("quotaUpdates") != null) {
									quotas = (ArrayList<String>) csm
											.getAttribute("quotaUpdates");
								} else {
									quotas = new ArrayList<String>();
								}
								if (!quotas.contains(operation))
									quotas.add(operation);
								csm.setAttribute("quotaUpdates", quotas);
							}
						}
					} else if (operator.charAt(0) == 'q') {

						int qIndex = Integer.parseInt(operator.substring(1)) - 1;
						Survey s = (Survey) csm
								.getAttribute("CurrentClientSurvey");
						s = new CustomSurveyDAO().findBySurrogateKey(s);
						Iterator<Quota> qIter = s.getQuotas().iterator();
						int index = 0;
						while (qIter.hasNext()) {
							Quota q = qIter.next();
							if (qIndex == index) {
								if (q.getCompleted() <= Integer
										.parseInt(condition
												.substring(internalPos + 2))) {
									// operation should be a string
									// representation of an int index of a
									// section
									nextPos = operation;
								}
								break;
							}
							index++;
						}
					}

				} else if (condition.indexOf("!=") != -1) {

					int internalPos = condition.indexOf("!=");
					operator = condition.substring(0, internalPos);

					if (operator.charAt(0) == 'p') {
						if (!this.isEqualTo(questionIndex, condition
								.substring(internalPos + 2), section, fillForm)) {
							try {
								Integer.parseInt(operation);
								nextPos = operation;
							} catch (NumberFormatException nfe) {
								ArrayList<String> quotas = null;
								if (csm.getAttribute("quotaUpdates") != null) {
									quotas = (ArrayList<String>) csm
											.getAttribute("quotaUpdates");
								} else {
									quotas = new ArrayList<String>();
								}
								if (!quotas.contains(operation))
									quotas.add(operation);
								csm.setAttribute("quotaUpdates", quotas);
							}
						}
					} else if (operator.charAt(0) == 'q') {

						int qIndex = Integer.parseInt(operator.substring(1)) - 1;
						Survey s = (Survey) csm
								.getAttribute("CurrentClientSurvey");
						s = new CustomSurveyDAO().findBySurrogateKey(s);
						Iterator<Quota> qIter = s.getQuotas().iterator();
						int index = 0;
						while (qIter.hasNext()) {
							Quota q = qIter.next();
							if (qIndex == index) {
								if (q.getCompleted() == Integer
										.parseInt(condition
												.substring(internalPos + 2))) {
									// operation should be a string
									// representation of an int index of a
									// section
									nextPos = operation;
								}
								break;
							}
							index++;
						}
					}
				}

			} catch (Exception e) {
				logger.info("Error on line of script, ignoring it: "
						+ e.getMessage());
			}

		}

		return nextPos;
	}

	// comparators for different question types

	private boolean isEqualTo(int questionPos, String checkAnswer,
			Section section, FillForm fform) {

		boolean retValue = false;
		Question ques = section.getQuestions().get(questionPos - 1);
		if (ques instanceof StringListQuestion) {

			StringListQuestion qs = (StringListQuestion) ques;
			String selected = null;
			switch (questionPos) {
			case 1:
				selected = fform.getUnique1();
				break;
			case 2:
				selected = fform.getUnique2();
				break;
			case 3:
				selected = fform.getUnique3();
				break;
			case 4:
				selected = fform.getUnique4();
				break;
			default:
				selected = fform.getUnique5();
				break;
			}

			if (checkAnswer.equals(selected))
				retValue = true;
			else
				retValue = false;

		} else if (ques instanceof CheckBoxListQuestion) {

			CheckBoxListQuestion cq = (CheckBoxListQuestion) ques;
			String[] selected = null;
			switch (questionPos) {
			case 1:
				selected = fform.getCheck1();
				break;
			case 2:
				selected = fform.getCheck2();
				break;
			case 3:
				selected = fform.getCheck3();
				break;
			case 4:
				selected = fform.getCheck4();
				break;
			default:
				selected = fform.getCheck5();
				break;
			}
			for (int i = 0; i < selected.length; i++) {
				if (selected[i].equals(checkAnswer)) {
					retValue = true;
					break;
				}
			}
		} else if (ques instanceof TextAreaQuestion
				|| ques instanceof StringQuestion) {
			
//			 given the pos check if there were previous posted strings
			int indexCount = 1;
			int index = 0;
			Iterator internalIter = section.getQuestions().iterator();
			while(internalIter.hasNext() && indexCount<questionPos){
				Question q = (Question) internalIter.next();
				indexCount++;
				if(q instanceof TextAreaQuestion || q instanceof StringQuestion)
					index++;
			}
			String value = fform.getTxtAnswer()[index];
			if (value.equals(checkAnswer))
				retValue = true;
			else
				retValue = false;
			
		} else if (ques instanceof CheckBoxMatrixQuestion) {
			
			// iterate through responses and see if they equal the comma separated values from checkAnswer
			
			Map matrix = null;
			switch (questionPos) {
			case 1:
				matrix = fform.getMatrix1();
				break;
			case 2:
				matrix = fform.getMatrix2();
				break;
			case 3:
				matrix = fform.getMatrix3();
				break;
			case 4:
				matrix = fform.getMatrix4();
				break;
			default:
				matrix = fform.getMatrix5();
				break;
			}
			
			int counter = 0;
			int control = 0;
			StringTokenizer st = new StringTokenizer(checkAnswer, ",");
			int max = st.countTokens();
			while(counter<max && control<max){
				String token = st.nextToken();
				if(matrix.containsValue(token))
					counter++;
				control++;
			}
			
			retValue = (counter==max) ? true : false;
			
		}

		return retValue;

	}

	private boolean isGreaterOrEqual(int questionPos, String checkAnswer,
			Section section, FillForm fform) {

		boolean retValue = false;
		Question ques = section.getQuestions().get(questionPos - 1);
		int check = Integer.parseInt(checkAnswer);
		if (ques instanceof StringListQuestion) {

			StringListQuestion qs = (StringListQuestion) ques;
			String selected = null;
			switch (questionPos) {
			case 1:
				selected = fform.getUnique1();
				break;
			case 2:
				selected = fform.getUnique2();
				break;
			case 3:
				selected = fform.getUnique3();
				break;
			case 4:
				selected = fform.getUnique4();
				break;
			default:
				selected = fform.getUnique5();
				break;
			}

			int value = Integer.parseInt(selected);
			if (value >= check)
				retValue = true;
			else
				retValue = false;

		} else if (ques instanceof CheckBoxListQuestion) {

			CheckBoxListQuestion cq = (CheckBoxListQuestion) ques;
			String[] selected = null;
			switch (questionPos) {
			case 1:
				selected = fform.getCheck1();
				break;
			case 2:
				selected = fform.getCheck2();
				break;
			case 3:
				selected = fform.getCheck3();
				break;
			case 4:
				selected = fform.getCheck4();
				break;
			default:
				selected = fform.getCheck5();
				break;
			}
			for (int i = 0; i < selected.length; i++) {
				int value = Integer.parseInt(selected[i]);
				if (value >= check) {
					retValue = true;
					break;
				}
			}
		} else if (ques instanceof TextAreaQuestion
				|| ques instanceof StringQuestion) {
			
//			 given the pos check if there were previous posted strings
			int indexCount = 1;
			int index = 0;
			Iterator internalIter = section.getQuestions().iterator();
			while(internalIter.hasNext() && indexCount<questionPos){
				Question q = (Question) internalIter.next();
				indexCount++;
				if(q instanceof TextAreaQuestion || q instanceof StringQuestion)
					index++;
			}
			int value = Integer.parseInt(fform.getTxtAnswer()[index]);
			if (value >= check)
				retValue = true;
			else
				retValue = false;
			
		}

		return retValue;

	}

	private boolean isLessOrEqual(int questionPos, String checkAnswer,
			Section section, FillForm fform) {

		boolean retValue = false;
		Question ques = section.getQuestions().get(questionPos - 1);
		int check = Integer.parseInt(checkAnswer);
		if (ques instanceof StringListQuestion) {

			StringListQuestion qs = (StringListQuestion) ques;
			String selected = null;
			switch (questionPos) {
			case 1:
				selected = fform.getUnique1();
				break;
			case 2:
				selected = fform.getUnique2();
				break;
			case 3:
				selected = fform.getUnique3();
				break;
			case 4:
				selected = fform.getUnique4();
				break;
			default:
				selected = fform.getUnique5();
				break;
			}

			int value = Integer.parseInt(selected);
			if (value <= check)
				retValue = true;
			else
				retValue = false;

		} else if (ques instanceof CheckBoxListQuestion) {

			CheckBoxListQuestion cq = (CheckBoxListQuestion) ques;
			String[] selected = null;
			switch (questionPos) {
			case 1:
				selected = fform.getCheck1();
				break;
			case 2:
				selected = fform.getCheck2();
				break;
			case 3:
				selected = fform.getCheck3();
				break;
			case 4:
				selected = fform.getCheck4();
				break;
			default:
				selected = fform.getCheck5();
				break;
			}
			for (int i = 0; i < selected.length; i++) {
				int value = Integer.parseInt(selected[i]);
				if (value <= check) {
					retValue = true;
					break;
				}
			}
		} else if (ques instanceof TextAreaQuestion
				|| ques instanceof StringQuestion) {
			
			// given the pos check if there were previous posted strings
			int indexCount = 1;
			int index = 0;
			Iterator internalIter = section.getQuestions().iterator();
			while(internalIter.hasNext() && indexCount<questionPos){
				Question q = (Question) internalIter.next();
				indexCount++;
				if(q instanceof TextAreaQuestion || q instanceof StringQuestion)
					index++;
			}
			int value = Integer.parseInt(fform.getTxtAnswer()[index]);
			if (value <= check)
				retValue = true;
			else
				retValue = false;

		} 

		return retValue;

	}

}
