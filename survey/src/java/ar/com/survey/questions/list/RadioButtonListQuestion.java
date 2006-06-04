package ar.com.survey.questions.list;

import ar.com.survey.questions.fields.RadioField;


public class RadioButtonListQuestion extends ListQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RadioButtonListQuestion() {
		super(0);
	}
	
	public RadioButtonListQuestion(int totalItems) {
		
		super(totalItems);
	}
	@Override
	public RadioField getField(int y) {
		return (RadioField) super.getField(y);
	}
	
	public boolean getBooleanField(int y) {
		return ((RadioField) super.getField(y)).isSelected();
	}

	public void setField(int y, boolean b) {
		super.setField(y, new RadioField(b));
	}
	
	
}
