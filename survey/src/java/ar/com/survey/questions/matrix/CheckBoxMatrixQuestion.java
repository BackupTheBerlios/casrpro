package ar.com.survey.questions.matrix;

import ar.com.survey.questions.fields.CheckBoxField;

public class CheckBoxMatrixQuestion extends MatrixQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CheckBoxMatrixQuestion() {
		super(0,0);
	}

	public CheckBoxMatrixQuestion(int x, int y) {
		super(x, y);
	}

	@Override
	public CheckBoxField getField(int x, int y) {
		return (CheckBoxField) super.getField(x, y);
	}
	
	public boolean getBooleanField(int x, int y) {
		return ((CheckBoxField) super.getField(x, y)).isSelected();
	}

	public void setField(int x, int y, boolean b) {
		// TODO Auto-generated method stub
		super.setField(x, y, new CheckBoxField(b));
	}
	
}
