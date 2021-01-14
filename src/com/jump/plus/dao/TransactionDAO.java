package com.jump.plus.dao;

import java.util.List;

import com.jump.plus.model.Transaction;

public interface TransactionDAO {

	public Transaction addTransaction(Transaction transaction);
	public List<Transaction> getTransactionByAccountId(int accountId, int numberOfItem);
}
