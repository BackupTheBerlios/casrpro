package ar.com.survey.model;

import java.io.Serializable;
import java.util.List;
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
	public Section getSection(int i) {
		return this.getSections().get(i); 
	}
	public void replaceSection(int i, Section s) {
		this.getSections().remove(i);
		this.getSections().add(i,s);
	}
	public void addSection(int i, Section s) {
		this.getSections().add(i,s);
	}

	public void deleteSection(int i) {
		this.getSections().remove(i);
	}
	protected abstract Set<Quota> getQuotas();
	protected abstract List<Section> getSections();
}


