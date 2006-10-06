package ar.com.survey;

import java.util.Collection;
import java.util.List;

import ar.com.survey.model.Person;
import ar.com.survey.model.Survey;
import ar.com.survey.questions.fields.Field;

public interface ISurveyComponent {
	/**Se utiliza cuando el cliente selecciona un survey para comenzar a contestarlo. 
	 * Con esto comienza la interacción.
	 * Luego de que la capa Struts obtiene este objeto, hay que procesar la primer sección del Survey
	 * y mostrarlo en pantalla, para que el cliente lo conteste.
	 * El objeto FillingSurveyStatus mantiene el estado y debe guardarse en la sesión..
	 * El usuario NO puede completar distintos cuestionarios en simultáneo (ba, si lo tiene que hacer, se puede...)
	 * Para evitar esto desde frontend, cada vez que el usuario quiere comenzar a contestar un cuestionario, se
	 * puede comprobar la existencia de este objeto en la sesión, si es así, dar la opción
	 * de cancelar el survey (llamar a cancelSurvey) o de continuarlo (mostrar en pantalla las preguntas
	 * correspondientes a survey.getSection(FillingSurveyStatus.getCurrentSection());
	 * 
	 * @param s requerido no nulo, debe ser un survey existente en base
	 * @param person requerido, al menos debe ser la persona "anonima"
	 * @return un FillingSurveyStatus
	 */
	FillingSurveyStatus beginSurvey(Survey s, Person person);
	
	
	/** El usuario quiere abandonar el cuestionario, y perder lo hecho hasta el momento.
	 * @param fss
	 */
	void cancelSurvey(FillingSurveyStatus fss);	
	
	
	/** El usuario acaba de contestar una sección, hay que guardar sus respuestas.
	 * El frontend crea un objeto Field del tipo correspondiente (String, Boolean, etc.) por cada respuesta recibida en el request.
	 * El momento de crearlo, le asocia la Question que corresponde.
	 * Por ejemplo, supongamos que 
	 * a) hay un Question Q=3 que corresponde a la sección Survey.getSection(fss.getCurrentSection())
	 * b) La Question es una Lista de 5 checkboxs, los cuales hay 3 verdaderos (los tres primeros).
	 * Esto implica que habrá que crear:
	 * Question q = (question actual)
	 * Collection<Field> fields = new ArrayList<BooleanField>[5];
	 * BooleanField boolean1 = new BooleanField(); boolean1.setValue(true); boolean1.setX(0); boolean1.setQuestion(q); fields.add(boolean1);
	 * BooleanField boolean2 = new BooleanField(); boolean1.setValue(true); boolean1.setX(1); boolean1.setQuestion(q); fields.add(boolean2);
	 * BooleanField boolean3 = new BooleanField(); boolean1.setValue(true); boolean1.setX(2); boolean1.setQuestion(q); fields.add(boolean3);
	 * BooleanField boolean4 = new BooleanField(); boolean1.setValue(false); boolean1.setX(3); boolean1.setQuestion(q); fields.add(boolean4);
	 * BooleanField boolean5 = new BooleanField(); boolean1.setValue(false); boolean1.setX(4); boolean1.setQuestion(q); fields.add(boolean5);
	 * 
	 * .. y repetir por cada Question en la sección.
	 * 
	 * Nota: 
	 * - si fuera un question con una sola respuesta, entonces x=0, y=0 (valores default)
	 * - si fuera un question tipo List (N respuestas, sean String, Option, etc.), utilizar x=(posición en la lista)
	 * - si fuera un question tipo Matrix (NxM respuestas, sean String, Option, etc.), utilizar x=(posición X), y=(posición y).
	 * 
	 * O sea, si estamos en presencia de un section con: 
	 *    - 3 respuestas simples,
	 *    - 1 lista de 5 options
	 *    - 1 lista de 2 checkbox
	 *    - 1 matriz de 4x5
	 *    - 1 matriz de 3x7
	 *    
	 *    Tendríamos en total: 3 + 5 + 2 + 20 + 21 = 51 Fields por crear y poner en el list.
	 *    Notar que se guardan tanto los negativos como los positivos. O sea, si estamo en presencia de una regunta de 5 options ¿por qué
	 *    no guardar simplemente la verdadera, si sabemos que las otras son falsas? Respuesta: cuando haya que calcular estadísticas o extraer
	 *    datos con sentencias SQL, se va todo al demonio si no está cargado también lo negativo (las sentencias se pueden volver muy complejas).   
	 *      
	 * Una vez que se entienda esto, se pueden utilzar los Factory Methods en FieldFactory para que el código quede más simple. Quedarí así:
	 * 
	 * 
	 * BooleanField boolean1 = FieldFactory.booleanField(true,q,0);
	 * BooleanField boolean2 = FieldFactory.booleanField(true,q,1);
	 * BooleanField boolean3 = FieldFactory.booleanField(true,q,2);
	 * BooleanField boolean4 = FieldFactory.booleanField(false,q,3);
	 * BooleanField boolean5 = FieldFactory.booleanField(false,q,4);
	 * 
	 * 
	 * 
	 * 
	 *  
	 * @param fss
	 * @param fields
	 */
	void saveSectionAnswers(FillingSurveyStatus fss, Collection<Field> fields);
	
	/** Solicita al componente que, en base a las características del Survey y del objeto FillingSurveyStatus, calcule
	 * cuál es la siguiente sección a mostrar. Es decir, calcular el flujo y además verificar Quotas.
	 * Si hay que seguir respondiendo alguna sección, retorna en currentSection la sección a continuar. En otro caso, retorna -1 en este
	 * atributo y el estado correspondiente (utilizar el enum, crear nuevos estados si es necesario).
	 * Si el cuestionario no debe seguir respondiéndose, se grabará su nuevo estado.  
	 * @param fss
	 */
	void getNextSection(FillingSurveyStatus fss);

	
	/** TBD
	 * @param fss
	 */
	void suspendSurvey(FillingSurveyStatus fss);
	/** TBD
	 * @param fss
	 */
	List<FillingSurveyStatus> getsuspendedSurveys(Person p);
	/** TBD
	 * @param fss
	 */
	void continueSuspendedSurvey(FillingSurveyStatus fss);

}
