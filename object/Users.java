package object;

public class Users
{
    private int user_Id;
    private String username, pw, salt;
    private int character_Level;
    private int experience;
    
    public Users(){
    	this.user_Id = 0;
    	this.username = "";
    	this.pw = "";
    	this.salt = "";
    	this.character_Level = -1;
    	this.experience = -1;
    }
    
    public Users(int user_Id, String username, String pw, String salt, int character_Level, int experience ){
    	this.user_Id = user_Id;
    	this.username = username;
    	this.pw = pw;
    	this.salt = salt;
    	this.character_Level = character_Level;
    	this.experience = experience;
    }
    public Users(String username, String pw, String salt, int character_Level, int experience ){
    	this.username = username;
    	this.pw = pw;
    	this.salt = salt;
    	this.character_Level = character_Level;
    	this.experience = experience;
    }
    public Users(int user_Id, String username, String pw){
    	this.user_Id = user_Id;
    	this.username = username;
    	this.pw = pw;
    }
	/**
	 * @return the user_Id
	 */
	public int getUser_Id() {
		return user_Id;
	}
	/**
	 * @param user_Id the user_Id to set
	 */
	public void setUser_Id(int user_Id) {
		this.user_Id = user_Id;
	}
	/**
	 * @return the username
	 */
	public String getUsername(){
		return username;
	}
	/**
	 *@param the user's username
	 */
	public void setUsername( String username ){
		this.username = username;
	}
	/**
	 * @return the pw
	 */
	public String getPw() {
		return pw;
	}
	/**
	 * @param pw the pw to set
	 */
	public void setPw(String pw) {
		this.pw = pw;
	}
	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the character_Level
	 */
	public int getCharacter_Level() {
		return character_Level;
	}
	/**
	 * @param character_Level the character_Level to set
	 */
	public void setCharacter_Level(int character_Level) {
		this.character_Level = character_Level;
	}
    /**
     * @return the user's experience
     */
    public int getExperience(){
    	return experience;
    }
    /**
     * @param experience of the user
     */
    public void setExperience(int experience){
    	this.experience = experience;
    }
}
