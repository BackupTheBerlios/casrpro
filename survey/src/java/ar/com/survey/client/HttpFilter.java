package ar.com.survey.client;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class HttpFilter implements Filter {
	
	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletResponse resp = (HttpServletResponse) response;
		resp.setHeader("Cache-Control", "max-age=1, must-revalidate");
		Calendar cal = Calendar.getInstance();
		StringBuffer date = new StringBuffer();
		switch(cal.get(Calendar.DAY_OF_WEEK)){
		case 1: 
			date.append("Sun");
			break;
		case 2:
			date.append("Mon");
			break;
		case 3:
			date.append("Tue");
			break;
		case 4:
			date.append("Wed");
			break;
		case 5:
			date.append("Thu");
			break;
		case 6:
			date.append("Fri");
			break;
		default:
			date.append("Sat");
		}
		date.append(", ");
		date.append(cal.get(Calendar.DAY_OF_MONTH));
		date.append(" ");
		switch(cal.get(Calendar.MONTH)){
		case 0:
			date.append("Jan");
			break;
		case 1:
			date.append("Feb");
			break;
		case 3:
			date.append("Mar");
			break;
		case 4:
			date.append("Apr");
			break;
		case 5:
			date.append("May");
			break;
		case 6:
			date.append("Jun");
			break;
		case 7:
			date.append("Jul");
			break;
		case 8:
			date.append("Aug");
			break;
		case 9:
			date.append("Sep");
			break;
		case 10:
			date.append("Oct");
			break;
		case 11:
			date.append("Nov");
			break;
		default:
			date.append("Dic");
			break;
		}
		
		date.append(" ");
		date.append(cal.get(Calendar.YEAR));
		date.append(" 00:00:00 GMT");
		resp.setHeader("Expires", date.toString());
		
		chain.doFilter(request, resp);
		
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

}
