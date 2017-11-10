# Simple Demo of Spring + SpringMVC + MyBatis
# SQL TABLE:
 #Database: users
 #create table t_user(USER_ID INT NOT NULL AUTO_INCREMENT,
     USER_NAME VARCHAR(50),
     USER_PASSWORD VARCHAR(50),
     USER_EMAIL VARCHAR(50),
     PRIMARY KEY ( `USER_ID` ));

# RESTful APIs
#
#Get user by user id
 GET http://39.106.107.244:8080/springTest/user/id/1
#
#Create user
 POST http://39.106.107.244:8080/springTest/user?name=jingzhou&password=jingzhou&email=jingzhou
#
#Update user
 PUT http://39.106.107.244:8080/springTest/user?name=jingzhou&password=jingzhou&email=jingzhou&id=3
#
#Delete user
 DELETE http://39.106.107.244:8080/springTest/user/6