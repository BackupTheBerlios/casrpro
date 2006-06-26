package ar.com.survey.model.enums;


public final class Role extends EnumType{
	public Role(String code, String desc) {
		super(code, desc);
	}
	//TODO: ideal: esto podemos mejorarlo bastante si es necesario:
	//1) Enum jdk 1.4 pattern para tener descripciones i18n o comportamiento
	//2) Enum jdk 5 + Hibernate usertypes
	//3) Elevar la clase a bean persistente (tablas codificadoras)
	//creo q 3 es lo mejor, es lo más claro, más clásico y lo que mejor evoluciona
	public static final Role ADMIN = new Role("A","Administrar");
	public static final Role FILLER = new Role("F","Completar cuestionarios");
	private static Role[] types = { ADMIN, FILLER };
	public static Role valueOf(String code) {
		return (Role) EnumType.find(types, code);		
	}
	
}
