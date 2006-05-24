package ar.com.survey.persistence;



import junit.framework.TestCase;

public class DBTableHelper {
	private Table name;
	private int mark;
	public  DBTableHelper(Table t) {
		this.name = t;
		mark();
	}
	public int getCount() {
		return DBHelper.countRecords(name);
	}
	public void checkDelta(int n) {
		TestCase.assertEquals(mark+n,getCount());
	}
	public void checkDeltaAndMark(int n) {
		TestCase.assertEquals(mark+n,getCount());
		mark();
	}
	public int mark() {
		this.mark = this.getCount();
		return this.mark;
	}
}