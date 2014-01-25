package me.thomas.demo.activiti.web.service;

import me.thomas.demo.activiti.web.entity.User;

import java.util.Iterator;

public interface IUserService {

	User getUserByName(String name);
	
	User getUserById(long userId);
	
	User createNewUser(User newUser, User user);
	
	User updateUser(User newUser, User user);
	
	Iterator<User> findAll();
}
