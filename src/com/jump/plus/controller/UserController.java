package com.jump.plus.controller;

import java.util.Map;

import com.jump.plus.constant.CreateNewUserMenu;
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
	
	public void createNewUser(Map<String, String> userInfo) {
		
		if (userInfo.size() != CreateNewUserMenu.selections.length)
			return ;
		User newUser = new User(0, userInfo.get(CreateNewUserMenu.USERNAME), 
				userInfo.get(CreateNewUserMenu.PASSWORD), userInfo.get(CreateNewUserMenu.FISRNAME), 
				userInfo.get(CreateNewUserMenu.LASTNAME), userInfo.get(CreateNewUserMenu.EMAIL), null, null);
		user = userDAO.addNewUser(newUser);
	}
}
