package ar.com.survey.questions.list;

import ar.com.survey.questions.fields.NumberField;


public class NumberListQuestion extends ListQuestion {


	private static final long serialVersionUID = 1L;

	public NumberListQuestion(int totalItems) {
	}

	@Override
	public NumberField getField(int y) {
		return (NumberField) super.getField(y);
	}
	
	public int getNumberField(int y) {
		return ((NumberField) super.getField(y)).getNumber();
	}
}
