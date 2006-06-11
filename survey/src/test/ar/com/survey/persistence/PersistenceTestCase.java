package ar.com.survey.persistence;

import junit.framework.TestCase;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import ar.com.survey.TestUtils;
import ar.com.survey.model.PersonDAO;
import ar.com.survey.model.QuestionDAO;
import ar.com.survey.model.QuotaDAO;
import ar.com.survey.model.SectionDAO;
import ar.com.survey.model.SurveyDAO;
import ar.com.survey.persistence.hibernate.HibernateUtil;

public class PersistenceTestCase extends TestCase {
	protected SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	protected SurveyDAO surveyDAO = new SurveyDAO();
	protected QuotaDAO quotaDAO = new QuotaDAO();
	protected QuestionDAO questionDAO = new QuestionDAO();
	protected SectionDAO sectionDAO = new SectionDAO();
	protected PersonDAO personDAO = new PersonDAO();
	
	
	
	/** This is usefull to "decorate" any string used in natural-id properties.
	 * This makes possible to repeat persistence tests without needing to clean the DB
	 * and without loosing foreing key constraints
	 * @param s
	 * @return
	 */
	protected static final String prefix(final String s) {
		return TestUtils.prefix(s);
	}
	
	protected final Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}
	protected final Transaction beginTx() {
		return HibernateUtil.beginTx();
		//return this.sessionFactory.getCurrentSession().beginTransaction();
	}
	protected final void closeTx() {
		HibernateUtil.closeTx();
		//this.sessionFactory.getCurrentSession().getTransaction().commit();
	}
	protected final void closeSession() {
		HibernateUtil.getSessionFactory().getCurrentSession().close();
		//this.sessionFactory.getCurrentSession().getTransaction().commit();
	}
}
