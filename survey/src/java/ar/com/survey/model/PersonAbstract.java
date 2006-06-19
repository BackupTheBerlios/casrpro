package ar.com.survey.model;

import java.io.Serializable;

import ar.com.survey.model.enums.Sex;

/**
 * 
 */
public abstract class PersonAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	public abstract void setSex(String s);
	public void setSex(Sex s) {
		setSex(s.getCode());
	}

}


