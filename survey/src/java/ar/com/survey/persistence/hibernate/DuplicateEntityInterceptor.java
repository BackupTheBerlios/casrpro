package ar.com.survey.persistence.hibernate;

import java.io.Serializable;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

public class DuplicateEntityInterceptor extends EmptyInterceptor {

	private static final long serialVersionUID = 1L;


    public boolean onSave(Object entity,
                          Serializable id,
                          Object[] state,
                          String[] propertyNames,
                          Type[] types) {

//        if ( entity instanceof SurveyAbstract ) {
//            SurveyAbstract s = (SurveyAbstract) entity;
//            SurveyDAO sd = new SurveyDAO();
//            Object instance = sd.findBySurrogateKey(s);
//            if (instance != null) {
//            	DuplicateEntityException dee = new DuplicateEntityException();
//            	dee.setExistingEntity(instance);
//            	dee.setTransientEntity(entity);
//            	throw new CallbackException(dee);
//            }
//        }
        return false;
    }
}
