package ar.com.survey.model;

import java.io.Serializable;

/**
 * 
 */
public abstract class SectionAbstract implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public abstract Survey getSurvey();

	public int getPosition() {
		return this.getSurvey().getSections().indexOf(this);
	}
}
