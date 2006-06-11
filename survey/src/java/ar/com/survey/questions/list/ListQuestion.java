package ar.com.survey.questions.list;

import java.util.List;

import ar.com.survey.model.Question;
import ar.com.survey.questions.fields.Field;

abstract class ListQuestion extends Question {

	protected ListQuestion() {
	}
	
	final public List<String> getItems() {
		return super.getRowTitles();
	}
	final public void setItems(List<String> s) {
		super.setRowTitles(s);
	}

	public Field getField(int xpos) {
		return super.getFieldAt(xpos,0); 
	}	
}
