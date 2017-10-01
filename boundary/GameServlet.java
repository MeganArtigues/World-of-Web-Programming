package boundary;

import persistance.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.jdbc.ResultSetMetaData;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapperBuilder;
import freemarker.template.SimpleHash;

import java.util.ArrayList;
import java.util.List;

import persistance.*;
import logicLayer.*;
import object.*;
/**
 * Servlet implementation class StartServlet
 */
@WebServlet("/GameServlet") 
public class GameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String templateDir = "/WEB-INF/templates";
	private TemplateProcessor processor;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GameServlet() {
        super();
    }
    
    public void init(ServletConfig config) throws ServletException {
		super.init(config);
		processor = new TemplateProcessor(templateDir, getServletContext());
	}
  //------------------------------------------------------------------------------------------------------------------------    

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String reg = request.getParameter("register");
		String battle = request.getParameter("battle");
		String question = request.getParameter("questions");
		String previous = request.getParameter("previous");
		String homepage = request.getParameter("homepage");
		String leader = request.getParameter("leader");
		String logout = request.getParameter("logout");
		if (login != null) {
			login(request, response);
		}else if(reg != null){
			register(request, response);
		}else if (battle != null) {
			battle(request, response);
		}else if( previous != null ){
			previous(request, response);
		}else if( question != null ){
			questions(request, response);
		}else if (homepage != null){
			homepage(request, response);
		}else if (leader != null){
			leaderboard(request, response);
		}else if (logout != null){
			logout(request, response);
		}
	} // end of doGet

    
  //----------------------------LOGIN METHOD-----------------------------------------------------------------------------------   
	
    
  	private void login(HttpServletRequest request, HttpServletResponse response) {
  		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
  		SimpleHash root = new SimpleHash(db.build());
  		String username = request.getParameter("username");
  		String password = request.getParameter("password");
  		
    	DbLogicImpl ctrl = new DbLogicImpl();
    	//success = 1 means the user was found with matching password and is allowed to log in
    	//success = 0 means incorrect login or password
  		int success = ctrl.checkUserLogin(username, password);
  		String templateName = null;

		root.put("username", username);
		root.put("password", password);
  		
		if (success == 1){
			templateName = "homepage.ftl";
			Users u = ctrl.getUser(username);
	  		
	  		int lvl = u.getCharacter_Level();
	  		int exp = u.getExperience();
	  		
	  		root.put("lvl", lvl);
	  		root.put("exp", exp);
  		}else{
  			String errorMsg = "Username of password is incorrect.";
  			root.put("errorMsg", errorMsg);
  			templateName = "error.ftl";
  		}

  		processor.processTemplate(templateName, root, request, response);
  	}//login
  	
  	


  //----------------------------REGISTER METHOD ---------------------------------------------------------------------------------    

  	private void register(HttpServletRequest request, HttpServletResponse response){
  		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
  		SimpleHash root = new SimpleHash(db.build());
  		String username = request.getParameter("username");
  		String password = request.getParameter("password");
  		DbLogicImpl ctrl = new DbLogicImpl();
  		
  		String templateName = null;
  		String errorMsg = "Username already exists. Please choose a different one.";
  		
  		//checks to see if the username already exists in the database
  		//lets user register if not in db, otherwise gives an error message
  		int check = ctrl.checkUser(username); 
  		if (check == 0){
  			ctrl.insertUser(username, password, "no salt", 1, 0);
  			root.put("username", username);
  			root.put("password", password);
  			templateName = "homepage.ftl";
  		}else{
  			root.put("username", username);
  			root.put("password", password);
  			root.put("errorMsg", errorMsg);
  			templateName = "error.ftl";
  		}

  		
  		root.put("lvl", "0");
  		root.put("exp", "0");
  		
  		processor.processTemplate(templateName, root, request, response);
  	}

//--------------------------LOGOUT METHOD-------------------------------------------------------------------------------
   public void logout(HttpServletRequest request, HttpServletResponse response){
	   DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String templateName = "index.ftl";
		processor.processTemplate(templateName, root, request, response);
   }
//--------------------------LEADERBOARD METHOD-------------------------------------------------------------------------------
    
    private void leaderboard(HttpServletRequest request, HttpServletResponse response) {
    	DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		root.put("username", username);
		root.put("password", password);
		

		//gets all users in descending order based on experience
    	DbLogicImpl ctrl = new DbLogicImpl();
    	ArrayList<Users> u = ctrl.getLeaderboard();
		
    	root.put("users", u);
    	String templateName = "leaderboard.ftl";
		processor.processTemplate(templateName, root, request, response);
	}
    
//--------------------------HOMEPAGE METHOD-------------------------------------------------------------------------------

	private void homepage(HttpServletRequest request, HttpServletResponse response) {
    	DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		root.put("username", username);
		root.put("password", password);
		
		//gets user info
    	DbLogicImpl ctrl = new DbLogicImpl();
		Users u = ctrl.getUser(username);
		
		int lvl = u.getCharacter_Level();
		int exp = u.getExperience();
		
		root.put("lvl", lvl);
		root.put("exp", exp);
		
		String templateName = "homepage.ftl";
		processor.processTemplate(templateName, root, request, response);
		
	}
	
	//-------------------------BATLLE METHOD-------------------------------------------------------------------------------------    

    private void battle(HttpServletRequest request, HttpServletResponse response) {
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		root.put("username", username);
		root.put("password", password);
	
		//gets user info to use when selecting questions
		DbLogicImpl ctrl = new DbLogicImpl();
		String templateName = null;
		Users u = ctrl.getUser(username);
		int level = u.getCharacter_Level();
		int userId = u.getUser_Id();
		
		
		//gets a random question appropriate for that user
		Questions q = ctrl.showQuestions(level, userId);
		int qId = q.getId();
		root.put("qId", qId);
		
		root.put("question", q.getQuestion());

		root.put("answer1", q.getAnswer1());
		root.put("answer2", q.getAnswer2());
		if (q.getAnswer3() == null && q.getAnswer4() == null){
			root.put("type", "other");
		}else{
			root.put("type", "options");
			root.put("answer3", q.getAnswer3());
			root.put("answer4", q.getAnswer4());
		}
		
		String questionType = q.getQuestion_Type();
		root.put("questionType", questionType);
		
		root.put("correct", q.getCorrect());
		
		templateName = "battle.ftl";
		processor.processTemplate(templateName, root, request, response);
		
	}

//--------------------------QUESTIONS METHOD-------------------------------------------------------------------------------
    private void questions(HttpServletRequest request, HttpServletResponse response) {
    	DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
    	SimpleHash root = new SimpleHash(db.build());
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	String questionType = request.getParameter("qType");
    	root.put("username", username);
    	root.put("password", password);
    	root.put("questionType", questionType);
    	
    	
    	//checks to see if user answer was correct
    	String qId1 = request.getParameter("qId");
    	System.out.println("qId1: "+qId1);
    	int qId = Integer.parseInt(qId1);
    	System.out.println("INT qId: "+qId);
    	
    	DbLogicImpl ctrl = new DbLogicImpl();
    	String templateName = null;
    	
    	String answer = request.getParameter("options");
    	System.out.println("answer: "+answer);
    	
    	String correct = request.getParameter("correct");
    	int correctAnswer = Integer.parseInt(correct);
    	int answered = 0;
    	
    	if (answer.equals("answer1")){
    		answered = 1;
    	}else if (answer.equals("answer2")){
    		answered = 2;
    	}else if (answer.equals("answer3")){
    		answered = 3;
    	}else if (answer.equals("answer4")){
    		answered = 4;
    	}
    	
    	
    	System.out.println("Correct: " + correct);
    	System.out.println("Answered: " + answered);
    	
    	if (correctAnswer == answered){
    		Users u = ctrl.getUser(username);
    		int uId = u.getUser_Id();
    		
    		u.setExperience(u.getExperience()+10);
    		
    		if (u.getExperience() % 50 == 0){
    			u.setCharacter_Level(u.getCharacter_Level()+1);
    		}
    		
    		
    		boolean update = ctrl.updateLevelAndExperience(username, u.getExperience(), u.getCharacter_Level());

    		if (update == true){
    			root.put("levelUp", "updated");
    			if (u.getCharacter_Level() == 2){
    				int item = 1;
    				ctrl.giveItem(uId, item);
    			}else if (u.getCharacter_Level() == 3){
    				
    			}else if (u.getCharacter_Level() == 4){
    				
    			}
    		}else{
    			root.put("levelUp", "none");
    		}
    		
    		ctrl.setComplete(uId, qId);
    		
    		templateName = "correct.ftl";
    	}else{
    		templateName = "incorrect.ftl";
    	}
    	
    	processor.processTemplate(templateName, root, request, response);
    }
    

	
//----------------------------PREVIOUS BATTLES METHOD-----------------------------------------------------------------------
	
	private void previous(HttpServletRequest request, HttpServletResponse response){
		DefaultObjectWrapperBuilder db = new DefaultObjectWrapperBuilder(Configuration.VERSION_2_3_25);
		SimpleHash root = new SimpleHash(db.build());
		String username = request.getParameter("username");
		String password = null;
		
		ResultSet rs = DbAccessImpl.retrieve("SELECT * FROM Users WHERE username='"+username+"'");
		int id = -1;
		ArrayList<Questions> questions = new ArrayList<Questions>();
		try{
			while(rs.next()){
				id = rs.getInt("user_Id");
				password = rs.getString("pw");
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		DbLogicImpl ctrl = new DbLogicImpl();
		questions = ctrl.getCompletedQuestions(id, username, password);
		root.put("questions", questions);
		root.put("username", username);
		root.put("password", password);
		String templateName = "previous.ftl";
		processor.processTemplate(templateName, root, request, response);
	}


//---------------------------------------------------------------------------------------------------------------------------
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}