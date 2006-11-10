package ar.com.survey.admin.db;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import ar.com.survey.model.Survey;
import ar.com.survey.model.SurveyDAO;
import ar.com.survey.util.Transformer;

public class CustomSurveyDAO extends SurveyDAO {

	private static final Logger log = org.apache.log4j.Logger
			.getLogger(ar.com.survey.admin.db.CustomSurveyDAO.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	public List findByCreationDate(Survey transientInstance) {

		log.debug("findByCreationDate starts");
		String dateString = Transformer
				.getReversedStringFromCalendar(transientInstance
						.getCreationDate());
		try {

			List instances = sessionFactory.getCurrentSession().createQuery(
					"from Survey as sur where sur.creationDate like '"
							+ dateString + "%'").list();
			if (instances == null) {
				log.debug("get successful, no instances found");
			} else {
				log.debug("get successful, instances found");
			}
			return instances;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public List findByNameAndCreationDate(Survey transientInstance) {
		log.debug("findByNameAndCreationDate starts");
		String dateString = Transformer
				.getReversedStringFromCalendar(transientInstance
						.getCreationDate());
		try {
			List instances = sessionFactory.getCurrentSession().createQuery(
					"from Survey as sur where sur.creationDate like '"
							+ dateString + "%' and name='"
							+ transientInstance.getName() + "'").list();
			if (instances == null) {
				log.debug("get successful, no instances found");
			} else {
				log.debug("get successful, instances found");
			}
			return instances;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public List findByStatusAndCreationDate(Survey transientInstance) {
		log.debug("findByStatusAndCreationDate starts");
		String dateString = Transformer
				.getReversedStringFromCalendar(transientInstance
						.getCreationDate());
		try {
			List instances = sessionFactory.getCurrentSession().createQuery(
					"from Survey as sur where sur.creationDate like '"
							+ dateString + "%' and status='"
							+ transientInstance.getStatus() + "'").list();
			if (instances == null) {
				log.debug("get successful, no instances found");
			} else {
				log.debug("get successful, instances found");
			}
			return instances;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public List findByNameAndCreationDateAndStatus(Survey transientInstance) {
		log.debug("findByNameAndCreationDate starts");
		String dateString = Transformer
				.getReversedStringFromCalendar(transientInstance
						.getCreationDate());
		try {
			List instances = sessionFactory.getCurrentSession().createQuery(
					"from Survey as sur where sur.creationDate like '"
							+ dateString + "%' and name='"
							+ transientInstance.getName() + "' and status='"
							+ transientInstance.getStatus() + "'").list();
			if (instances == null) {
				log.debug("get successful, no instances found");
			} else {
				log.debug("get successful, instances found");
			}
			return instances;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public List findByNameAndStatus(Survey transientInstance) {
		log.debug("findByNameAndStatus starts");
		try {
			List instances = sessionFactory.getCurrentSession().createQuery(
					"from Survey as sur where name='"
							+ transientInstance.getName() + "' and status='"
							+ transientInstance.getStatus() + "'").list();
			if (instances == null) {
				log.debug("get successful, no instances found");
			} else {
				log.debug("get successful, instances found");
			}
			return instances;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public List findByStatus(Survey transientInstance) {
		log.debug("findByStatus starts");
		try {
			List instances = sessionFactory.getCurrentSession().createQuery(
					"from Survey as sur where status='"
							+ transientInstance.getStatus() + "'").list();
			if (instances == null) {
				log.debug("get successful, no instances found");
			} else {
				log.debug("get successful, instances found");
			}
			return instances;
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public void deleteBySurrogate(Survey transientInstance) {
		log.debug("delete by surrogate starts");
		try {
			Survey survey = (Survey) sessionFactory.getCurrentSession()
					.createQuery(
							"from Survey as sur where name='"
									+ transientInstance.getName() + "'")
					.uniqueResult();
			if (survey == null) {
				log.debug("get successful, no instances found");
			} else {
				log.debug("get successful, instances found");
				sessionFactory.getCurrentSession().delete(survey);
				sessionFactory.getCurrentSession().flush();
			}
		} catch (RuntimeException re) {
			log.error("delete persisted failed", re);
			throw re;
		}
	}
	
	 public void createOrUpdate(Survey transientInstance) {
	        log.debug("persisting Survey instance");
	        try {
	            sessionFactory.getCurrentSession().saveOrUpdate(transientInstance);
	            sessionFactory.getCurrentSession().flush();
	            log.debug("persist successful");
	        }
	        catch (ConstraintViolationException cve) {
	            log.error("persist failed: " + cve.getMessage());
	            throw cve;
	        }
	        catch (RuntimeException re) {
	            log.error("persist failed: " + re.getMessage());
	            throw re;
	        }
	    }
}
