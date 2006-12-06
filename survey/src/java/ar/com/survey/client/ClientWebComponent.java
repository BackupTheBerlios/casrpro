package ar.com.survey.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ar.com.survey.admin.db.CustomFilledSurveyDAO;
import ar.com.survey.admin.db.CustomSurveyDAO;
import ar.com.survey.model.FilledSurvey;
import ar.com.survey.model.Person;
import ar.com.survey.model.PersonDAO;
import ar.com.survey.model.Question;
import ar.com.survey.model.Quota;
import ar.com.survey.model.Section;
import ar.com.survey.model.Survey;
import ar.com.survey.model.SurveyDAO;
import ar.com.survey.model.enums.FilledSurveyStatus;
import ar.com.survey.model.enums.RestrictionType;
import ar.com.survey.model.enums.SurveyState;
import ar.com.survey.questions.fields.BooleanField;
import ar.com.survey.questions.fields.Field;
import ar.com.survey.questions.fields.FieldFactory;
import ar.com.survey.questions.fields.NumberField;
import ar.com.survey.questions.fields.StringField;
import ar.com.survey.questions.list.CheckBoxListQuestion;
import ar.com.survey.questions.list.NumberListQuestion;
import ar.com.survey.questions.list.StringListQuestion;
import ar.com.survey.questions.matrix.CheckBoxMatrixQuestion;
import ar.com.survey.questions.single.StringQuestion;
import ar.com.survey.questions.single.TextAreaQuestion;
import ar.com.survey.util.DbPropsImpl;
import ar.com.survey.web.struts.form.FillForm;

public class ClientWebComponent {

	private CustomSurveyDAO sDAO;

	private PersonDAO pDAO;

	private CustomFilledSurveyDAO fDAO;

	public ClientWebComponent() {
		sDAO = new CustomSurveyDAO();
		pDAO = new PersonDAO();
		fDAO = new CustomFilledSurveyDAO();
	}

	public ActionForward getSurveyPage(String name, String token,
			ClientSessionManager csm, ActionMapping mapping) {

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
					csm.setAttribute("Person", anonymous);
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
							csm.setAttribute("Person", person);
							csm.setAttribute("Person", person);
						}
					} else
						return mapping.findForward("incorrectToken");
				}
				csm.setAttribute("CurrentClientSurvey", survey);
				csm.setAttribute("CurrentClientSection", survey.getSection(0));
				return mapping.findForward("answers");
			}

		} else {

			// DESIGN load anonymous and vote
			Person anonymous = new Person();
			DbPropsImpl db = new DbPropsImpl();
			anonymous.setEmail(db.getValue("AnonymousEmail"));
			anonymous = pDAO.findBySurrogateKey(anonymous);
			csm.setAttribute("Person", anonymous);
			csm.setAttribute("CurrentClientSurvey", survey);
			csm.setAttribute("CurrentClientSection", survey.getSection(0));
			return mapping.findForward("answers");
		}
	}

	public boolean getNextSurveySection(ClientSessionManager csm, int sectionId) {
		boolean retVal = true;
		Survey surv = (Survey) csm.getAttribute("CurrentClientSurvey");
		if (surv.getSections().size() >= sectionId)
			csm.setAttribute("CurrentClientSection", surv
					.getSection(sectionId - 1));
		else
			retVal = false;
		return retVal;
	}

	public void addAnswersToSession(ArrayList answers, Section section, FillForm fform){
		
		// iterate the questions and fill fields as required
		Iterator iter = section.getQuestions().iterator();
		int index = 1;
		int lastString = 0;
		while (iter.hasNext()) {
			Question question = (Question) iter.next();
			if (question instanceof TextAreaQuestion) {
				TextAreaQuestion qs = (TextAreaQuestion) question;
				String[] values = fform.getTxtAnswer();
				Field field = FieldFactory.stringField(
						fform.getTxtAnswer()[lastString], question);
				lastString++;
				Collection<Field> col = new ArrayList<Field>(1);
				col.add(field);
				answers.add(col);
			} else if (question instanceof StringQuestion) {
				StringQuestion qs = (StringQuestion) question;
				String[] values = fform.getTxtAnswer();
				Field field = FieldFactory.stringField(values[lastString],
						question);
				lastString++;
				Collection<Field> col = new ArrayList<Field>(1);
				col.add(field);
				answers.add(col);
			} else if (question instanceof NumberListQuestion) {
				String[] numbers = null;
				switch (index) {
				case 1:
					numbers = fform.getNumber1();
					break;
				case 2:
					numbers = fform.getNumber2();
					break;
				case 3:
					numbers = fform.getNumber3();
					break;
				case 4:
					numbers = fform.getNumber4();
					break;
				default:
					numbers = fform.getNumber5();
					break;
				}
				Collection<Field> col = new ArrayList<Field>(numbers.length);
				for (int i = 0; i < numbers.length; i++) {
					NumberField field = FieldFactory.numberField(Integer
							.parseInt(numbers[i]), question, 0);
					col.add(field);
				}
				answers.add(col);
			} else if (question instanceof StringListQuestion) {
				StringListQuestion qs = (StringListQuestion) question;
				String selected = null;
				switch (index) {
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
				List<String> items = qs.getItems();
				Collection<Field> col = new ArrayList<Field>(items.size());
				Iterator iterator = items.iterator();
				int innerIndex = 0;
				while (iterator.hasNext()) {
					String temp = (String) iterator.next();
					BooleanField bfield = selected.equals(temp) ? FieldFactory
							.booleanField(true, question, innerIndex)
							: FieldFactory.booleanField(false, question,
									innerIndex);
					col.add(bfield);
					innerIndex++;
				}
				answers.add(col);
			} else if (question instanceof CheckBoxListQuestion) {
				CheckBoxListQuestion cq = (CheckBoxListQuestion) question;
				String[] selected = null;
				switch (index) {
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
				int innerIndex = 0;
				List<String> items = cq.getItems();
				Collection<Field> col = new ArrayList<Field>(items.size());
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					String temp = (String) iterator.next();

					boolean found = false;
					for (int x = 0; x < selected.length; x++) {
						if (selected[x].equals(temp))
							found = true;
					}
					BooleanField bfield = found ? FieldFactory.booleanField(
							true, question, innerIndex) : FieldFactory
							.booleanField(false, question, innerIndex);
					col.add(bfield);
					innerIndex++;
				}
				answers.add(col);
			} else if (question instanceof CheckBoxMatrixQuestion) {
				CheckBoxMatrixQuestion cx = (CheckBoxMatrixQuestion) question;
				Map matrix = null;
				int items = cx.getItems().size();
				int cols = cx.getColumnsTitles().size();
				switch (index) {
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
				Collection<Field> col = new ArrayList<Field>();
				for (int z = 0; z < items; z++) {
					String key = "value" + (z + 1) + "1";
					StringField sf = FieldFactory.stringField((String) matrix
							.get(key), question);
					col.add(sf);
				}
				answers.add(col);
			}
			index++;
		}
		
	}
	
	public void persistAnswers(ClientSessionManager csm) {
		Person person = (Person) csm.getAttribute("Person");
		Survey survey = (Survey) csm.getAttribute("CurrentClientSurvey");
		ArrayList answers = (ArrayList) csm.getAttribute("answers");
		Collection<Field> col = new ArrayList<Field>();
		Iterator iter = answers.iterator();
		while (iter.hasNext()) {
			ArrayList<Field> af = (ArrayList<Field>) iter.next();
			Iterator iter2 = af.iterator();
			while (iter2.hasNext()) {
				Field f = (Field) iter2.next();
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
		
		if (csm.getAttribute("quotaUpdates") != null) {
			ArrayList<String> quotas = (ArrayList<String>) csm.getAttribute("quotaUpdates");
			Iterator qiter = quotas.iterator();
			while(qiter.hasNext()){
				String quota = (String) qiter.next();
				if(quota.indexOf("--")!=-1){
					int pos = quota.indexOf("--");
					int qpos = Integer.parseInt(quota.substring(1,pos))-1;
					
					Survey s = (Survey) csm.getAttribute("CurrentClientSurvey");
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
					
					Survey s = (Survey) csm.getAttribute("CurrentClientSurvey");
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
			
			csm.removeAttribute("quotaUpdates");
			
		}
		
		// remove attrs and invalidate session
		csm.removeAttribute("CurrentClientSurvey");
		csm.removeAttribute("answers");
		csm.removeAttribute("Person");
		csm.removeAttribute("CurrentClientSection");
		// session.invalidate();
	}
	
	/**
	 * 
	 * This method persists temporal answers to disk, this includes just the last section's answers
	 * 
	 * @param answers
	 * @param section
	 * @param fform
	 */
	public void persistSectionAnswers(FilledSurvey fs, Section section, FillForm fform, ClientSessionManager csm){
		
		Collection<Field> col = new ArrayList<Field>();
		// iterate the questions and fill fields as required
		Iterator iter = section.getQuestions().iterator();
		int index = 1;
		int lastString = 0;
		while (iter.hasNext()) {
			Question question = (Question) iter.next();
			if (question instanceof TextAreaQuestion) {
				TextAreaQuestion qs = (TextAreaQuestion) question;
				String[] values = fform.getTxtAnswer();
				Field field = FieldFactory.stringField(
						fform.getTxtAnswer()[lastString], question);
				lastString++;
				col.add(field);
			} else if (question instanceof StringQuestion) {
				StringQuestion qs = (StringQuestion) question;
				String[] values = fform.getTxtAnswer();
				Field field = FieldFactory.stringField(values[lastString],
						question);
				lastString++;
				col.add(field);
			} else if (question instanceof NumberListQuestion) {
				String[] numbers = null;
				switch (index) {
				case 1:
					numbers = fform.getNumber1();
					break;
				case 2:
					numbers = fform.getNumber2();
					break;
				case 3:
					numbers = fform.getNumber3();
					break;
				case 4:
					numbers = fform.getNumber4();
					break;
				default:
					numbers = fform.getNumber5();
					break;
				}
				for (int i = 0; i < numbers.length; i++) {
					NumberField field = FieldFactory.numberField(Integer
							.parseInt(numbers[i]), question, 0);
					col.add(field);
				}
			} else if (question instanceof StringListQuestion) {
				StringListQuestion qs = (StringListQuestion) question;
				String selected = null;
				switch (index) {
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
				List<String> items = qs.getItems();
				Iterator iterator = items.iterator();
				int innerIndex = 0;
				while (iterator.hasNext()) {
					String temp = (String) iterator.next();
					BooleanField bfield = selected.equals(temp) ? FieldFactory
							.booleanField(true, question, innerIndex)
							: FieldFactory.booleanField(false, question,
									innerIndex);
					col.add(bfield);
					innerIndex++;
				}
			} else if (question instanceof CheckBoxListQuestion) {
				CheckBoxListQuestion cq = (CheckBoxListQuestion) question;
				String[] selected = null;
				switch (index) {
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
				int innerIndex = 0;
				List<String> items = cq.getItems();
				Iterator iterator = items.iterator();
				while (iterator.hasNext()) {
					String temp = (String) iterator.next();

					boolean found = false;
					for (int x = 0; x < selected.length; x++) {
						if (selected[x].equals(temp))
							found = true;
					}
					BooleanField bfield = found ? FieldFactory.booleanField(
							true, question, innerIndex) : FieldFactory
							.booleanField(false, question, innerIndex);
					col.add(bfield);
					innerIndex++;
				}
			} else if (question instanceof CheckBoxMatrixQuestion) {
				CheckBoxMatrixQuestion cx = (CheckBoxMatrixQuestion) question;
				Map matrix = null;
				int items = cx.getItems().size();
				int cols = cx.getColumnsTitles().size();
				switch (index) {
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
				for (int z = 0; z < items; z++) {
					String key = "value" + (z + 1) + "1";
					StringField sf = FieldFactory.stringField((String) matrix
							.get(key), question);
					col.add(sf);
				}
			}
			index++;
		}
		
		// persist this section's fields to db
		// fs.getState().equals(FilledSurveyStatus.INCOMPLETO.getCode())
		fs = fDAO.findById(fs.getId());
		if(fs!=null){
			Iterator itc = col.iterator();
			while(itc.hasNext()){
				fs.getFields().add((Field)itc.next());
			}
			new CustomFilledSurveyDAO().createOrUpdate(fs);
		}
		else {
			Person person = (Person) csm.getAttribute("Person");
			Survey survey = (Survey) csm.getAttribute("CurrentClientSurvey");
			person = new PersonDAO().findBySurrogateKey(person);
			survey = new SurveyDAO().findBySurrogateKey(survey);
			fs = new FilledSurvey(col, Calendar.getInstance(),
					null,
					FilledSurveyStatus.INCOMPLETO.getCode(), 0, person, survey);
			Collection<FilledSurvey> cols = survey.getFilledSurveys2();
			if(cols!=null)
				cols.add(fs);
			Collection<FilledSurvey> ccols = person.getFilledSurveys();
			ccols.add(fs);
			
			new SurveyDAO().createOrUpdate(survey);
			
			new CustomFilledSurveyDAO().createNew(fs);
		}
		
		csm.setAttribute("FilledSurvey", fs);
		
		
	}
	
	public void finishSurvey(ClientSessionManager csm) {
		Person person = (Person) csm.getAttribute("Person");
		Survey survey = (Survey) csm.getAttribute("CurrentClientSurvey");
		person = new PersonDAO().findBySurrogateKey(person);
		survey = new SurveyDAO().findBySurrogateKey(survey);
		
		FilledSurvey fs = (FilledSurvey) csm.getAttribute("FilledSurvey");
		fs = fDAO.findById(fs.getId());
		fs.setState(FilledSurveyStatus.COMPLETADO.getCode());
		fs.setFinishDate(Calendar.getInstance());
		fDAO.createOrUpdate(fs);
		
		// udpdate quotas in session
		
		if (csm.getAttribute("quotaUpdates") != null) {
			ArrayList<String> quotas = (ArrayList<String>) csm.getAttribute("quotaUpdates");
			Iterator qiter = quotas.iterator();
			while(qiter.hasNext()){
				String quota = (String) qiter.next();
				if(quota.indexOf("--")!=-1){
					int pos = quota.indexOf("--");
					int qpos = Integer.parseInt(quota.substring(1,pos))-1;
					
					Survey s = (Survey) csm.getAttribute("CurrentClientSurvey");
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
					
					Survey s = (Survey) csm.getAttribute("CurrentClientSurvey");
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
			
			csm.removeAttribute("quotaUpdates");
			
		}
		
		// remove attrs and invalidate session
		csm.removeAttribute("CurrentClientSurvey");
		csm.removeAttribute("Person");
		csm.removeAttribute("CurrentClientSection");
		csm.removeAttribute("FilledSurvey");
		// session.invalidate();
	}

}
