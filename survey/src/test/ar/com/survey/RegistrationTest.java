package ar.com.survey;

import ar.com.survey.model.Person;
import ar.com.survey.persistence.DBTableHelper;
import ar.com.survey.persistence.PersistenceTestCase;
import ar.com.survey.persistence.Table;
import ar.com.survey.registration.RegistrationComponent;
import ar.com.survey.util.DbPropsImpl;

public class RegistrationTest extends PersistenceTestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(RegistrationTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	/**
	 * 
	 */
	public void testRegistration() {
		DBTableHelper tPerson = new DBTableHelper(Table.PERSON);
		
		RegistrationComponent rc = new RegistrationComponent();
		rc.setEmailService(new DummyEmailService());
		rc.setDbProps(new DbPropsImpl());
		
		Person reg1 = new Person();
		reg1.setFirstName(prefix("name1"));
		rc.register(reg1);
		
		tPerson.checkDeltaAndMark(1);
		
		
		
/*		//test simple create
		DBTableHelper tPerson = new DBTableHelper(Table.PERSON);
		DBTableHelper tQuota = new DBTableHelper(Table.QUOTA);		
		Person p1 = new Person();
		p1.setFirstName(prefix("name1"));
		personDAO
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
		this.closeTx();*/

	}		
		
	

}
