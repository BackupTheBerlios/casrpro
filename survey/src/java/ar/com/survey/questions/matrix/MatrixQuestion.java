package ar.com.survey.questions.matrix;

import java.util.List;

import ar.com.survey.model.Question;

abstract class MatrixQuestion extends Question{

	protected MatrixQuestion() {
	}
	final public List<String> getItems() { 
		return super.getRowTitles();
	}
	final public void setItems(List<String> s) {
		super.setRowTitles(s);
	}
	final public List<String> getColumnsTitles() {
		return super.getColumnTitles();
	}
	final public void setColumnsTitles(List<String> s) {
		super.setColumnTitles(s);
	}
}
