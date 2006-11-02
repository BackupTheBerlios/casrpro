package ar.com.survey.web.struts.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.survey.ISurveyComponent;
import ar.com.survey.client.ClientWebComponent;
import ar.com.survey.client.IFlowManager;
import ar.com.survey.client.dto.FlowManageDTO;
import ar.com.survey.model.Question;
import ar.com.survey.model.Section;
import ar.com.survey.questions.fields.BooleanField;
import ar.com.survey.questions.fields.Field;
import ar.com.survey.questions.fields.FieldFactory;
import ar.com.survey.questions.fields.NumberField;
import ar.com.survey.questions.list.CheckBoxListQuestion;
import ar.com.survey.questions.list.NumberListQuestion;
import ar.com.survey.questions.list.StringListQuestion;
import ar.com.survey.questions.matrix.CheckBoxMatrixQuestion;
import ar.com.survey.questions.single.StringQuestion;
import ar.com.survey.questions.single.TextAreaQuestion;
import ar.com.survey.web.struts.form.FillForm;

public class FillAction extends DispatchAction {

	private IFlowManager flowManager;

	private ISurveyComponent surveyComponent;

	private static Logger logger = Logger.getLogger(FillAction.class);

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {

		return null;
	}

	public ActionForward fill(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		ClientWebComponent cwp = new ClientWebComponent();
		ActionForward mapp = cwp.getSurveyPage(request.getParameter("sid"),
				request.getParameter("token"), request.getSession(), mapping);
		HttpSession session = request.getSession();
		ArrayList answers = new ArrayList();
		if (mapp.equals(mapping.findForward("answers"))) {
			FillForm fform = (FillForm) form;
			FlowManageDTO flowDTO = flowManager.getNextStep(fform, request
					.getSession());
			request.setAttribute("flowDTO", flowDTO);
			session.setAttribute("answers", answers);
		}
		return mapp;
	}

	public ActionForward fillNext(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		FillForm fform = (FillForm) form;
		HttpSession session = request.getSession();

		if(session.getAttribute("answers")==null)
			return mapping.findForward("invalidSession");
		
		ArrayList answers = (ArrayList) session.getAttribute("answers");
		
		// iterate the questions and fill fields as required

		Section section = (Section) session.getAttribute("CurrentSection");
		Iterator iter = section.getQuestions().iterator();
		int index = 1;
		while (iter.hasNext()) {
			Question question = (Question) iter.next();
			if (question instanceof TextAreaQuestion) {
				TextAreaQuestion qs = (TextAreaQuestion) question;
				Field field = FieldFactory.stringField(
						fform.getTxtAnswer()[index - 1], question);
				Collection<Field> col = new ArrayList<Field>(1);
				col.add(field);
				answers.add(col);
			} else if (question instanceof StringQuestion) {
				StringQuestion qs = (StringQuestion) question;
				Field field = FieldFactory.stringField(
						fform.getTxtAnswer()[index - 1], question);
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
					String key = "value" + (z + 1);
					for (int y = 0; y < cols; y++) {
						String tkey = key + (y + 1);
						if (matrix.get(tkey) != null) {
							BooleanField bfield = ((String) matrix.get(tkey))
									.equals("") ? FieldFactory.booleanField(
									false, question, y, z) : FieldFactory.booleanField(
									true, question, y, z);
							col.add(bfield);
						}
						else {
							col.add(FieldFactory.booleanField(false, question, y, z));
						}
					}
				}
				answers.add(col);
			}
			index++;
		}

		// Get Next step to display

		FlowManageDTO flowDTO = flowManager.getNextStep(fform, request
				.getSession());
		ClientWebComponent cwp = new ClientWebComponent();
		cwp.getNextSurveySection(request.getSession(), flowDTO.getSection());
		request.setAttribute("flowDTO", flowDTO);
		
		// add results to session
		fform.reset(mapping, request);
		
		return mapping.findForward("answers");
	}
	
	public ActionForward finish(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		
		FillForm fform = (FillForm) form;
		HttpSession session = request.getSession();

		if(session.getAttribute("answers")==null)
			return mapping.findForward("invalidSession");
		
		ArrayList answers = (ArrayList) session.getAttribute("answers");
		
		// iterate the questions and fill fields as required

		Section section = (Section) session.getAttribute("CurrentSection");
		Iterator iter = section.getQuestions().iterator();
		int index = 1;
		while (iter.hasNext()) {
			Question question = (Question) iter.next();
			if (question instanceof TextAreaQuestion) {
				TextAreaQuestion qs = (TextAreaQuestion) question;
				Field field = FieldFactory.stringField(
						fform.getTxtAnswer()[index - 1], question);
				Collection<Field> col = new ArrayList<Field>(1);
				col.add(field);
				answers.add(col);
			} else if (question instanceof StringQuestion) {
				StringQuestion qs = (StringQuestion) question;
				Field field = FieldFactory.stringField(
						fform.getTxtAnswer()[index - 1], question);
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
				StringBuffer key = new StringBuffer();
				for (int z = 0; z < items; z++) {
					 key = new StringBuffer("value");
					 key.append(Integer.toString(z + 1));
					for (int y = 0; y < cols; y++) {
						String tkey = key.toString() + Integer.toString(y + 1);
						if (matrix.get(tkey) != null) {
							BooleanField bfield = ((String) matrix.get(tkey))
									.equals("") ? FieldFactory.booleanField(
									false, question, y, z) : FieldFactory.booleanField(
									true, question, y, z);
							col.add(bfield);
						}
						else {
							col.add(FieldFactory.booleanField(false, question, y, z));
						}
					}
				}
				answers.add(col);
			}
			index++;
		}

		// add results to session
		fform.reset(mapping, request);
		
		ClientWebComponent cwp = new ClientWebComponent();
		cwp.persistAnswers(session);
		return mapping.findForward("finish");
	}

	public void setFlowManager(IFlowManager flowManager) {
		this.flowManager = flowManager;
	}

	public void setSurveyComponent(ISurveyComponent surveyComponent) {
		this.surveyComponent = surveyComponent;
	}

}
