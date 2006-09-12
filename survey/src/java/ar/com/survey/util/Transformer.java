package ar.com.survey.util;

import java.util.Calendar;

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
}
