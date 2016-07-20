package com.my.eventsdemo.controllers;

import java.math.BigInteger;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.eventsdemo.dao.UserDao;
import com.my.eventsdemo.entity.User;
import com.my.eventsdemo.entity.UserStatus;
import com.my.eventsdemo.model.UserRegister;

@Controller
public class RegisterController {
	@Autowired
	private UserDao userDao;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/api/register", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody ResponseEntity<User> register(@RequestBody UserRegister user) throws NoSuchAlgorithmException {
		logger.info("registering" + user.getName());
		User usr=null;
		String response="";
		boolean t=false;
		List<User> users=userDao.getAllUsers();
		for (User u : users) {
			if(u.getUsername().equals(user.getUsername())){
				t=true;
				response="Error: username allready exists!";
				break;				
			}else if(u.getMail().equals(user.getEmail())){
				t=true;
				response="Error: email allready in user!";
				break;	
			}
			
		}
		
		
		if(!t){
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
		messageDigest.update(user.getPassword().getBytes(),0, user.getPassword().length());  
		String hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);  
		if (hashedPass.length() < 32) {
		   hashedPass = "0" + hashedPass; 
		}
		
		User newUser=new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(hashedPass);
		newUser.setMail(user.getEmail());
		newUser.setName(user.getName());
		newUser.setStatus(UserStatus.INACTIVE);
		newUser.setRole("User");
		userDao.addUser(newUser);
		
		usr = userDao.findUserByName(user.getUsername());
	      
		 ApplicationContext context = new FileSystemXmlApplicationContext("src/main/webapp/WEB-INF/spring/appServlet/buisness-config.xml");
         
	        //Get the mailer instance
	        com.my.eventsdemo.util.SendMail mailer = (com.my.eventsdemo.util.SendMail) context.getBean("sendMail");
	 
	        //Send a composed mail
	        mailer.sendMail("uddmejl1@gmail.com", usr.getMail(), "Account activation", " Hello "+ usr.getName() + " .You can activate your account by clicking the following link:"+
	        		"http://localhost:8080/eventsdemo/api/activate/"+usr.getId()	        		
	        		);
		
		response="Account created, activation email has been sent!";
		
		}else{
			return new ResponseEntity<User>(usr, HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<User>(usr, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/activate/{accountId}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Object> SendMail( @PathVariable int accountId) throws URISyntaxException {
		
		User u = userDao.findUser(accountId);
		u.setStatus(UserStatus.ACTIVE);
		userDao.editUser(u);
		
		 URI uri = new URI("http://localhost:8080/eventsdemo/#/activated");
		    HttpHeaders httpHeaders = new HttpHeaders();
		    httpHeaders.setLocation(uri);
	      
	      
		return new ResponseEntity<Object>(httpHeaders, HttpStatus.SEE_OTHER);
	}
}
