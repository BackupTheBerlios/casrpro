package ar.com.survey.questions.fields;

public abstract class Field {

	public Field() {
		super();
	}


    private long id;
    private int xpos;
    private int ypos;


    long getId() {
       return this.id;
   }
   
    void setId(long id) {
       this.id = id;
   }
   public int getXpos() {
       return this.xpos;
   }
   
   public void setXpos(int xpos) {
       this.xpos = xpos;
   }
   public int getYpos() {
       return this.ypos;
   }
   
   public void setYpos(int ypos) {
       this.ypos = ypos;
   }
	public abstract String getValue();	

}
