package com.jump.plus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jump.plus.connection.ConnectionManager;
import com.jump.plus.model.Transaction;

public class TransactionDAOImp implements TransactionDAO {

	Connection conn = ConnectionManager.getConnection();
	
	@Override
	public List<Transaction> getTransactionByAccountId(int inpurtAccountId, int numberOfItem) {
		final String statement = "SELECT * FROM transaction "
				+ "WHERE account_id = ? "
				+ "ORDER BY create_date DESC "
				+ "LIMIT ?";
		List<Transaction> transactions = new ArrayList<>();
		
		try (PreparedStatement pr = conn.prepareStatement(statement)) {
			pr.setInt(1, inpurtAccountId);
			pr.setInt(2, numberOfItem);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("transaction_id");
				int userId = rs.getInt("user_id");
				int accountId = rs.getInt("account_id");
				double amount = rs.getDouble("amount");
				String type = rs.getString("type");
				Timestamp createDate = rs.getTimestamp("create_date");
				
				Transaction transaction = new Transaction(id, amount, userId, accountId, type, createDate);
				transactions.add(transaction);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return transactions;
	}

	@Override
	public Transaction addTransaction(Transaction transaction) {
		final String statement = "INSERT INTO transaction "
				+ " (transaction_id, amount, type, user_id, account_id, create_date) "
				+ " VALUES "
				+ "(null, ?, ?, ?, ?, NOW())";
		
		try (PreparedStatement pr = conn.prepareStatement(statement)) {
			pr.setDouble(1, transaction.getAmount());
			pr.setString(2, transaction.getType());
			pr.setInt(3, transaction.getUserId());
			pr.setInt(4, transaction.getAccountId());
			if (pr.executeUpdate() == 1) {
				List<Transaction> transactions = getTransactionByAccountId(transaction.getAccountId(), 1);
				return transactions.get(0);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
