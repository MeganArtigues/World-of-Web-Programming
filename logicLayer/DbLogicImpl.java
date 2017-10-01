package logicLayer;

import object.*;
import persistance.DbPersistImpl;

import java.util.ArrayList;

public class DbLogicImpl {
	
DbPersistImpl persist;
	
	public DbLogicImpl(){
		persist = new DbPersistImpl();
	}
	
	//gets all user info based on username
	public Users getUser(String username){
		return persist.getUserInfo(username);
	}
	
	//checks to see if username/password combo exists
	public int checkUser(String username){
		return persist.checkUsername(username);	
	}
	
	//adds user to db
	public int insertUser(String username, String pw, String salt, int level, int exp ){
		Users u = new Users( username, pw, salt, level, exp);
		return persist.addUser(u);
	}
	
	//displays a random question for "battle"
	public Questions showQuestions(int level, int userId){
		return persist.showQuestion(level, userId);
	}
	
	//ability to insert a question into db
	public int insertQuestion(String question, String answer1, String answer2, String answer3, String answer4, int correct, int level, String question_Type){
		Questions q = new Questions(question, answer1, answer2, answer3, answer4, correct, level, question_Type);
		return persist.persistQuestion(q);
	}
	
	/*public int addUserItem(int user_Id, String username, String pw, int itemid, String description){
		Users u = new Users(user_Id, username, pw);
		Items i = new Items(itemid, description);
		return persist.addUser_Item(u, i);
	}*/
	
	/*public int questionCompleted(int user_Id, String username, String pw, int question_Id, String question, String answer1, String answer2, String answer3, String answer4, int correct){
		Users u = new Users(user_Id, username, pw);
		Questions q = new Questions(question_Id, question, answer1, answer2, answer3, answer4, correct);
		return persist.completeQuestion(u, q);
	}*/
	
	//gets list of all completed questions for that user
	public ArrayList<Questions> getCompletedQuestions(int user_Id, String username, String pw){
		Users u = new Users(user_Id, username, pw);
		return persist.showCompletedQuestions(u);
	}
	
	//updates user experience and level in db
	public boolean updateLevelAndExperience(String username, int exp, int lvl){
		return persist.updateLevelAndExperience(username, exp, lvl);
	}
	
	//marks question as completed if answered correctly
	public int setComplete(int userId, int questionId){
		return persist.setComplete(userId, questionId);
	}
	
	//gives user an item if they earn one by leveling up
	public int giveItem(int uId, int itemId){
		return persist.giveItem(uId, itemId);
	}
	
	//returns list of users based on highest to lowest experience
	public ArrayList<Users> getLeaderboard(){
		return persist.getLeaderboard();
	}
	
	//checks user criteria for login
	public int checkUserLogin(String username, String password){
		return persist.checkUserLogin(username, password);
	}
	
}//end class
	