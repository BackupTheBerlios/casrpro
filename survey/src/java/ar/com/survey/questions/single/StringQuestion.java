package ar.com.survey.questions.single;

import ar.com.survey.questions.fields.StringField;


public class StringQuestion extends SingleFieldQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StringQuestion() {
		super();
	}
	@Override
	public StringField getField() {
		return (StringField) super.getField();
	}
	
	public String getStringField(int y) {
		return ((StringField) super.getField()).getString();
	}

	public void setField(String b) {
		super.setField(new StringField(b));
	}
	
	
}
