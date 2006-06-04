package ar.com.survey.questions.list;

import ar.com.survey.model.GenericQuestionData;
import ar.com.survey.model.Question;
import ar.com.survey.questions.fields.Field;

abstract class ListQuestion extends Question {

	protected ListQuestion(int totalItems) {
		this.genericQuestion = new GenericQuestionData(1,totalItems);
	}
	
	final public String[] getItems() {
		return genericQuestion.getAxisNamesY();
	}

	public String getItems(int i) {
		return genericQuestion.getAxisNamesY(i);
	}

	public Field getField(int y) {
		return genericQuestion.getField(0, y);
	}

	public String getTitle() {
		return genericQuestion.getTitle();
	}

	protected void setField(int y, Field f) {
		genericQuestion.setField(0, y, f);
	}

	public void setTitle(String title) {
		genericQuestion.setTitle(title);
	}
	
	
}
