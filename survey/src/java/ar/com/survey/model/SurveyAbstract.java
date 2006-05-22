package ar.com.survey.model;

import java.io.Serializable;
import java.util.Set;

/**
 * 
 */
public abstract class SurveyAbstract implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/** find a quota belonging to this survey and with specified name
	 * @param name required not null
	 * @return null if not found
	 */
	public Quota getQuota(String name) {
		if (name == null) throw new IllegalArgumentException("name can't be null");
		for (Quota q : this.getQuotas()) {
			if (q.getName().equals(name)) return q; 
		}
		return null;		
	}

	protected abstract Set<Quota> getQuotas();
}


