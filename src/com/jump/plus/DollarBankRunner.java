package com.jump.plus;

import com.jump.plus.dao.UserDAO;
import com.jump.plus.dao.UserDAOImp;

public class DollarBankRunner {

	public static void main(String[] args) {
		
		UserDAO userDao = new UserDAOImp();
		userDao.getUserList();
	}
}
