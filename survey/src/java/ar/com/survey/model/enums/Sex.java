package ar.com.survey.model.enums;


public final class Sex extends EnumType{
	public Sex(String code, String desc) {
		super(code, desc);
	}
	//TODO: ideal: esto podemos mejorarlo bastante si es necesario:
	//1) Enum jdk 1.4 pattern para tener descripciones i18n o comportamiento
	//2) Enum jdk 5 + Hibernate usertypes
	//3) Elevar la clase a bean persistente (tablas codificadoras)
	//creo q 3 es lo mejor, es lo más claro, más clásico y lo que mejor evoluciona
	public static final Sex MALE = new Sex("M","Masculino");
	public static final Sex FEMALE = new Sex("F","Femenino");
	private static Sex[] types = { MALE, FEMALE };
	public static Sex valueOf(String code) {
		return (Sex) EnumType.find(types, code);		
	}
	
}
