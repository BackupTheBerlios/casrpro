package ar.com.survey.questions.fields;

public class CheckBoxField extends Field {

	boolean selected;
	public CheckBoxField(boolean s) {
		this.selected = s;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	@Override
	public String getValue() {
		return Boolean.valueOf(this.selected).toString();
	}

}
