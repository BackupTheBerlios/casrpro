package ar.com.survey.model.enums;

public class RestrictionType extends EnumType {
	
	public RestrictionType(String code, String desc) {
		super(code, desc);
	}
	//TODO: ideal: esto podemos mejorarlo bastante si es necesario:
	//1) Enum jdk 1.4 pattern para tener descripciones i18n o comportamiento
	//2) Enum jdk 5 + Hibernate usertypes
	//3) Elevar la clase a bean persistente (tablas codificadoras)
	//creo q 3 es lo mejor, es lo más claro, más clásico y lo que mejor evoluciona
	public static final SurveyState OPEN = new SurveyState("OPEN","Abierto");
	public static final SurveyState RESTRICTED = new SurveyState("RESTRICTED","Restringido");
	private static SurveyState[] types = { OPEN, RESTRICTED };
	public static SurveyState valueOf(String code) {
		return (SurveyState) EnumType.find(types, code);		
	}

}
