package ar.com.survey.persistence;



public enum Table {
	SURVEY ("survey"),
	QUOTA ("quota"),
	SECTION ("section"),
	QUESTION ("question"),
	PERSON ("person");
	
	
	public final String name;
	Table(String name) {
		this.name = name;
	} 
}