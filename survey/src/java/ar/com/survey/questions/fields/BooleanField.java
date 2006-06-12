package ar.com.survey.questions.fields;

public class BooleanField extends Field {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	boolean selected;
	public BooleanField(boolean s) {
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
