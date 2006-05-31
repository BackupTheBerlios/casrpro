package ar.com.survey.model;

import java.util.List;

import ar.com.survey.persistence.DBTableHelper;
import ar.com.survey.persistence.PersistenceTestCase;
import ar.com.survey.persistence.Table;

public class SurveySectionRelationshipTest extends PersistenceTestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(SurveySectionRelationshipTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

		
	public void testSurveySectionRelationShip() {
		//test simple create
		DBTableHelper tSurvey = new DBTableHelper(Table.SURVEY);
		DBTableHelper tSection = new DBTableHelper(Table.SECTION);		
		Survey s1 = new Survey();
		s1.setName(prefix("survey1"));
		List<Section> sections = s1.getSections();
		for (int i = 0; i < 7; i++) {
			Section sectionTmp = new Section();
			sectionTmp.setName(prefix("survey1section"+i));
			sections.add(sectionTmp);
		}
		surveyDAO.createOrUpdate(s1);
		this.closeTx();		
		tSurvey.checkDeltaAndMark(1);
		tSection.checkDeltaAndMark(7);
		//end
		
		//test cascade delete
		surveyDAO.delete(s1);
		this.closeTx();	
		tSurvey.checkDeltaAndMark(-1);
		tSection.checkDeltaAndMark(-7);	
		//end
		
		//create again, test correct order, test insert-in-the-middle, test delete-in-the-middle
		Survey s2 = new Survey();
		s2.setName(prefix("survey2"));
		List<Section> sections2 = s2.getSections();
		for (int i = 0; i < 7; i++) {
			Section sectionTmp = new Section();
			sectionTmp.setName(prefix("survey1section"+i));
			sections2.add(sectionTmp);
		}
		surveyDAO.createOrUpdate(s2);
		this.closeTx();
		this.beginTx();
		tSurvey.checkDeltaAndMark(1);
		tSection.checkDeltaAndMark(7);	
		s2 = surveyDAO.findById(s2.getId());
		assertEquals(prefix("survey1section2"),s2.getSections().get(2).getName());
		assertEquals(0,s2.getSections().get(0).getPosition());
		assertEquals(6,s2.getSections().get(s2.getSections().size()-1).getPosition());
		Section sectionTmp = new Section();
		sectionTmp.setName(prefix("survey1section3b"));
		Section sectionTmp2 = new Section();
		sectionTmp2.setName(prefix("survey1section5b"));
		s2.getSections().add(3,sectionTmp); //insert-in-the-middle
		s2.addSection(7,sectionTmp2);//insert-in-the-middle
		this.closeTx();
		this.beginTx();
		s2 = surveyDAO.findById(s2.getId());
		tSection.checkDeltaAndMark(2);
		assertEquals(9,s2.getSections().size());
		assertEquals(prefix("survey1section0"),s2.getSection(0).getName());
		assertEquals(prefix("survey1section1"),s2.getSections().get(1).getName());
		assertEquals(prefix("survey1section2"),s2.getSections().get(2).getName());
		assertEquals(prefix("survey1section3b"),s2.getSection(3).getName());
		assertEquals(prefix("survey1section3"),s2.getSections().get(4).getName());
		assertEquals(prefix("survey1section4"),s2.getSections().get(5).getName());
		assertEquals(prefix("survey1section5"),s2.getSection(6).getName());
		assertEquals(prefix("survey1section5b"),s2.getSections().get(7).getName());
		assertEquals(prefix("survey1section6"),s2.getSections().get(8).getName());
		this.closeTx();
		this.beginTx();
		Section sectionTmp3 = new Section();
		sectionTmp3.setName(prefix("survey1section3xxx"));
		s2.getSections().add(3,sectionTmp); //insert-in-the-middle
		s2 = surveyDAO.findById(s2.getId());
		s2.replaceSection(3,sectionTmp3);
		this.closeTx();
		tSection.checkDeltaAndMark(0);
		assertEquals(prefix("survey1section2"),s2.getSections().get(2).getName());
		assertEquals(prefix("survey1section3xxx"),s2.getSection(3).getName());
		assertEquals(prefix("survey1section3"),s2.getSections().get(4).getName());		
		tSurvey.checkDeltaAndMark(0);
		tSection.checkDeltaAndMark(0);
		
		
		s2 = surveyDAO.findById(s2.getId());
		s2.deleteSection(2);
		s2.getSections().remove(5);
		this.closeTx();
		this.beginTx();
		s2 = surveyDAO.findById(s2.getId());
		assertEquals(7,s2.getSections().size());
		assertEquals(prefix("survey1section0"),s2.getSection(0).getName());
		assertEquals(prefix("survey1section1"),s2.getSections().get(1).getName());
		assertEquals(prefix("survey1section3xxx"),s2.getSection(2).getName());
		assertEquals(prefix("survey1section3"),s2.getSections().get(3).getName());
		assertEquals(prefix("survey1section4"),s2.getSections().get(4).getName());
		assertEquals(prefix("survey1section5b"),s2.getSections().get(5).getName());
		assertEquals(prefix("survey1section6"),s2.getSections().get(6).getName());

		tSection.checkDeltaAndMark(-2);
		
		
		//end
		
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
