package ar.com.survey.questions.list;

import ar.com.survey.questions.fields.StringField;


public class StringListQuestion extends ListQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private StringListQuestion() {
		super(0);
	}
	
	public StringListQuestion(int totalItems) {
		super(totalItems);
	}
	@Override
	public StringField getField(int y) {
		return (StringField) super.getField(y);
	}
	
	public String getStringField(int y) {
		return ((StringField) super.getField(y)).getString();
	}

	public void setField(int y, String b) {
		super.setField(y, new StringField(b));
	}
	
	
}
