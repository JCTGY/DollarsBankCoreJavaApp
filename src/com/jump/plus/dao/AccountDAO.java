package com.jump.plus.dao;

import java.util.List;

import com.jump.plus.model.Account;

public interface AccountDAO {

	public Account addAccount(Account account);
	public Account getAccountById(int accountId);
	public Account updateAccount(Account account);
	public List<Account> getAccountByUserId(int userId);
}
