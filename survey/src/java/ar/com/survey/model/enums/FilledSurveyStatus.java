package ar.com.survey.model.enums;

public final class FilledSurveyStatus extends EnumType {
	public FilledSurveyStatus(String code, String desc) {
		super(code, desc);
	}
	public static final FilledSurveyStatus INCOMPLETO = new FilledSurveyStatus("I","Incompleto");
	public static final FilledSurveyStatus COMPLETADO = new FilledSurveyStatus("C","Completado");
	public static final FilledSurveyStatus QUOTA_EXCEDIDA = new FilledSurveyStatus("Q","Quota Excedida");
	public static final FilledSurveyStatus N_A = new FilledSurveyStatus("N","No aplica");
	private static FilledSurveyStatus[] types = { INCOMPLETO, COMPLETADO, QUOTA_EXCEDIDA, N_A };
	public static FilledSurveyStatus valueOf(String code) {
		return (FilledSurveyStatus) EnumType.find(types, code);		
	}
	
}
