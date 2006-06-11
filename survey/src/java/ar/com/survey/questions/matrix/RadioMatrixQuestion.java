package ar.com.survey.questions.matrix;

import ar.com.survey.questions.fields.BooleanField;

public class RadioMatrixQuestion extends MatrixQuestion {


	private static final long serialVersionUID = 1L;

	public RadioMatrixQuestion(int x, int y) {
	}

	public BooleanField getField(int x, int y) {
		return (BooleanField) super.getFieldAt(x, y);
	}
	
	public boolean getBooleanFieldAt(int x, int y) {
		return ((BooleanField) super.getFieldAt(x, y)).isSelected();
	}
}
