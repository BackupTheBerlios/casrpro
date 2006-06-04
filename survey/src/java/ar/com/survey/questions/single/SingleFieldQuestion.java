package ar.com.survey.questions.single;

import ar.com.survey.model.GenericQuestionData;
import ar.com.survey.model.Question;
import ar.com.survey.questions.fields.Field;

abstract class SingleFieldQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SingleFieldQuestion() {
		genericQuestion = new GenericQuestionData(1,1);
	}
	public Field getField() {
		return genericQuestion.getField(0, 0);
	}

	public String getTitle() {
		return genericQuestion.getTitle();
	}

	protected void setField(Field f) {
		genericQuestion.setField(0, 0, f);
	}

	public void setTitle(String title) {
		genericQuestion.setTitle(title);
	}

}
