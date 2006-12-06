package ar.com.survey.questions.fields;

public class StringField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String string;
	public StringField(String s) {
		this.string = s;
	}
	
	public StringField() {
	}
	
	@Override
	public String getValue() {
		return this.string;
	}
	public String getString() {
		return string;
	}
	public void setString(String string) {
		this.string = string;
	}

}
