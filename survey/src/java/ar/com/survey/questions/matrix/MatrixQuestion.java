package ar.com.survey.questions.matrix;

import ar.com.survey.model.GenericQuestionData;
import ar.com.survey.model.Question;
import ar.com.survey.questions.fields.Field;

abstract class MatrixQuestion extends Question{

	protected MatrixQuestion(int x, int y) {
		this.genericQuestion = new GenericQuestionData(x,y);
	}

	public String[] getAxisNamesX() {
		return genericQuestion.getAxisNamesX();
	}

	public String getAxisNamesX(int i) {
		return genericQuestion.getAxisNamesX(i);
	}

	public String[] getAxisNamesY() {
		return genericQuestion.getAxisNamesY();
	}

	public String getAxisNamesY(int i) {
		return genericQuestion.getAxisNamesY(i);
	}

	protected Field getField(int x, int y) {
		return genericQuestion.getField(x, y);
	}
	protected void setField(int x, int y, Field f) {
		genericQuestion.setField(x,y,f);
	}

	public String getTitle() {
		return genericQuestion.getTitle();
	}

	public void setAxisNamesX(String[] axisNamesX) {
		genericQuestion.setAxisNamesX(axisNamesX);
	}

	public void setAxisNamesY(String[] axisNamesY) {
		genericQuestion.setAxisNamesY(axisNamesY);
	}

	public void setTitle(String title) {
		genericQuestion.setTitle(title);
	}
	
	
}
