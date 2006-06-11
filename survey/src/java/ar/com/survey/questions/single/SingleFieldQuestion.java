package ar.com.survey.questions.single;

import ar.com.survey.model.Question;
import ar.com.survey.questions.fields.Field;

abstract class SingleFieldQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SingleFieldQuestion() {
	}
	public Field getField() {
		return super.getFieldAt(0,0);
	}
}
