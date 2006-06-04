package ar.com.survey.questions.list;

import ar.com.survey.questions.fields.CheckBoxField;


public class CheckBoxListQuestion extends ListQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CheckBoxListQuestion(int totalItems) {
		super(totalItems);
	}
	private CheckBoxListQuestion() {
		super(0);
	}
	@Override
	public CheckBoxField getField(int y) {
		return (CheckBoxField) super.getField(y);
	}
	
	public boolean getBooleanField(int y) {
		return ((CheckBoxField) super.getField(y)).isSelected();
	}

	public void setField(int y, boolean b) {
		super.setField(y, new CheckBoxField(b));
	}
	
	
}
