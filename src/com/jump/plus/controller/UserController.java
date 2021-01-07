package com.jump.plus.controller;

import com.jump.plus.dao.UserDAO;
import com.jump.plus.dao.UserDAOImp;
import com.jump.plus.model.User;

public class UserController {

	
	private final UserDAO userDAO;
	
	private static User user;
	
	public UserController() {
		this.userDAO = new UserDAOImp();
	}
	
	public boolean login(String username, String password) {
		user = userDAO.getUserByName(username);
		if (user == null || !user.getPassword().equals(password)) 
			return false;
		return true;
	}
	
	public void logout() {
		user = null;
	}
}
