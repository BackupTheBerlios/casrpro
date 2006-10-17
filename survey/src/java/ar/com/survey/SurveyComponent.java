package ar.com.survey;

import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import ar.com.survey.model.FilledSurvey;
import ar.com.survey.model.FilledSurveyDAO;
import ar.com.survey.model.Person;
import ar.com.survey.model.Survey;
import ar.com.survey.model.enums.FilledSurveyStatus;
import ar.com.survey.questions.fields.Field;

public class SurveyComponent implements ISurveyComponent{
	
	public FillingSurveyStatus beginSurvey(Survey survey, Person person) {
		/*
		 - comprobar que el Survey y la Persona existen (esto ya lo hice a nivel de componente web)
		 - crear un FilledSurvey nuevo. 
		      Asociarle el survey y la persona.
		      Completar los datos de fechas y estado
		      currentSection en 0 (la primera)
		 - Crear un FilledSurveyStatus, completarlo con los mismos datos de arriba.
		      Ponerle el ID del Filledsurvey creado
		      Retornarlo
		 */
		
		FilledSurvey fs = new FilledSurvey();
		fs.setCurrentSection(0);
		fs.setInitDate(Calendar.getInstance());
		fs.setPerson(person);
		fs.setSurvey(survey);
		fs.setState(FilledSurveyStatus.INCOMPLETO.getCode());
		
		FilledSurveyDAO fsDAO = new FilledSurveyDAO();
		fsDAO.createNew(fs);
		
		FillingSurveyStatus fss = new FillingSurveyStatus();
		fss.setCurrentSection(0);
		fss.setInitDate(Calendar.getInstance());
		fss.setPerson(person);
		fss.setSurvey(survey);
		fss.setState(FilledSurveyStatus.INCOMPLETO.getCode());
		// fss.setFilledsurveyID(fs.getId());
		
		return fss;
	}
	
	public void cancelSurvey(FillingSurveyStatus fss) {
		/* Localizar el FilledSurvey.
		 * Borrarlo con cascade (o sea, borrar también sus Field hijos). Esto se hace en el mapping de Hiber.
		 */ 			
	}
	
	public void saveSectionAnswers(FillingSurveyStatus fss, Collection<Field> fields) {
		/*
		 * Sólo hay que grabar los nuevos field hijos.
		 */
		
		
		
	}
	
	public void getNextSection(FillingSurveyStatus fss) {
		/*
		 * Este es mas complejo.
		 * Aquí hay que considerar la seccion actual (sale de fss.getCurrentSection) y calcular cual es la siguiente seccion a ejecutar
		 * O sea, ejecutar los scripts.
		 * Si el cuestionario debe terminar (pregunta terminal, o quota excedida), hay que modificarlo marcando su estado y modificar el objeto fss
		 * avisando de tal situación.
		 * 
		 * Para comenzar, consideraremos una implementación simple que avanza por las rpeguntas 0,1,2...etc. y marca el questionario como finalizado
		 * cuando llega a la última.
		 * 
		 * 
		 */
	}

	
	public void suspendSurvey(FillingSurveyStatus fss) {
		throw new UnsupportedOperationException();		
	}
	
	public List<FillingSurveyStatus> getsuspendedSurveys(Person p) {
		throw new UnsupportedOperationException();	
	}
	
	public void continueSuspendedSurvey(FillingSurveyStatus fss) {
		throw new UnsupportedOperationException();
	}

}
