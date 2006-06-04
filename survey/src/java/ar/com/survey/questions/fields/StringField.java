package ar.com.survey.questions.fields;

public class StringField extends Field {

	String string;
	public StringField(String s) {
		this.string = s;
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
