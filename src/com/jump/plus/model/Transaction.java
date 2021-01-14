package com.jump.plus.model;

import java.sql.Timestamp;

public class Transaction {

	private int id;
	private double amount;
	private int userId;
	private int accountId;
	private String type;
	private Timestamp createDate;

	public Transaction(int id, double amount, int userId, int accountId, String type, Timestamp create_date) {
		super();
		this.id = id;
		this.amount = amount;
		this.userId = userId;
		this.accountId = accountId;
		this.type = type;
		this.createDate = create_date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp create_date) {
		this.createDate = create_date;
	}

	public int getId() {
		return id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", amount=" + amount + ", userId=" + userId + ", accountId=" + accountId
				+ ", type=" + type + ", create_date=" + createDate + "]";
	}

}
