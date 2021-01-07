package com.jump.plus.constant;

public enum Command {
	
	EXIT("exit"),
	LOGIN("login"),
	LOGOUT("logout"),
	BACK("back");

	private String command;
	
	Command(String command) {
		this.command = command;
	}

	public String getCommand() {
		return command;
	}
}
