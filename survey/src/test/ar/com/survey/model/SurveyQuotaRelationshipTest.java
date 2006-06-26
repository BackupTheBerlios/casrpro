package ar.com.survey.model;

import ar.com.survey.exceptions.DuplicateEntityException;
import ar.com.survey.persistence.DBTableHelper;
import ar.com.survey.persistence.PersistenceTestCase;
import ar.com.survey.persistence.Table;

public class SurveyQuotaRelationshipTest extends PersistenceTestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(SurveyQuotaRelationshipTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	public void testSurveyQuotaRelationship() {
		
		//test simple create
		DBTableHelper tSurvey = new DBTableHelper(Table.SURVEY);
		DBTableHelper tQuota = new DBTableHelper(Table.QUOTA);		
		Survey s1 = new Survey();
		s1.setName(prefix("name1"));
		surveyDAO.createNew(s1);
		this.closeTx();		
		tSurvey.checkDeltaAndMark(1);
		//end
		
		//test createNew throws DuplicateEntityException
		boolean catchBlockEntered = false; 
		try {
			//create dif survey with same natural-id
			Survey s2 = new Survey();
			s2.setName(prefix("name1"));
			surveyDAO.createNew(s2);			
		} catch (DuplicateEntityException e) {
			catchBlockEntered = true;
			tSurvey.checkDeltaAndMark(0);
			//throw e;
		}
		assertTrue("can't create two Surveys with same natural-id",catchBlockEntered);
		assertNull("get non existent quota",s1.getQuota("no"));
		//end
					
		
		
		//test quota needs to be added to the set to be persisted
		Quota q1 = new Quota(prefix("q1"),s1,22,33);
		this.closeTx();
		tQuota.checkDeltaAndMark(0);
		//end
		

		//test save two quotas
		Quota q2 = new Quota(prefix("q2"),null,44,55);
		Quota q3 = new Quota(prefix("q3"),null,441,551);
		Quota q4 = new Quota(prefix("q4"),null,442,552);
		Quota q4dup = new Quota(prefix("q4"),null,442,552);
		s1.getQuotas().add(q1);
		s1.getQuotas().add(q2);
		s1.getQuotas().add(q3);
		s1.getQuotas().add(q4);
		s1.getQuotas().add(q4dup);  //set allows no duplicates
		surveyDAO.createOrUpdate(s1);					
		this.closeTx();
		tQuota.checkDeltaAndMark(4);
		tSurvey.checkDeltaAndMark(0);
		//end
		
		
		//quota q1 re-assigned to a newly created survey
		this.beginTx();
		Survey s2 = new Survey();
		s2.setName(prefix("name2"));
		s2.getQuotas().add(q1);
		surveyDAO.createNew(s2);
		this.closeTx();
		
		tQuota.checkDeltaAndMark(0);
		tSurvey.checkDeltaAndMark(1);
		s1 = surveyDAO.findBySurrogateKey(createSurvey(prefix("name1")));
		s2 = surveyDAO.findBySurrogateKey(createSurvey(prefix("name2")));
		assertEquals(3,s1.getQuotas().size());
		assertEquals(1,s2.getQuotas().size());
		assertNull(s1.getQuota(prefix("q1")));
		assertNotNull(s1.getQuota(prefix("q2")));
		assertNotNull(s1.getQuota(prefix("q3")));
		assertNotNull(s1.getQuota(prefix("q4")));
		assertNull(s2.getQuota(prefix("q2")));
		assertNotNull(s2.getQuota(prefix("q1")));
		//end
		
		//add quotas to s2
		Quota q5 = new Quota(prefix("q5"),null,44,55);
		Quota q6 = new Quota(prefix("q6"),null,441,551);
		s2 = surveyDAO.findById(s2.getId());
		s2.getQuotas().add(q5);
		s2.getQuotas().add(q6);
		surveyDAO.createOrUpdate(s2);	
		this.closeTx();
		tQuota.checkDeltaAndMark(2);
		//end
		
		//test graph modification, note that is not needed to execute an explicit update,
		//h3 will check the dirty flags.
		s2 = surveyDAO.findById(s2.getId());
		s2.getQuota(prefix("q5")).setLimit(567);
		s2.getQuota(prefix("q6")).setLimit(789);
		this.closeTx();
		this.beginTx();
		s2 = surveyDAO.findById(s2.getId());
		assertEquals(s2.getQuota(prefix("q5")).getLimit(),567);
		assertEquals(s2.getQuota(prefix("q6")).getLimit(),789);		
		//end
		
		//test relationship not broken on dirty
		q2 = quotaDAO.findById(q2.getId());
		q1 = quotaDAO.findById(q1.getId());
		q1.setSurvey(null);
		q2.setSurvey(null);
		quotaDAO.createOrUpdate(q1);
		this.closeTx();
		tQuota.checkDeltaAndMark(0);
		tSurvey.checkDeltaAndMark(0);
		//end
		
		//test relationship deleted
		s1 = surveyDAO.findById(s1.getId());
		q2 = s1.getQuota(prefix("q2"));
		assertNotNull(q2);
		s2 = surveyDAO.findById(s2.getId());
		q1 = s2.getQuota(prefix("q1"));
		assertNotNull(q1);
		assertTrue(s1.getQuotas().remove(q2));
		assertTrue(s2.getQuotas().remove(q1));		
		this.closeTx();
		tQuota.checkDeltaAndMark(-2);
		tSurvey.checkDeltaAndMark(0);
		//end
		
		//test set clean
		s1 = surveyDAO.findById(s1.getId());
		s1.getQuotas().clear();
		this.closeTx();
		tQuota.checkDeltaAndMark(-2);
		tSurvey.checkDeltaAndMark(0);
		//end
		
		//test cascade delete
		surveyDAO.delete(surveyDAO.findById(s2.getId()));
		this.closeTx();
		tQuota.checkDeltaAndMark(-2);
		tSurvey.checkDeltaAndMark(-1);
		//end
		this.closeTx();
		this.closeSession();	
	}

	private Survey createSurvey(String n) {
		Survey s =  new Survey();
		s.setName(n);
		return s;
	}		
		
		
		
		
		
//		this.beginTx();
//		s1 = surveyDAO.findById(s1.getId());
//		s1.getSections().add(new Section(null,prefix("section1"),null,null,null));
//		s1.getSections().add(new Section(null,prefix("section1"),null,null,null));
//
//		DBTableHelper tSection = new DBTableHelper(Table.SECTION);
//		try {
//			catchBlockEntered = false;
//			surveyDAO.createOrUpdate(s1);
//			this.getSession().flush();
////		} catch (ConstraintViolationException cve) {
////			HibernateUtil.handleException(null);			
////			catchBlockEntered = true;			
////		} catch (DuplicateEntityException dee) {
////			HibernateUtil.handleException(null);			
////			catchBlockEntered = true;			
//		} catch (NonUniqueObjectException nuoe) {
//			HibernateUtil.handleException(null);			
//			catchBlockEntered = true;			
//		}
//		
//		assertTrue(catchBlockEntered);
//		catchBlockEntered = false;
//
//		tSection.checkDeltaAndMark(0);
//		tSurvey.checkDeltaAndMark(0);
//		
//		this.beginTx();
//		s1.getSections().get(1).setName(prefix("section2"));
//		//s1.getSections().remove(0);
//		surveyDAO.createOrUpdate(s1);
////		//tSection.checkDeltaAndMark(2);
////		//tSurvey.checkDeltaAndMark(0);

}
