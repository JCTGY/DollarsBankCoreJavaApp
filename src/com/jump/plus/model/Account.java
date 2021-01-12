package com.jump.plus.model;

import java.sql.Timestamp;

public class Account {

	private int id;
	private int userId;
	private double balance;
	private String type;
	private Timestamp modifyDate;
	private Timestamp createDate;

	public Account(int id, int userId, double balance, String type, Timestamp modifyDate, Timestamp createDate) {
		super();
		this.id = id;
		this.userId = userId;
		this.balance = balance;
		this.type = type;
		this.modifyDate = modifyDate;
		this.createDate = createDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public int getId() {
		return id;
	}

	public int getUserId() {
		return userId;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", userId=" + userId + ", balance=" + balance + ", type=" + type + ", modifyDate="
				+ modifyDate + ", createDate=" + createDate + "]";
	}

}
