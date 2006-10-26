package ar.com.survey.util;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Transformer {

	public static Calendar getCalendarFromString(String date){
		Calendar calendar = Calendar.getInstance();
		String[] dateParts = date.split("/");
		int day = Integer.parseInt(dateParts[0]);
		int month = Integer.parseInt(dateParts[1]) - 1;
		int year = Integer.parseInt(dateParts[2]);
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.DAY_OF_MONTH, day);
		return calendar;
	}
	
	public static String getStringFromCalendar(Calendar date){
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		StringBuffer retDay = new StringBuffer();
		if(day<10)
			retDay.append("0" + day);
		else
			retDay.append(day);
		retDay.append("/");
		if(month<10)
			retDay.append("0" + month);
		else
			retDay.append(month);
		retDay.append("/");
		retDay.append(year);
		return retDay.toString();
	}
	
	public static String getReversedStringFromCalendar(Calendar date){
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		StringBuffer retDay = new StringBuffer();
		retDay.append(year);
		retDay.append("-");
		if(month<10)
			retDay.append("0" + month);
		else
			retDay.append(month);
		retDay.append("-");
		if(day<10)
			retDay.append("0" + day);
		else
			retDay.append(day);
		return retDay.toString();
	}
	
	/**
	 * 
	 * Had to create this method because of the encoding used by ajax
	 * 
	 * @param originalTxt containning especial html chars linke &aacute;
	 * @return String representing text with acents and ñ
	 */
	public static String htmlEspecialCharsConverter(String originalTxt){
		
		Pattern p = Pattern.compile("#aacute;");
		Matcher m = p.matcher(originalTxt);
		String retValue = m.replaceAll("á");
		
		p = Pattern.compile("#eacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("é");
		
		p = Pattern.compile("#iacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("í");		
		
		p = Pattern.compile("#oacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("ó");
		
		p = Pattern.compile("#uacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("ú");
		
		p = Pattern.compile("#Aacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("Á");
		
		p = Pattern.compile("#Eacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("É");
		
		p = Pattern.compile("#Iacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("Í");
		
		p = Pattern.compile("#Oacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("Ó");
		

		p = Pattern.compile("#Uacute;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("Ú");
		
		p = Pattern.compile("#uuml;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("ü");
		

		p = Pattern.compile("#Uuml;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("Ü");
		
		p = Pattern.compile("#ntilde;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("ñ");
		
		p = Pattern.compile("#Ntilde;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("Ñ");
		
		p = Pattern.compile("##191;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("¿");
		
		p = Pattern.compile("##161;");
		m = p.matcher(retValue);
		retValue = m.replaceAll("¡");
		
		
		return retValue;
	}
}
