package ar.com.survey;


public class TestUtils {
	private final static String testPrefix;
	static {
		StringBuilder sb = new StringBuilder("t_");
		for (int i = 0; i < 5; i++) {
			sb.append((char) (Math.random() * 26 + 'a'));
		}
		sb.append('_');
		testPrefix = sb.toString();
	}
	public static final String prefix(final String s) {
		return testPrefix+s;		
	}

	private TestUtils() {
		super();
	}

}
