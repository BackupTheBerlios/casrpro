package ar.com.survey.model.enums;


public final class SurveyState extends EnumType{
	public SurveyState(String code, String desc) {
		super(code, desc);
	}
	//TODO: ideal: esto podemos mejorarlo bastante si es necesario:
	//1) Enum jdk 1.4 pattern para tener descripciones i18n o comportamiento
	//2) Enum jdk 5 + Hibernate usertypes
	//3) Elevar la clase a bean persistente (tablas codificadoras)
	//creo q 3 es lo mejor, es lo m�s claro, m�s cl�sico y lo que mejor evoluciona
	public static final SurveyState OPEN = new SurveyState("OPEN","Abierto");
	public static final SurveyState CLOSED = new SurveyState("CLOSED","Cerrado");
	private static SurveyState[] types = { OPEN, CLOSED };
	public static SurveyState valueOf(String code) {
		return (SurveyState) EnumType.find(types, code);		
	}
	
}
