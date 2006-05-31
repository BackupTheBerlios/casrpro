package ar.com.survey;

import ar.com.survey.model.enums.Sex;
import ar.com.survey.persistence.PersistenceTestCase;

public class EnumsTest extends PersistenceTestCase {

	public static void main(String[] args) {
		junit.textui.TestRunner.run(EnumsTest.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

		
	public void testSexEnum() {
		assertEquals("F",Sex.FEMALE.getCode());
		assertEquals("M",Sex.MALE.getCode());
		
		Sex m = Sex.valueOf("M");
		Sex f = Sex.valueOf("F");
		Sex x = Sex.valueOf("X");
		assertNotNull(m);
		assertNotNull(m.getDescription());
		assertNotNull(f);
		assertNotNull(f.getDescription());
		assertNull(x);
		
		assertEquals("M",m.getCode());
		assertEquals("F",f.getCode());
		
	
	}
	
}
