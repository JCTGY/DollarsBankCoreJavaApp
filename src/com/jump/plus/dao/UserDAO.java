package com.jump.plus.dao;

import java.util.List;

import com.jump.plus.model.User;

public interface UserDAO {

	public List<User> getUserList();
	public User getUserByName(String username);
}
