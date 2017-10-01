-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: wowpdb
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `completed_questions`
--

DROP TABLE IF EXISTS `completed_questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `completed_questions` (
  `user_Id` int(11) NOT NULL,
  `question_Id` int(11) NOT NULL,
  PRIMARY KEY (`user_Id`,`question_Id`),
  KEY `question_Id` (`question_Id`),
  CONSTRAINT `completed_questions_ibfk_1` FOREIGN KEY (`user_Id`) REFERENCES `users` (`user_Id`),
  CONSTRAINT `completed_questions_ibfk_2` FOREIGN KEY (`question_Id`) REFERENCES `questions` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `completed_questions`
--

LOCK TABLES `completed_questions` WRITE;
/*!40000 ALTER TABLE `completed_questions` DISABLE KEYS */;
INSERT INTO `completed_questions` VALUES (1,1),(2,1),(3,1),(5,1),(3,2),(3,3),(3,4),(2,6),(1,7),(3,7),(5,7),(1,8),(3,8),(5,8),(17,8),(3,9),(4,9),(5,9),(18,9),(3,12),(3,13),(3,15),(3,16),(3,20),(5,20),(3,24),(3,27),(3,28),(3,30),(3,33),(3,37),(3,38),(5,38),(3,39),(3,41),(5,41),(3,43),(5,43),(3,44),(1,48),(1,49),(3,49),(5,49),(3,50),(4,50),(5,50);
/*!40000 ALTER TABLE `completed_questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `items`
--

DROP TABLE IF EXISTS `items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `items` (
  `item_Id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(140) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`item_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `items`
--

LOCK TABLES `items` WRITE;
/*!40000 ALTER TABLE `items` DISABLE KEYS */;
INSERT INTO `items` VALUES (1,'A timed Google seach box that displays for 1 minute.',NULL);
/*!40000 ALTER TABLE `items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(200) DEFAULT NULL,
  `answer1` varchar(100) DEFAULT NULL,
  `answer2` varchar(100) DEFAULT NULL,
  `answer3` varchar(100) DEFAULT NULL,
  `answer4` varchar(100) DEFAULT NULL,
  `correct` int(11) DEFAULT NULL,
  `level` int(11) DEFAULT NULL,
  `question_Type` varchar(16) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'What does HTML stand for?','HyperTypeMachineLanguage','HyperTextMarkupLanguage','HyperTextMachineLearning','HyperTextMemoryLanguage',2,1,'HTML'),(2,'True or False: The browser places a margin of white space between block elements','True','False',NULL,NULL,1,3,'HTML'),(3,'True or False: Only one inline element can appear on a line','True','False',NULL,NULL,2,3,'HTML'),(4,'True or False: An inline element must be nested inside a block element','True','False',NULL,NULL,1,3,'HTML'),(5,'How many heading tags are there?','3','10','Infinite','6',4,5,'HTML'),(6,'Fill in the missing attribute: &lt;a ___=\"http://www.google.com/\"&gt;Google&lt;/a&gt;','class','type','href','name',3,2,'HTML'),(7,'What is the proper HTML comment syntax?','\"//Content\"','\"/*Content*/\"','&lt;#Content&gt;','&lt;!--Content--&gt;',4,1,'HTML'),(8,'What does CSS stand for?','CascadingStyleSheet','CompleteStyleSheet','CascadingStyleSyntax','CompleteSyntaxStyle',1,1,'CSS'),(9,'True or False: CSS describes the appearance, layout, and presentation of information on a web page','True','False',NULL,NULL,1,1,'CSS'),(10,'True or False: It is good habit to write all CSS within a style attribute in HTML','True','False',NULL,NULL,2,2,'CSS'),(11,'\"#example{ ... }\" What is example defined as?','class','id','name','type',2,3,'CSS'),(12,'\".example{ ... }\" What is example defined as?','class','id','name','type',1,3,'CSS'),(13,'True or False: A page can link to multiple stylesheets','True','False',NULL,NULL,1,2,'CSS'),(14,'Which has the highest precedence?','A linked stylesheet','Style written in a &lt;style&gt; tag','Style written as an inline style attribute','All have the same precedence',3,4,'CSS'),(15,'What is the proper CSS comment syntax?','\"*/Content/*\"','\"/*Content*/\"','&lt;#Content&gt;','&lt;!--Content--&gt;',2,1,'CSS'),(16,'True or False: Every element is composed of content, a border, padding, and a margin','True','False',NULL,NULL,1,3,'CSS'),(17,'Which of the following methods does the engine NOT call during a servlets normal life cycle?','init()','service()','log()','destroy()',3,4,'Servlet'),(18,'Every servlet call results in a call to which method?','init()','service()','doGet()','doPost()',2,4,'Servlet'),(19,'Ideally, how many times should init() be called?','None','Once total','Once for each request','Twice',2,2,'Servlet'),(20,'Where should you put your third-party jars?','WEB-INF/lib','Java Resources/src','In the root directory','On the third shelf',1,2,'Servlet'),(21,'Which of the following is NOT an advantage Post() has over Get()?','Post() does not dispay form data','Post() has no restrictions on data type','Post() has no restrictions on form length','Post() results can be bookmarked',4,2,'Servlet'),(22,'Which of the following would be considered metadata?','Comments','The time the data was last modified','The results of a query','A nested query',2,3,'Database'),(23,'JDBC consists of two parts: A JDBC API and...','A JDBC install wizard','A JDBC driver manager','A table of prepared SQL statements','Cookies',2,4,'Database'),(24,'What is the first step in using JDBC?','Load the driver','Define the connection URL','Establish the connection','Create a statement object',1,3,'Database'),(25,'How are the results of a query stored in JDBC?','Statement object','Connection object','Query object','ResultSet object',4,5,'Database'),(26,'Which of the following is an example of an aggregate function?','SELECT DISTINCT()','JOIN()','MAX()','EXISTS()',3,4,'Database'),(27,'What does MVC stand for in Object-Oriented design?','Mehdi Very Confused','Modesto Valley California','Multi Variable Calculus','Model View Controller',4,1,'MVC'),(28,'Which part of MVC manages the data?','Model','View','Controller','None of the above',1,4,'MVC'),(29,'Which part of MVC handles user interaction?','Model','View','Controller','None of the above',3,4,'MVC'),(30,'Which part of MVC displays data?','Model','View','Controller','None of the above',2,2,'MVC'),(31,'Which part of MVC ensures code independence?','Model','View','Controller','None of the above',4,4,'MVC'),(32,'Which part of MVC would an HTML page be?','Model','View','Controller','None of the above',2,2,'MVC'),(33,'Which part of MVC would a servlet be?','Model','View','Controller','None of the above',3,4,'MVC'),(34,'Which part of MVC communicates with a database?','Model','View','Controller','None of the above',1,4,'MVC'),(35,'Which of the following types of data is NOT supported by Ajax?','XML','HTML','JSON formatted strings','Java objects',4,5,'JQuery'),(36,'In which circumstance would you want to use Ajax?','If you want a user to frequently refresh a web page','If you have a large amount of data that must be loaded ahead of time','If you prefer the user to navigate to a large number of web pages','If you want to suggest the results of a search before the user hits enter',4,5,'JQuery'),(37,'Which of the following Java features are analogous to the functionality of Ajax?','Event listeners','Try/Catch blocks','ArrayLists','Bit shifting',1,5,'JQuery'),(38,'True or False: JavaScript closely corresponds to Java','True','False',NULL,NULL,2,1,'JavaScript'),(39,'Where are you supposed to include the &lt;script&gt; tag to link a JavaScript file?','inside &lt;title&gt;','inside &lt;body&gt;','inside &lt;head&gt;','right after &lt;!DOCTYPE HTML&gt;',3,1,'JavaScript'),(40,'Which JavaScript function does NOT bring up a dialog box?','alert()','prompt()','open()','confirm()',3,5,'JavaScript'),(41,'True or False: JavaScript is a dynamically typed language (i.e. uses var to dynamically assign types)','True','False',NULL,NULL,1,2,'JavaScript'),(42,'In JavaScript, \"==\" is equivalent to _____ in Java','==','.equalTo()','=','.equals()',4,5,'JavaScript'),(43,'Basic JavaScript function syntax looks like which of the following?','function name(param){...}','function(){...}','public name(param){...}','public function name(param){...}',1,2,'JavaScript'),(44,'How do you access an element from an HTML file?','document.getElement(\"element\")','document.getElementById(\"element\")','document.findElement(\"element\")','document.get(\"element\")',2,3,'JavaScript'),(45,'onclick() is classified as:','an HTML tag','a JavaScript function','a JavaScript tag','an event handler',4,5,'JavaScript'),(46,'What is the proper syntax for selecting a DOM element by id using JQuery?','$(\"element-id\")','JQ.getElement(\"element-id\")','$element-id','#(\"element-id\")',1,5,'JQuery'),(47,'How would you add a class to an element using JQuery?','$(\"element-id\")+class(\"name\")','$(\"element-id\").addClass(\"name\")','$addClass(\"name\").element-id','$(element-id.addClass(\"name\"))',2,3,'JQuery'),(48,'You can return all elements with JQuery by using:','$(getAll)','$(document).getAll','$getAll','$(\"*\")',4,5,'JQuery'),(49,'What is a servlet?','The term for a newborn server','a third-party library linked inside an HTML head','a Java server side program, which outputs an HTML page','a server side applet, which outputs an HTML page',3,1,'Servlet'),(50,'True or False: The query \"SELECT * FROM table\" selects all tuples in table','True','False',NULL,NULL,1,1,'Database');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_items`
--

DROP TABLE IF EXISTS `user_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_items` (
  `ui_User_Id` int(11) NOT NULL,
  `ui_Item_Id` int(11) NOT NULL,
  PRIMARY KEY (`ui_Item_Id`,`ui_User_Id`),
  KEY `ui_User_Id` (`ui_User_Id`),
  CONSTRAINT `user_items_ibfk_1` FOREIGN KEY (`ui_User_Id`) REFERENCES `users` (`user_Id`),
  CONSTRAINT `user_items_ibfk_2` FOREIGN KEY (`ui_Item_Id`) REFERENCES `items` (`item_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_items`
--

LOCK TABLES `user_items` WRITE;
/*!40000 ALTER TABLE `user_items` DISABLE KEYS */;
INSERT INTO `user_items` VALUES (3,1),(5,1);
/*!40000 ALTER TABLE `user_items` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `user_Id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(140) DEFAULT NULL,
  `pw` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `character_Level` int(11) DEFAULT NULL,
  `Experience` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_Id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'TestUser','password',NULL,2,60),(2,'ADDINGTEST','add','no salt',1,0),(3,'nnn','nnn','no salt',17,840),(4,'aaa','aaa','no salt',1,20),(5,'iii','iii','no salt',3,100),(6,'uuu','uuu','no salt',1,0),(7,'newUser','iiii','no salt',1,0),(8,'jjj','jjjj','no salt',1,0),(9,'www','www','no salt',1,0),(10,'qqq','qqq','no salt',1,0),(11,'sss','sss','no salt',1,0),(12,'ttt','ttt','no salt',1,0),(13,'rrr','rrr','no salt',1,0),(14,'eee','eee','no salt',1,0),(15,'xxx','xxx','no salt',1,0),(16,'hhh','lll','no salt',1,0),(17,'lll','ttt','no salt',1,10),(18,'ppp','ppp','no salt',1,10);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-09-30 21:36:43
