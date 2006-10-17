package ar.com.survey.admin.db;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import ar.com.survey.model.FilledSurvey;
import ar.com.survey.model.FilledSurveyDAO;
import ar.com.survey.model.Person;
import ar.com.survey.model.Survey;

public class CustomFilledSurveyDAO extends FilledSurveyDAO {

	private static final Logger log = org.apache.log4j.Logger
			.getLogger(CustomFilledSurveyDAO.class);

	private final SessionFactory sessionFactory = getSessionFactory();

	public void createNew(FilledSurvey transientInstance) {
		log.debug("persisting new FilledSurvey instance");
		try {
			sessionFactory.getCurrentSession().save(transientInstance);
			log.debug("created new FilledSurvey successful: "
					+ transientInstance);
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

	public FilledSurvey getInstance(Survey s, Person p) {

		log.debug("getInstance starts");

		try {
			List instances = sessionFactory.getCurrentSession().createQuery(
					"from FilledSurvey as fs where fs.survey.name='"
							+ s.getName() + "' and fs.person.email='"
							+ p.getEmail() + "'").list();
			if (instances == null) {
				log.debug("get successful, no instances found");
				return null;
			} else {
				if (instances.size() == 0) {
					log.debug("get successful, no instances found");
					return null;
				} else {
					log.debug("get successful, instances found");
					return (FilledSurvey) instances.get(0);
				}
			}
		} catch (RuntimeException re) {
			log.error("persist failed", re);
			throw re;
		}
	}

}
