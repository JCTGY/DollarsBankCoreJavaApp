package com.jump.plus.controller;

import java.util.List;

import com.jump.plus.dao.AccountDAO;
import com.jump.plus.dao.AccountDAOImp;
import com.jump.plus.model.Account;

public class AccountController {

	private AccountDAO accountDAO = new AccountDAOImp();
	
	public Account createNewAccount(String accountType, int userId) {
		Account account = new Account(0, userId, 0, accountType, null, null);
		return accountDAO.addAccount(account);
	}
	
	public List<Account> getAccountsByUserId(int userId) {
		return accountDAO.getAccountByUserId(userId);
	}
}
