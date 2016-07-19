package com.my.eventsdemo.controllers;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public @ResponseBody User register(@RequestBody UserRegister user) throws NoSuchAlgorithmException {
		logger.info("registering" + user.getName());
		
		
		
		MessageDigest messageDigest = MessageDigest.getInstance("MD5");  
		messageDigest.update(user.getPassword().getBytes(),0, user.getPassword().length());  
		String hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);  
		if (hashedPass.length() < 32) {
		   hashedPass = "0" + hashedPass; 
		}
		
		User newUser=new User();
		newUser.setUsername(user.getUsername());
		newUser.setPassword(hashedPass);
		newUser.setStatus(UserStatus.ACTIVE);
		newUser.setRole("User");
		userDao.addUser(newUser);
	      
	      
		return newUser;
	}
	

}
