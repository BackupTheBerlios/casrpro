package ar.com.survey.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.com.survey.admin.db.CustomFilledSurveyDAO;
import ar.com.survey.admin.db.CustomSurveyDAO;
import ar.com.survey.model.FilledSurvey;
import ar.com.survey.model.Person;
import ar.com.survey.model.PersonDAO;
import ar.com.survey.model.Quota;
import ar.com.survey.model.Survey;
import ar.com.survey.model.SurveyDAO;
import ar.com.survey.model.enums.FilledSurveyStatus;
import ar.com.survey.model.enums.RestrictionType;
import ar.com.survey.model.enums.SurveyState;
import ar.com.survey.questions.fields.Field;
import ar.com.survey.util.DbPropsImpl;

public class ClientWebComponent {

	private CustomSurveyDAO sDAO;

	private PersonDAO pDAO;

	private CustomFilledSurveyDAO fDAO;

	private static Logger logger = Logger.getLogger(ClientWebComponent.class);

	public ClientWebComponent() {
		sDAO = new CustomSurveyDAO();
		pDAO = new PersonDAO();
		fDAO = new CustomFilledSurveyDAO();
	}

	public ActionForward getSurveyPage(String name, String token,
			HttpSession session, ActionMapping mapping) {

		Survey survey = new Survey();
		survey.setName(name);
		survey = sDAO.findBySurrogateKey(survey);
		if (survey == null)
			return mapping.findForward("surveyError");
		else if (!survey.getStatus().equals(SurveyState.DESIGN.getCode())) {
			if (!survey.getStatus().equals(SurveyState.OPEN.getCode()))
				return mapping.findForward("closedSurvey");
			else {
				// OPEN
				// check token required
				// load person
				if (RestrictionType.OPEN.getCode().equals(
						survey.getRestrictionType())) {
					// load anonymous
					// start filling
					Person anonymous = new Person();
					anonymous.setEmail(new DbPropsImpl()
							.getValue("AnonymousEmail"));
					anonymous = pDAO.findBySurrogateKey(anonymous);
					session.setAttribute("Person", anonymous);
				} else {
					// Restricted, check token if valid and then check that did
					// not vote before
					// load person
					// check

					if (token == null || token.length() < 2)
						return mapping.findForward("incorrectToken");

					Person person = new Person();
					person.setToken(token);
					List<Person> persons = pDAO.findByExample(person);
					if (persons.size() > 0) {
						person = persons.get(0);
						// Check if person has voted this survey before
						FilledSurvey fs = fDAO.getInstance(survey, person);
						if (fs!=null)
								return mapping.findForward("clientVoted");
						else {
							session.setAttribute("Person", person);
							session.setAttribute("Person", person);
						}
					} else
						return mapping.findForward("incorrectToken");
				}
				session.setAttribute("CurrentSurvey", survey);
				session.setAttribute("CurrentSection", survey.getSection(0));
				return mapping.findForward("answers");
			}

		} else {

			// DESIGN load anonymous and vote
			Person anonymous = new Person();
			DbPropsImpl db = new DbPropsImpl();
			anonymous.setEmail(db.getValue("AnonymousEmail"));
			anonymous = pDAO.findBySurrogateKey(anonymous);
			session.setAttribute("Person", anonymous);
			session.setAttribute("CurrentSurvey", survey);
			session.setAttribute("CurrentSection", survey.getSection(0));
			return mapping.findForward("answers");
		}
	}

	public boolean getNextSurveySection(HttpSession session, int sectionId) {
		boolean retVal = true;
		Survey surv = (Survey) session.getAttribute("CurrentSurvey");
		if (surv.getSections().size() >= sectionId)
			session.setAttribute("CurrentSection", surv
					.getSection(sectionId - 1));
		else
			retVal = false;
		return retVal;
	}

	public void persistAnswers(HttpSession session) {
		Person person = (Person) session.getAttribute("Person");
		Survey survey = (Survey) session.getAttribute("CurrentSurvey");
		ArrayList answers = (ArrayList) session.getAttribute("answers");
		Collection<Field> col = new ArrayList<Field>();
		Iterator iter = answers.iterator();
		while (iter.hasNext()) {
			ArrayList<Field> af = (ArrayList<Field>) iter.next();
			Iterator iter2 = af.iterator();
			while (iter2.hasNext()) {
				Field f = (Field) iter2.next();
//				logger.info("Question: " + f.getQuestion().getTitle());
//				logger.info("value: " + f.getValue());
//				logger.info("Pos: " + f.getXpos() + " " + f.getYpos());
				col.add(f);
			}
		}
		person = new PersonDAO().findBySurrogateKey(person);
		survey = new SurveyDAO().findBySurrogateKey(survey);
		FilledSurvey fs = new FilledSurvey(col, Calendar.getInstance(),
				Calendar.getInstance(),
				FilledSurveyStatus.COMPLETADO.getCode(), 0, person, survey);
		Collection<FilledSurvey> cols = survey.getFilledSurveys2();
		if(cols!=null)
			cols.add(fs);
		Collection<FilledSurvey> ccols = person.getFilledSurveys();
		ccols.add(fs);
		// new SurveyDAO().createOrUpdate(survey);
		new CustomFilledSurveyDAO().createNew(fs);
		
		// udpdate quotas in session
		
		if (session.getAttribute("quotaUpdates") != null) {
			ArrayList<String> quotas = (ArrayList<String>) session
					.getAttribute("quotaUpdates");
			Iterator qiter = quotas.iterator();
			while(qiter.hasNext()){
				String quota = (String) qiter.next();
				if(quota.indexOf("--")!=-1){
					int pos = quota.indexOf("--");
					int qpos = Integer.parseInt(quota.substring(1,pos))-1;
					
					Survey s = (Survey) session
							.getAttribute("CurrentSurvey");
					s = new CustomSurveyDAO().findBySurrogateKey(s);
					Iterator<Quota> qIter = s.getQuotas().iterator();
					int index = 0;
					while (qIter.hasNext()) {
						Quota q = qIter.next();
						if (qpos == index) {
							q.setCompleted(q.getCompleted()-1);
							break;
						}
						index++;
					}
					
				}
				else {
					int pos = quota.indexOf("++");
					int qpos = Integer.parseInt(quota.substring(1,pos))-1;
					
					Survey s = (Survey) session
							.getAttribute("CurrentSurvey");
					s = new CustomSurveyDAO().findBySurrogateKey(s);
					Iterator<Quota> qIter = s.getQuotas().iterator();
					int index = 0;
					while (qIter.hasNext()) {
						Quota q = qIter.next();
						if (qpos == index) {
							q.setCompleted(q.getCompleted()+1);
							break;
						}
						index++;
					}
				}
			}
			
			session.removeAttribute("quotaUpdates");
			
		}
		
		// remove attrs and invalidate session
		session.removeAttribute("CurrentSurvey");
		session.removeAttribute("answers");
		session.removeAttribute("Person");
		session.removeAttribute("CurrentSection");
		session.invalidate();
	}

}
