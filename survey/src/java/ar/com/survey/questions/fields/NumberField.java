package ar.com.survey.questions.fields;

public class NumberField extends Field{

	public int number;
	public NumberField(int v) {
		this.number = v;
	}

	@Override
	public String getValue() {
		return ""+number;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int n) {
		this.number = n;
	}

}
