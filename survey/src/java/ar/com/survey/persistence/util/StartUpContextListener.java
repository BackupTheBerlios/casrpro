package ar.com.survey.persistence.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import ar.com.survey.model.Person;
import ar.com.survey.model.PersonDAO;
import ar.com.survey.model.enums.Sex;
import ar.com.survey.persistence.hibernate.HibernateUtil;
import ar.com.survey.util.Base64;
import ar.com.survey.util.DbPropsImpl;

public class StartUpContextListener implements ServletContextListener {

	private static Logger logger = Logger
			.getLogger(StartUpContextListener.class);

	public void contextDestroyed(ServletContextEvent arg0) {

	}

	public void contextInitialized(ServletContextEvent arg0) {

		// Check if anonymous exists in db, if not insert it into db

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();
		Person anonymous = new Person();
		anonymous.setEmail(new DbPropsImpl().getValue("AnonymousEmail"));
		PersonDAO pDAO = new PersonDAO();
		anonymous = pDAO.findBySurrogateKey(anonymous);
		if (anonymous != null)
			logger.info("Anonymous user found in db: "
					+ anonymous.getFirstName());
		else {
			logger.info("Inserting Anonymous in db");
			anonymous = new Person();
			anonymous.setFirstName("Anonymous");
			anonymous.setLastName("Anonymous");
			anonymous.setEmail(new DbPropsImpl().getValue("AnonymousEmail"));
			anonymous.setSex(Sex.MALE);

			StringBuilder token = new StringBuilder();
			token.append(Math.random());
			token.append(anonymous.getEmail().hashCode());
			token.append(System.currentTimeMillis());
			String result = Base64.encodeObject(token.toString());
			// Added a replacer for equals values causing the outlooks email
			// clients to break link
			anonymous.setToken(result.replace('=', '3'));
			pDAO.createNew(anonymous);
		}
		tx.commit();
	}
}
