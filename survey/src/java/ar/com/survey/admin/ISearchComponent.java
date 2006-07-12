package ar.com.survey.admin;

import java.util.Collection;

/**
 * 
 * @author Claudio Petronio
 * 
 * This is the interface used to query the persistence layer for specific surveys objects
 *
 */
public interface ISearchComponent {
	
	/**
	 * 
	 * @param id filter to search for
	 * @return Collection of survey objects found for the supplied id
	 *  
	 */
	public Collection getSurveysByID(String id);
	
	/**
	 * 
	 * @param name filter to search for
	 * @return Collection of survey objects found for the supplied name
	 * 
	 */
	public Collection getSurveysByName(String name);
	
	/**
	 * 
	 * @param date filter to search for
	 * @return Collection of survey objects found for the supplied date
	 * 
	 */
	public Collection getSurveysByCreationDate(String date);
	
	/**
	 * 
	 * @param statusCode filter to search for
	 * @return Collection of survey objects found for the supplied status code
	 * 
	 */
	public Collection getSurveysByStatus(String statusCode);
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param date
	 * @param statusCode
	 * @return Collection of survey objects found for the supplied filters
	 * 
	 */
	public Collection getSurveysByFullDescription(String id, String name, String date, String statusCode);
	
}
