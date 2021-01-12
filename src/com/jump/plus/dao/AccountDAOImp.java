package com.jump.plus.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.jump.plus.connection.ConnectionManager;
import com.jump.plus.model.Account;

public class AccountDAOImp implements AccountDAO {

	Connection conn = ConnectionManager.getConnection();
	@Override
	public Account addAccount(Account account) {
		final String statement = "INSERT INTO account "
				+ "	(account_id, balance, type, user_id, create_date, modify_date) "
				+ "VALUES"
				+ " (null, ?, ?, ?, NOW(), NOW())";
		try (PreparedStatement pr = conn.prepareStatement(statement)) {
			pr.setDouble(1, account.getBalance());
			pr.setString(2, account.getType());
			pr.setInt(3, account.getUserId());
			if (pr.executeUpdate() == 1) {
				List<Account> accounts = getAccountByUserId(account.getUserId());
				return accounts.get(accounts.size() - 1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Account getAccountById(int inputAccountId) {
		final String statement = "SELECT * FROM account WHERE account_id = ?";
		try (PreparedStatement pr = conn.prepareStatement(statement)) {
			pr.setInt(1, inputAccountId);
			ResultSet rs = pr.executeQuery();
			if (rs.next()) {
				int accountId = rs.getInt("account_id");
				int userId = rs.getInt("user_id");
				double balance = rs.getDouble("balance");
				String type = rs.getString("type");
				Timestamp createTime = rs.getTimestamp("create_date");
				Timestamp modifyDate = rs.getTimestamp("modify_date");
				
				Account account = new Account(accountId, userId, balance, type, modifyDate, createTime);
				return account;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Account> getAccountByUserId(int inputUserId) {
		final String statement = "SELECT * FROM account WHERE user_id = ?";
		List<Account> accounts = new ArrayList<>();
		
		try (PreparedStatement pr = conn.prepareStatement(statement)) {
			pr.setInt(1, inputUserId);
			ResultSet rs = pr.executeQuery();
			while (rs.next()) {
				int accountId = rs.getInt("account_id");
				int userId = rs.getInt("user_id");
				double balance = rs.getDouble("balance");
				String type = rs.getString("type");
				Timestamp createTime = rs.getTimestamp("create_date");
				Timestamp modifyDate = rs.getTimestamp("modify_date");
				
				Account account = new Account(accountId, userId, balance, type, modifyDate, createTime);
				accounts.add(account);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return accounts;
	}

}
