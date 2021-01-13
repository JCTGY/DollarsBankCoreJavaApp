package com.jump.plus.controller;

import static org.fusesource.jansi.Ansi.ansi;

import java.util.List;

import org.fusesource.jansi.Ansi.Color;

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
	
	public Account getAccountById(int accountId) {
		return accountDAO.getAccountById(accountId);
	}
	
	public Account deposit(Account account, double amount) {
		account.setBalance(amount + account.getBalance());
		Account updateAccount = accountDAO.updateAccount(account);
		if (updateAccount == null) {
			System.out.println(
					ansi().eraseScreen().fg(Color.RED).a("Cannot update account balance").reset());
			return account;
		}
		System.out.println(
				ansi().eraseScreen().fg(Color.GREEN).a("Current Balance: " + account.getBalance()).reset());
		return updateAccount;
	}
	
	public Account withdraw(Account account, double amount) {
		if (account.getBalance() < amount) {
			System.out.println(
					ansi().eraseScreen().fg(Color.RED).a("Not enougth balance").reset());
			return account;
		}
		account.setBalance(account.getBalance() - amount);
		Account updateAccount = accountDAO.updateAccount(account);
		if (updateAccount == null) {
			System.out.println(
					ansi().eraseScreen().fg(Color.RED).a("Cannot update account balance").reset());
			return account;
		}
		System.out.println(
				ansi().eraseScreen().fg(Color.GREEN).a("Current Balance: " + account.getBalance()).reset());
		return updateAccount;
	}
	
	public Account transerFunds(Account account, double amount, int transferAccountId) {
		if (account.getBalance() < amount) {
			System.out.println(
					ansi().eraseScreen().fg(Color.RED).a("Not enougth balance").reset());
			return account;
		}
		Account transferAccount = getAccountById(transferAccountId);
		if (transferAccount == null) {
			System.out.println(
					ansi().eraseScreen().fg(Color.RED).a("Cannot found Account: " + transferAccountId).reset());
			return account;
		}
		transferAccount.setBalance(transferAccount.getBalance() + amount);
		account.setBalance(account.getBalance() - amount);
		if (accountDAO.updateAccount(transferAccount) == null) {
			System.out.println(
					ansi().eraseScreen().fg(Color.RED)
					.a("Cannot transfer balance at Account: " + transferAccountId).reset());
			return account;
		}
		Account updateAccount = accountDAO.updateAccount(account);
		if (updateAccount == null) {
			System.out.println(
					ansi().eraseScreen().fg(Color.RED).a("Cannot update account balance").reset());
			return account;
		}
		System.out.println(
				ansi().eraseScreen().fg(Color.GREEN).a("Current Balance: " + account.getBalance()).reset());
		return updateAccount;
	}
}
