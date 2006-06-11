package ar.com.survey.questions.single;

import ar.com.survey.questions.fields.NumberField;


public class NumberQuestion extends SingleFieldQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumberQuestion() {
		super();
	}
	@Override
	public NumberField getField() {
		return (NumberField) super.getField();
	}
	
	public int getNumberField() {
		return ((NumberField) super.getField()).getNumber();
	}
}
