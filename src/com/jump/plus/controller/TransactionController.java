package com.jump.plus.controller;

import java.util.List;

import com.jump.plus.dao.TransactionDAO;
import com.jump.plus.dao.TransactionDAOImp;
import com.jump.plus.model.Transaction;

public class TransactionController {

	private TransactionDAO transactionDAO = new TransactionDAOImp();
	
	public Transaction createNewTransaction(int accountId, int userId, double amount, String type) {
		Transaction transaction = new Transaction(0, amount, userId, accountId, type, null);
		return transactionDAO.addTransaction(transaction);
	}
	
	public List<Transaction> getTransactionByAccountId(int accountId) {
		final int numberOfItems = 5;
		return transactionDAO.getTransactionByAccountId(accountId, numberOfItems);
	}
}
