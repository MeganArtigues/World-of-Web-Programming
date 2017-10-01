package persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.mysql.jdbc.Connection;

import object.*;

public class DbPersistImpl {
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
	//retreives and sets all user info to a Users object
	public Users getUserInfo(String username){
		String sql = "SELECT * FROM Users WHERE username = '" + username + "';";
		ResultSet r = DbAccessImpl.retrieve(sql);
		Users u = null;
		
		try {
			if (r.next()){
				u = new Users(r.getInt("user_Id"), r.getString("username"), r.getString("pw"), r.getString("salt"), r.getInt("character_Level"), r.getInt("Experience")); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return u;
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------

	//a statement to insert a question into the database
	public int persistQuestion(Questions q) {
		String sql = "INSERT INTO Questions (question, answer1, answer2, answer3, answer4, correct, level, question_Type) VALUES"
				+ "('" + q.getQuestion() + "','" + q.getAnswer1() + "','" + q.getAnswer2() + "','" + q.getAnswer3() + "','" + q.getAnswer4() + "','" + q.getCorrect() + "','" + q.getLevel() + "','" + q.getQuestion_Type() + "');";
		return DbAccessImpl.create(sql);
	}	

//----------------------------------------------------------------------------------------------------------------------------------------------------------

	//show a question based on level
	public Questions showQuestion(int level, int userId) {
		Random rand = new Random();
		
		System.out.println(level + " , " + userId);
		
		String sql = "SELECT * FROM questions WHERE NOT EXISTS (SELECT * FROM completed_questions WHERE Questions.id = completed_questions.question_Id AND completed_questions.user_Id = '" + userId + "') AND Questions.level <= '" + level + "';";
		ArrayList<Questions> tempList = new ArrayList<Questions>();
		ResultSet rs = DbAccessImpl.retrieve(sql);
		try {
			while(rs.next()) {
				//System.out.println(rs.getInt("id"));
				Questions question = new Questions(rs.getInt("id"), rs.getString("question"), rs.getString("answer1"), rs.getString("answer2"), rs.getString("answer3"), rs.getString("answer4"), rs.getInt("correct"), rs.getInt("level"), rs.getString("question_Type"));
				tempList.add(question);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
		
		int size = tempList.size();
		int index = rand.nextInt((size-1)+1);
		Questions q = null;
		q = tempList.get(index);
		
		System.out.println(q.getQuestion());
		
		return q;
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

	//add a user
	public int addUser(Users u) {
		String sql = "INSERT INTO Users (username, pw, salt, character_Level, Experience) VALUES"
				+ "('"+u.getUsername()+"','"+u.getPw()+"', '"+u.getSalt()+"', '"+u.getCharacter_Level()+"', '"+u.getExperience()+"')";
		return DbAccessImpl.create(sql);
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

   //give an item to a user int the user_items table    
    public int addUser_Item(Users u, Items i){
        String sql = "INSERT INTO user_Items (ui_User_Id, ui_Item_Id) VALUES('"+u.getUser_Id()+"', '"+i.getItem_Id()+"')";
        return DbAccessImpl.create(sql);
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------
	
    //add to a list of completed questions for that user    
   /* public int completeQuestion(Users u, Questions q){
        String sql = "INSERT INTO completed_questions (user_Id, question_Id) VALUES('"+u.getUser_Id()+"','"+q.getId()+"')";
        return DbAccessImpl.create(sql);
    }*/

//----------------------------------------------------------------------------------------------------------------------------------------------------------

    //retrieve a list of questions that the user has completed
    public ArrayList<Questions> showCompletedQuestions(Users u){
    	String sql = "SELECT Questions.id, Questions.question, Questions.answer1, Questions.answer2, Questions.answer3, Questions.answer4, Questions.correct"
    			+ " FROM ((completed_questions INNER JOIN Users ON completed_questions.user_Id = Users.user_Id)"
    			+ " INNER JOIN Questions ON completed_questions.question_Id = Questions.id)"
    			+ " WHERE completed_questions.user_Id = '"+u.getUser_Id()+"' AND Users.user_Id = '"+u.getUser_Id()+"'";
		ArrayList<Questions> tempList = new ArrayList<Questions>();
		ResultSet rs = DbAccessImpl.retrieve(sql);
		try {
			while(rs.next()) {
				//System.out.println(rs.getInt("id"));
				Questions question = new Questions(rs.getInt("id"), rs.getString("question"), rs.getString("answer1"), rs.getString("answer2"), rs.getString("answer3"), rs.getString("answer4"), rs.getInt("correct"));
				tempList.add(question);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempList;
    }

//----------------------------------------------------------------------------------------------------------------------------------------------------------

    //check to see if username already exists
	public int checkUsername(String username) {
		int success = 0;
		String sql = "SELECT * FROM Users WHERE username = '" + username + "';";
		
		ResultSet r;
		r = DbAccessImpl.retrieve(sql);

		String isUser = null;
		try {
			if (r.next()){
				success++;
				System.out.println("success: " + success);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
	}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

	//update user level and experience
	public boolean updateLevelAndExperience(String username, int exp, int lvl){
		boolean levelUp = false;
		String sql1 = "UPDATE Users SET Experience = '" + exp + "' WHERE Users.username = '" + username + "';";
		int r;
		r = DbAccessImpl.update(sql1);
		
		
		String sql2 = "Select character_Level FROM Users WHERE username = '" + username + "';";
		ResultSet r2;
		r2 = DbAccessImpl.retrieve(sql2);	
		
		String eConvert = null;
		int eResult = -1;
		try {
			if (r2.next()){
				eConvert = r2.getString(1);
				eResult = Integer.parseInt(eConvert);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (eResult < lvl){
			String sql3 = "UPDATE Users SET character_Level = '" + lvl + "' WHERE Users.username = '" + username + "';";
			int r3;
			r3 = DbAccessImpl.update(sql3);
			levelUp = true;
		}
		return levelUp;
	}
	
	//----------------------------------------------------------------------------------------------------------------------------------------------------------

	//sets a question to completed when user answers correctly
		public int setComplete(int uId, int qId) {
			 String sql = "INSERT INTO completed_questions (user_Id, question_Id) VALUES('"+uId+"','"+qId+"')";
		     return DbAccessImpl.create(sql);
		}

//----------------------------------------------------------------------------------------------------------------------------------------------------------

		//gives hint item to user
		public int giveItem(int uId, int itemId){
			String sql = "INSERT INTO user_items (ui_User_Id, ui_Item_Id) VALUES ('" + uId +"','" + itemId+"');";
			return DbAccessImpl.create(sql);
		}
		
//----------------------------------------------------------------------------------------------------------------------------------------------------------
		//gets all users in order based on experience - descending order
		public ArrayList<Users> getLeaderboard(){
			String sql = "SELECT * FROM users ORDER BY Experience DESC";
			ArrayList<Users> tempList = new ArrayList<Users>();
			ResultSet rs = DbAccessImpl.retrieve(sql);
			try {
				while(rs.next()) {
					//System.out.println(rs.getInt("id"));
					Users u= new Users(rs.getInt("user_Id"), rs.getString("username"), rs.getString("pw"), rs.getString("salt"), rs.getInt("character_Level"), rs.getInt("Experience"));
					tempList.add(u);	
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return tempList;
		}
		
//----------------------------------------------------------------------------------------------------------------------------------------------------------
		//checks user login criteria
		public int checkUserLogin(String username, String password){
			int success = 0;
			String sql = "SELECT * FROM Users WHERE username = '"+username+"' AND pw = '"+password+"'";
	  		ResultSet rs = DbAccessImpl.retrieve(sql);
	  		
	  		try{
	  			if(rs.next()){
	  				if( username.equals(rs.getString("username")) && password.equals(rs.getString("pw")) ){
	  					success = 1;
	  				}
	  			}else{
	  				success = 0;
	  			}
	  		}catch(SQLException e){
	  			e.printStackTrace();
	  		}
	  		
	  		return success;
		}
}