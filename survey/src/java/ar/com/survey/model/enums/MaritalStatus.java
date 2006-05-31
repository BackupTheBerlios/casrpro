package ar.com.survey.model.enums;

public final class MaritalStatus extends EnumType {
	public MaritalStatus(String code, String desc) {
		super(code, desc);
	}
	//TODO: ideal: esto podemos mejorarlo bastante si es necesario:
	//1) Enum jdk 1.4 pattern para tener descripciones i18n o comportamiento
	//2) Enum jdk 5 + Hibernate usertypes
	//3) Elevar la clase a bean persistente (tablas codificadoras)
	//creo q 3 es lo mejor, es lo más claro, más clásico y lo que mejor evoluciona
	public static final MaritalStatus SINGLE = new MaritalStatus("S","Soltero(a)");
	public static final MaritalStatus MARRIED = new MaritalStatus("M","Casado(a)");
	public static final MaritalStatus DIVORCED = new MaritalStatus("D","Divorciado(a)");
	public static final MaritalStatus WIDOWED = new MaritalStatus("V","Viudo(a)");
	private static MaritalStatus[] types = { SINGLE, MARRIED, DIVORCED, WIDOWED };
	public static MaritalStatus valueOf(String code) {
		return (MaritalStatus) EnumType.find(types, code);		
	}
	
}
