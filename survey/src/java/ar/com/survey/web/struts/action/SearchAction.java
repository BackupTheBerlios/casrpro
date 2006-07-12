package ar.com.survey.web.struts.action;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import ar.com.survey.admin.ISearchComponent;
import ar.com.survey.web.struts.form.SearchForm;

public class SearchAction extends DispatchAction {

	private static Logger logger = Logger.getLogger(SearchAction.class);

	private ISearchComponent searchFacade;

	protected ActionForward unspecified(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse reponse)
			throws Exception {

		return mapping.findForward("searchForm");
	}

	public ActionForward search(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		// verify which filter has been posted
		int switchFlag = 0;
		SearchForm sform = (SearchForm) form;
		if (sform.getId() != null && !sform.getId().equals(""))
			switchFlag++;

		if (sform.getName() != null && !sform.getName().equals(""))
			switchFlag += 2;

		if (sform.getCreationDate() != null
				&& !sform.getCreationDate().equals(""))
			switchFlag += 4;

		if (sform.getStatus() != null && !sform.getStatus().equals(""))
			switchFlag += 8;

		Collection surveys = null;
		
		switch (switchFlag) {

		case 1:
			surveys = searchFacade.getSurveysByID(sform.getId());
			break;

		case 2:
			surveys = searchFacade.getSurveysByName(sform.getName());
			break;

		case 4:
			surveys = searchFacade.getSurveysByCreationDate(sform.getCreationDate());
			break;

		case 8:
			surveys = searchFacade.getSurveysByStatus(sform.getStatus());
			break;

		default:
			surveys = searchFacade.getSurveysByFullDescription(sform.getId(), sform
					.getName(), sform.getCreationDate(), sform.getStatus());

		}

		request.setAttribute("surveys", surveys);
		return mapping.findForward("searchForm");

	}

	/**
	 * 
	 * Sets the search component implementation to use
	 * 
	 * @param searchFacade
	 */
	public void setSearchFacade(ISearchComponent searchFacade) {
		this.searchFacade = searchFacade;
	}

}
