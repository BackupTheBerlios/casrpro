package ar.com.survey.client;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.StringTokenizer;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ar.com.survey.admin.db.CustomSurveyDAO;
import ar.com.survey.client.dto.FlowManageDTO;
import ar.com.survey.model.Quota;
import ar.com.survey.model.Section;
import ar.com.survey.model.Survey;
import ar.com.survey.util.LineParser;
import ar.com.survey.web.struts.form.FillForm;

public class FlowManagerFacadeImpl implements IFlowManager {

	private static final Logger logger = Logger
			.getLogger(FlowManagerFacadeImpl.class);

	public FlowManageDTO getNextStep(FillForm fillForm, HttpSession session) {

		FlowManageDTO flowDTO = null;
		Survey survey = (Survey) session.getAttribute("CurrentClientSurvey");

		Section section = (Section) session.getAttribute("CurrentClientSection");
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
				nextSection = findMatch(fillForm, session, commands);
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
				nextSection = findMatch(fillForm, session, commands);
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
			pos = Integer.parseInt(nextSection);

		int size = survey.getSections().size();
		int result = size - pos;
		if (result > 1) {
			if (nextSection == null)
				flowDTO = new FlowManageDTO(NEXT, SUBMIT, pos + 1);
			else
				flowDTO = new FlowManageDTO(NEXT, SUBMIT, pos);
		} else {
			if (nextSection == null)
				flowDTO = new FlowManageDTO(FINISH, CLOSE, pos + 1);
			else
				flowDTO = new FlowManageDTO(FINISH, CLOSE, pos);
		}
		return flowDTO;
	}

	/*
	 * Each command consist of a question number, a condition and an operation
	 * to execute if the if compares a quota (q) command, then the operation
	 * should be a ++ or a -- if the if compares a question (p) command, then
	 * the operation should be a number indicating the next section to jump to
	 */
	private String findMatch(FillForm fillForm, HttpSession session,
			ArrayList<String> commands) {

		String nextPos = null;
		Iterator iter = commands.iterator();
		while (iter.hasNext() && nextPos == null) {
			// StringTokenizer st = new StringTokenizer((String) iter.next(), " ");
			LineParser st = new LineParser((String) iter.next(), ' ');
			try {
				int questionIndex = 0;
				if (st.countTokens() == 3)
					questionIndex = Integer.parseInt(st.nextToken());
				String condition = st.nextToken();
				String operation = st.nextToken();
				String answer = null;
				switch (questionIndex) {
				case 1:
					answer = fillForm.getUnique1();
					break;
				case 2:
					answer = fillForm.getUnique2();
					break;
				case 3:
					answer = fillForm.getUnique3();
					break;
				case 4:
					answer = fillForm.getUnique4();
				default:
					answer = fillForm.getUnique5();
					break;
				}

				// Before parsing check if its a jump condition
				
				if(condition.equals("Jump")){
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
						if (answer.equals(condition.substring(internalPos + 2))) {
							try {
								Integer.parseInt(operation);
								nextPos = operation;
							} catch (NumberFormatException nfe) {
								ArrayList<String> quotas = null;
								if (session.getAttribute("quotaUpdates") != null) {
									quotas = (ArrayList<String>) session
											.getAttribute("quotaUpdates");
								} else {
									quotas = new ArrayList<String>();
								}
								if (!quotas.contains(operation))
									quotas.add(operation);
								session.setAttribute("quotaUpdates", quotas);
							}
						}
					} else if (operator.charAt(0) == 'q') {

						int qIndex = Integer.parseInt(operator.substring(1)) - 1;
						Survey s = (Survey) session
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
						int ans = Integer.parseInt(answer);
						int res = Integer.parseInt(condition
								.substring(internalPos + 2));
						if (ans >= res) {
							try {
								Integer.parseInt(operation);
								nextPos = operation;
							} catch (NumberFormatException nfe) {
								ArrayList<String> quotas = null;
								if (session.getAttribute("quotaUpdates") != null) {
									quotas = (ArrayList<String>) session
											.getAttribute("quotaUpdates");
								} else {
									quotas = new ArrayList<String>();
								}
								if (!quotas.contains(operation))
									quotas.add(operation);
								session.setAttribute("quotaUpdates", quotas);
							}
						}
					} else if (operator.charAt(0) == 'q') {

						int qIndex = Integer.parseInt(operator.substring(1)) - 1;
						Survey s = (Survey) session
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
						int ans = Integer.parseInt(answer);
						int res = Integer.parseInt(condition
								.substring(internalPos + 2));
						if (ans <= res) {
							try {
								Integer.parseInt(operation);
								nextPos = operation;
							} catch (NumberFormatException nfe) {
								ArrayList<String> quotas = null;
								if (session.getAttribute("quotaUpdates") != null) {
									quotas = (ArrayList<String>) session
											.getAttribute("quotaUpdates");
								} else {
									quotas = new ArrayList<String>();
								}
								if (!quotas.contains(operation))
									quotas.add(operation);
								session.setAttribute("quotaUpdates", quotas);
							}
						}
					} else if (operator.charAt(0) == 'q') {

						int qIndex = Integer.parseInt(operator.substring(1)) - 1;
						Survey s = (Survey) session
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
						if (!answer.equals(condition.substring(internalPos + 2))) {
							try {
								Integer.parseInt(operation);
								nextPos = operation;
							} catch (NumberFormatException nfe) {
								ArrayList<String> quotas = null;
								if (session.getAttribute("quotaUpdates") != null) {
									quotas = (ArrayList<String>) session
											.getAttribute("quotaUpdates");
								} else {
									quotas = new ArrayList<String>();
								}
								if (!quotas.contains(operation))
									quotas.add(operation);
								session.setAttribute("quotaUpdates", quotas);
							}
						}
					} else if (operator.charAt(0) == 'q') {

						int qIndex = Integer.parseInt(operator.substring(1)) - 1;
						Survey s = (Survey) session
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
				logger.info("Error on line of script, ignoring it");
			}
			
			

		}

		return nextPos;
	}

}
