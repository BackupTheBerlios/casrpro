package ar.com.survey.web.struts.form;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;

public class FillForm extends BaseForm {

	private String nextPos;
	
	// generic texts (includes textboxes and textareas)
	private String[] txtAnswer = null;

	// fixed 5 checboxes
	private String[] check1 = null;

	private String[] check2 = null;

	private String[] check3 = null;

	private String[] check4 = null;

	private String[] check5 = null;

	// fixed 5 numbers lists

	private String[] number1 = null;

	private String[] number2 = null;

	private String[] number3 = null;

	private String[] number4 = null;

	private String[] number5 = null;

	// fixed 5 unique answers

	private String unique1 = null;

	private String unique2 = null;

	private String unique3 = null;

	private String unique4 = null;

	private String unique5 = null;

	// fixed 5 maps objects for 5 matrix questions

	private Map matrix1 = new HashMap();

	private Map matrix2 = new HashMap();

	private Map matrix3 = new HashMap();

	private Map matrix4 = new HashMap();

	private Map matrix5 = new HashMap();

	public String[] getTxtAnswer() {
		return txtAnswer;
	}

	public void setTxtAnswer(String[] txtAnswer) {
		this.txtAnswer = txtAnswer;
	}

	public String[] getCheck1() {
		return check1;
	}

	public void setCheck1(String[] check1) {
		this.check1 = check1;
	}

	public String[] getCheck2() {
		return check2;
	}

	public void setCheck2(String[] check2) {
		this.check2 = check2;
	}

	public String[] getCheck3() {
		return check3;
	}

	public void setCheck3(String[] check3) {
		this.check3 = check3;
	}

	public String[] getCheck4() {
		return check4;
	}

	public void setCheck4(String[] check4) {
		this.check4 = check4;
	}

	public String[] getCheck5() {
		return check5;
	}

	public void setCheck5(String[] check5) {
		this.check5 = check5;
	}

	public String[] getNumber1() {
		return number1;
	}

	public void setNumber1(String[] number1) {
		this.number1 = number1;
	}

	public String[] getNumber2() {
		return number2;
	}

	public void setNumber2(String[] number2) {
		this.number2 = number2;
	}

	public String[] getNumber3() {
		return number3;
	}

	public void setNumber3(String[] number3) {
		this.number3 = number3;
	}

	public String[] getNumber4() {
		return number4;
	}

	public void setNumber4(String[] number4) {
		this.number4 = number4;
	}

	public String[] getNumber5() {
		return number5;
	}

	public void setNumber5(String[] number5) {
		this.number5 = number5;
	}

	public String getUnique1() {
		return unique1;
	}

	public void setUnique1(String unique1) {
		this.unique1 = unique1;
	}

	public String getUnique2() {
		return unique2;
	}

	public void setUnique2(String unique2) {
		this.unique2 = unique2;
	}

	public String getUnique3() {
		return unique3;
	}

	public void setUnique3(String unique3) {
		this.unique3 = unique3;
	}

	public String getUnique4() {
		return unique4;
	}

	public void setUnique4(String unique4) {
		this.unique4 = unique4;
	}

	public String getUnique5() {
		return unique5;
	}

	public void setUnique5(String unique5) {
		this.unique5 = unique5;
	}

	public void setMatrix1(String key, Object value) {
		matrix1.put(key, value);
	}

	public Object getMatrix1(String key) {
		return matrix1.get(key);
	}
	
	public void setMatrix2(String key, Object value) {
		matrix2.put(key, value);
	}

	public Object getMatrix2(String key) {
		return matrix2.get(key);
	}
	
	public void setMatrix3(String key, Object value) {
		matrix3.put(key, value);
	}

	public Object getMatrix3(String key) {
		return matrix3.get(key);
	}
	
	public void setMatrix4(String key, Object value) {
		matrix4.put(key, value);
	}

	public Object getMatrix4(String key) {
		return matrix4.get(key);
	}
	
	public void setMatrix5(String key, Object value) {
		matrix5.put(key, value);
	}

	public Object getMatrix5(String key) {
		return matrix5.get(key);
	}

	public String getNextPos() {
		return nextPos;
	}

	public void setNextPos(String nextPos) {
		this.nextPos = nextPos;
	}

	public Map getMatrix1() {
		return matrix1;
	}

	public Map getMatrix2() {
		return matrix2;
	}

	public Map getMatrix3() {
		return matrix3;
	}

	public Map getMatrix4() {
		return matrix4;
	}

	public Map getMatrix5() {
		return matrix5;
	}
	
	

	public void setMatrix1(Map matrix1) {
		this.matrix1 = matrix1;
	}

	public void setMatrix2(Map matrix2) {
		this.matrix2 = matrix2;
	}

	public void setMatrix3(Map matrix3) {
		this.matrix3 = matrix3;
	}

	public void setMatrix4(Map matrix4) {
		this.matrix4 = matrix4;
	}

	public void setMatrix5(Map matrix5) {
		this.matrix5 = matrix5;
	}

	public void reset(ActionMapping arg0, HttpServletRequest arg1) {
		super.reset(arg0, arg1);
		txtAnswer = null;
		check1 = null;
		check2 = null;
		check3 = null;
		check4 = null;
		check5 = null;
		number1 = null;
		number2 = null;
		number3 = null;
		number4 = null;
		number5 = null;
		unique1 = null;
		unique2 = null;
		unique3 = null;
		unique4 = null;
		unique5 = null;
	}
	
}
