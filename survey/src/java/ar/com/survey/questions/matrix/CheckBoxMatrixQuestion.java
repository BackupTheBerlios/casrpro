package ar.com.survey.questions.matrix;

import ar.com.survey.questions.fields.BooleanField;

public class CheckBoxMatrixQuestion extends MatrixQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CheckBoxMatrixQuestion() {
	}


	public BooleanField getField(int x, int y) {
		return (BooleanField) super.getFieldAt(x, y);
	}
	
	public boolean getBooleanField(int x, int y) {
		return ((BooleanField) super.getFieldAt(x, y)).isSelected();
	}

	
}
