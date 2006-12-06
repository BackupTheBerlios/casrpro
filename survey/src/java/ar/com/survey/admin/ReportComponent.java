package ar.com.survey.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import ar.com.survey.persistence.hibernate.HibernateUtil;
import ar.com.survey.util.SimpleFilter;
import ar.com.survey.util.Transformer;
import ar.com.survey.web.struts.form.ReportForm;

public class ReportComponent {

	private static final Logger log = Logger.getLogger(ReportComponent.class);

	public ReportComponent() {

	}

	public boolean printCountFS(ReportForm rf, HttpServletResponse response) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();

		// Get survey id, then query by filters
		Query q = session.createQuery(
				"select s.id from Survey as s where s.name=?").setString(0,
				rf.getSurveyName());
		Long sid = (Long) q.uniqueResult();

		if (sid == null)
			return false;

		ArrayList sqlParams = new ArrayList();

		// create query string
		StringBuffer sql = new StringBuffer(
				"select count(*) from FilledSurvey fs where fs.survey.id = ?");
		sqlParams.add(sid);

		if (rf.getEndDate() != null && !rf.getEndDate().equals("")) {
			sql.append(" and fs.finishDate < ?");
			sqlParams.add(Transformer.getCalendarFromString(rf.getEndDate())
					.getTime());
		}

		if (rf.getStartDate() != null && !rf.getStartDate().equals("")) {
			sql.append(" and fs.initDate > ?");
			sqlParams.add(Transformer.getCalendarFromString(rf.getStartDate())
					.getTime());
		}

		if (rf.getFstatus() != null && !rf.getFstatus().equals("")
				&& !rf.getFstatus().equals("all")) {
			sql.append(" and fs.state = ?");
			sqlParams.add(rf.getFstatus());
		}

		q = session.createQuery(sql.toString());

		// iterate sqlParams and add each filter restriction based on it's type

		for (int i = 0; i < sqlParams.size(); i++) {
			if (sqlParams.get(i) instanceof Date)
				q.setDate(i, (Date) sqlParams.get(i));
			else if (sqlParams.get(i) instanceof String)
				q.setString(i, (String) sqlParams.get(i));
			else
				q.setLong(i, (Long) sqlParams.get(i));
		}

		Integer d = (Integer) q.uniqueResult();
		log.info("Resultado obtenido para este survey: " + d.intValue());

		try {
			printPDFToResponse(d.intValue(), response, rf.getSurveyName(), rf
					.getStartDate(), rf.getEndDate(), rf.getFstatus());
		} catch (IOException ioe) {
			ioe.printStackTrace();
			return false;
		}
		return true;
	}

private void printPDFToResponse(int count, HttpServletResponse response, String surveyName, String startDate, String endDate, String state)
			throws IOException {

		JasperReport jasperReport;
		JasperPrint jasperPrint;
		try {
			HashMap map = new HashMap();
			ArrayList al = new ArrayList();
			SimpleFilter sf = new SimpleFilter();
			sf.setFilterName("Fecha de inicio");
			if(startDate!=null && !startDate.equals(""))
				sf.setFilterValue(startDate);
			else
				sf.setFilterValue("No Aplicado");
			al.add(sf);
			sf = new SimpleFilter();
			sf.setFilterName("Fecha de fin");
			if(endDate!=null && !endDate.equals(""))
				sf.setFilterValue(endDate);
			else
				sf.setFilterValue("No Aplicado");
			al.add(sf);
			sf = new SimpleFilter();
			sf.setFilterName("Por estado");
			if(state!=null && !state.equals(""))
				sf.setFilterValue(state);
			else
				sf.setFilterValue("No Aplicado");
			al.add(sf);
			JRBeanCollectionDataSource jrb = new JRBeanCollectionDataSource(al);
			map.put("ReportTitle", "FilledSurveys Count - " + surveyName);
			map
					.put(
							"Copyright",
							"Copyright © 2006 Great Insights SRL. All rights reserved. Great Insights ® is a registered trademark");
			map.put("Count", Integer.toString(count));
			ClassLoader cl = this.getClass().getClassLoader();
			InputStream configStream = cl.getResourceAsStream("jasperCount.xml");
			jasperReport = JasperCompileManager.compileReport(configStream);
			byte[] bytes = null;
			bytes = JasperRunManager.runReportToPdf(jasperReport, map, jrb);
			response.setContentType("application/pdf");
			response.setContentLength(bytes.length);
			
			ServletOutputStream servletOutputStream = response.getOutputStream();
			servletOutputStream.write(bytes, 0, bytes.length);
			servletOutputStream.flush();
			servletOutputStream.close();
		} catch (JRException e) {
			e.printStackTrace();
		}

	}}
