package ar.com.survey.model.enums;

public abstract class EnumType {

	protected String code;
	protected String description;


	protected EnumType(String code, String desc) {
		this.code = code;
		this.description = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	protected static EnumType find(EnumType[] types, String code2) {
		for (EnumType s : types) {
			if (s.code.equals(code2)) return s;
		}
		return null;
	}

}
