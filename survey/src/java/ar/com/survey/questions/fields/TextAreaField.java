package ar.com.survey.questions.fields;

public class TextAreaField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String string;
	public TextAreaField(String s) {
		this.string = s;
	}
	
	public TextAreaField() {
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
