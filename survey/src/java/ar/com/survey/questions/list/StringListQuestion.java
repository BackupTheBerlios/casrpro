package ar.com.survey.questions.list;

import ar.com.survey.questions.fields.StringField;


public class StringListQuestion extends ListQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StringListQuestion() {
	}
	
	@Override
	public StringField getField(int y) {
		return (StringField) super.getField(y);
	}
	
	public String getStringField(int y) {
		return ((StringField) super.getField(y)).getString();
	}
}
