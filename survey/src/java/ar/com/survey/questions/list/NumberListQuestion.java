package ar.com.survey.questions.list;

import ar.com.survey.questions.fields.NumberField;


public class NumberListQuestion extends ListQuestion {


	private static final long serialVersionUID = 1L;
	private int min, max, total;
	private String validationType;

	public NumberListQuestion(int totalItems) {
	}
	
	public NumberListQuestion() {
	}

	@Override
	public NumberField getField(int y) {
		return (NumberField) super.getField(y);
	}
	
	public int getNumberField(int y) {
		return ((NumberField) super.getField(y)).getNumber();
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getValidationType() {
		return validationType;
	}

	public void setValidationType(String validationType) {
		this.validationType = validationType;
	}
	
	
}
