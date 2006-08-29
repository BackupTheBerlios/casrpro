package ar.com.survey.questions.list;

import ar.com.survey.questions.fields.BooleanField;


public class RadioButtonListQuestion extends ListQuestion {


	private static final long serialVersionUID = 1L;

	public RadioButtonListQuestion() {
	}
	
	@Override
	public BooleanField getField(int y) {
		return (BooleanField) super.getField(y);
	}
	
	public boolean getBooleanField(int y) {
		return ((BooleanField) super.getField(y)).isSelected();
	}
}
