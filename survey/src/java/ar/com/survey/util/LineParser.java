package ar.com.survey.util;

import java.util.ArrayList;
import java.util.Iterator;

public class LineParser {
	
	private String separator;
	private String line;
	private ArrayList tokens = new ArrayList();
	private Iterator tokenIter = null;
	
	public LineParser(String data, char separator){
		this(data, Character.toString(separator));
	}
	
	public LineParser(String data, String separator){
		this.separator = separator;
		this.line = data;
		parse();
	}
	
	/* parse the string items separated by a simple space into the arrylist */
	
	private void parse(){
		StringBuffer bf = new StringBuffer();
		boolean com = false;
		for(int i=0;i<line.length();i++){
			if(line.charAt(i)!=separator.charAt(0) && line.charAt(i)!='"'){
				bf.append(line.charAt(i));
			} else if (line.charAt(i)=='"'){
				com = com ? false : true;
			} else {
				if(com) {
					bf.append(separator);
				}
				else {
					tokens.add(bf.toString());
					bf = new StringBuffer();
				}
			}
		}
		if(bf.length()>0)
			tokens.add(bf.toString());
		tokenIter = tokens.iterator();
	}
	
	public int countTokens(){
		return tokens.size();
	}
	
	public String nextToken(){
		return (String) tokenIter.next();
	}

}
