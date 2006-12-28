package ar.com.survey.questions.fields;

import ar.com.survey.model.Question;

public class FieldFactory {

	private FieldFactory() {
	}
	
	public static BooleanField booleanField(boolean value, Question q) {
		return booleanField(value,q,0,0);
	}
	public static BooleanField booleanField(boolean value, Question q, int x) {
		return booleanField(value,q,x,0);
	}
	public static BooleanField booleanField(boolean value, Question q, int x, int y) {
		BooleanField field = new BooleanField(value);
		field.setQuestion(q);
		field.setXpos(x);
		field.setYpos(y);
		return field;
	}
	
	public static CheckBoxField checkBoxField(boolean value, Question q) {
		return checkBoxField(value,q,0,0);
	}
	public static CheckBoxField checkBoxField(boolean value, Question q, int x) {
		return checkBoxField(value,q,x,0);
	}
	public static CheckBoxField checkBoxField(boolean value, Question q, int x, int y) {
		CheckBoxField field = new CheckBoxField(value);
		field.setQuestion(q);
		field.setXpos(x);
		field.setYpos(y);
		return field;
	}
	
	
	public static NumberField numberField(int value, Question q) {
		return numberField(value,q,0,0);
	}
	public static NumberField numberField(int value, Question q, int x) {
		return numberField(value,q,x,0);
	}
	public static NumberField numberField(int value, Question q, int x, int y) {
		NumberField field = new NumberField(value);
		field.setQuestion(q);
		field.setXpos(x);
		field.setYpos(y);
		return field;
	}
	
	public static StringField stringField(String value, Question q) {
		return stringField(value,q,0,0);
	}
	public static StringField stringField(String value, Question q, int x) {
		return stringField(value,q,x,0);
	}
	public static StringField stringField(String value, Question q, int x, int y) {
		StringField field = new StringField(value);
		field.setQuestion(q);
		field.setXpos(x);
		field.setYpos(y);
		return field;
	}

	public static TextAreaField textAreaField(String value, Question q) {
		return textAreaField(value,q,0,0);
	}
	public static TextAreaField textAreaField(String value, Question q, int x) {
		return textAreaField(value,q,x,0);
	}
	public static TextAreaField textAreaField(String value, Question q, int x, int y) {
		TextAreaField field = new TextAreaField(value);
		field.setQuestion(q);
		field.setXpos(x);
		field.setYpos(y);
		return field;
	}


}
