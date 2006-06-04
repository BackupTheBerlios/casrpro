package ar.com.survey.questions.fields;

public class NumberField extends Field{

	public int n;
	public NumberField(int v) {
		this.n = v;
	}

	@Override
	public String getValue() {
		return ""+n;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

}
