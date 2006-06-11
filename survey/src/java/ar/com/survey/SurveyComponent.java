package ar.com.survey;

import ar.com.survey.model.Section;
import ar.com.survey.model.Survey;

public class SurveyComponent {
	public SurveyComponent() {
		super();
	}
	
	/** Given a partially ansered questionnaire, calculates the next section that should be posted
	 * in the view, according Quota and Flow scripts.
	 * @param s
	 * @param currentSection
	 * @return
	 */
	public Section getNextSection(Survey s, int currentSection) {
		return null;
	}
}
