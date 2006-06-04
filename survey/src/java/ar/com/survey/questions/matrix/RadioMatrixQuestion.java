package ar.com.survey.questions.matrix;

import ar.com.survey.questions.fields.RadioField;

public class RadioMatrixQuestion extends MatrixQuestion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RadioMatrixQuestion(int x, int y) {
		super(x, y);
	}
	private RadioMatrixQuestion() {
		super(0,0);
	}

	@Override
	public RadioField getField(int x, int y) {
		return (RadioField) super.getField(x, y);
	}
	
	public boolean getBooleanField(int x, int y) {
		return ((RadioField) super.getField(x, y)).isSelected();
	}

	public void setField(int x, int y, boolean b) {
		// TODO Auto-generated method stub
		super.setField(x, y, new RadioField(b));
	}
	
}
