package ar.com.survey.model;

import java.io.Serializable;

import org.hibernate.CallbackException;
import org.hibernate.Session;
import org.hibernate.classic.Lifecycle;

import ar.com.survey.questions.fields.Field;

public abstract class GenericQuestionDataAbstract implements Lifecycle{ 

	public boolean onDelete(Session arg0) throws CallbackException {
		//return true to veto the operation
		return false;
	}

	public void onLoad(Session arg0, Serializable arg1) {
		this.axisNamesX = decodeAxis(this.getEncodedAxisX());
		this.axisNamesY = decodeAxis(this.getEncodedAxisY());
		this.fields  = decodeFields(this.getEncodedField());
	}




	public boolean onSave(Session arg0) throws CallbackException {
		this.setEncodedAxisX(encodeAxis(this.axisNamesX));
		this.setEncodedAxisY(encodeAxis(this.axisNamesY));
		this.setEncodedField(encodeFields(this.fields));
		return false;
	}

	public boolean onUpdate(Session arg0) throws CallbackException {
		this.setEncodedAxisX(encodeAxis(this.axisNamesX));
		this.setEncodedAxisY(encodeAxis(this.axisNamesY));
		this.setEncodedField(encodeFields(this.fields));
		return false;
	}

	public GenericQuestionDataAbstract() {
		super();
	}

	private static final String separator = "|";
	
	abstract int getVersion();

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String[] axisNamesX;

	private String[] axisNamesY;

	private Field[][] fields;

	private static String encodeAxis(String[] sa) {
		StringBuilder sb = new StringBuilder();
		for (String s : sa) {
			if (s.contains(separator))
				throw new IllegalArgumentException("String " + s
						+ " contains the caracter " + separator
						+ " which is not allowed.");
			sb.append(s);
			sb.append(separator);
		}
		return sb.toString();
	}
	private static String encodeFields(Field[][] ff) {
		// TODO Auto-generated method stub
		return null;				
	}
	private static Field[][] decodeFields(String s) {
		// TODO Auto-generated method stub
		return null;	
	}

	private static String[] decodeAxis(String encodedAxisX) {
		// TODO Auto-generated method stub
		return null;
	}

	public GenericQuestionDataAbstract(int x, int y) {
		this.fields = new Field[x][y];
	}

	public String[] getAxisNamesX() {
		return axisNamesX;
	}

	public void setAxisNamesX(String[] axisNamesX) {
		this.axisNamesX = axisNamesX;
	}

	public String[] getAxisNamesY() {
		return axisNamesY;
	}

	public void setAxisNamesY(String[] axisNamesY) {
		this.axisNamesY = axisNamesY;
	}

	public String getAxisNamesX(int i) {
		return this.axisNamesX[i];
	}

	public String getAxisNamesY(int i) {
		return this.axisNamesY[i];
	}

	public Field getField(int x, int y) {
		return this.fields[x][y];
	}

	public void setField(int x, int y, Field f) {
		this.fields[x][y] = f;
	}
	// ------------------------------------------ these method will be implemented by the generated POJO
	
	abstract protected String getEncodedAxisX();
	abstract protected void setEncodedAxisX(String encodedAxisX);
	abstract protected String getEncodedAxisY();
	abstract protected void setEncodedAxisY(String encodedAxisY);
	abstract protected String getEncodedField();
	abstract protected void setEncodedField(String encodedField);
	
}
