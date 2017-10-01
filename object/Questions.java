package object;

public class Questions
{
    private int id;
    private String question, answer1, answer2, answer3, answer4;
    private int correct;
    private int level;
    private String question_Type;

    public Questions( int id, String question, String answer1, String answer2, String answer3, String answer4, int correct, int level, String question_Type){
    	this.id = id;
    	this.question = question;
    	this.answer1 = answer1;
    	this.answer2 = answer2;
    	this.answer3 = answer3;
    	this.answer4 = answer4;
    	this.correct = correct;
    	this.level = level;
    	this.question_Type = question_Type;
    }
    public Questions( String question, String answer1, String answer2, String answer3, String answer4, int correct, int level, String question_Type){
    	this.question = question;
    	this.answer1 = answer1;
    	this.answer2 = answer2;
    	this.answer3 = answer3;
    	this.answer4 = answer4;
    	this.correct = correct;
    	this.level = level;
    	this.question_Type = question_Type;
    }
    public Questions( int id, String question, String answer1, String answer2, String answer3, String answer4, int correct ){
    	this.id = id;
    	this.question = question;
    	this.answer1 = answer1;
    	this.answer2 = answer2;
    	this.answer3 = answer3;
    	this.answer4 = answer4;
    	this.correct = correct;
    }
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}
	/**
	 * @return the a1
	 */
	public String getAnswer1() {
		return answer1;
	}
	/**
	 * @param a1 the a1 to set
	 */
	public void setAnswer1(String a1) {
		this.answer1 = a1;
	}
	/**
	 * @return the a2
	 */
	public String getAnswer2() {
		return answer2;
	}
	/**
	 * @param a2 the a2 to set
	 */
	public void setAnswer2(String a2) {
		this.answer2 = a2;
	}
	/**
	 * @return the a3
	 */
	public String getAnswer3() {
		return answer3;
	}
	/**
	 * @param a3 the a3 to set
	 */
	public void setAnswer3(String a3) {
		this.answer3 = a3;
	}
	/**
	 * @return the a4
	 */
	public String getAnswer4() {
		return answer4;
	}
	/**
	 * @param a4 the a4 to set
	 */
	public void setAnswer4(String a4) {
		this.answer4 = a4;
	}
	/**
	 * @return the correct
	 */
	public int getCorrect() {
		return correct;
	}
	/**
	 * @param correct the correct to set
	 */
	public void setCorrect(int correct) {
		this.correct = correct;
	}
	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	/**
	 * @return the item_Type
	 */
	public String getQuestion_Type() {
		return question_Type;
	}
	/**
	 * @param item_Type the item_Type to set
	 */
	public void setItem_Type(String question_Type) {
		this.question_Type = question_Type;
	}
            
}