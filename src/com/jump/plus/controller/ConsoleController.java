package com.jump.plus.controller;

import java.io.Console;
import java.util.Scanner;

import com.jump.plus.constant.Command;
import com.jump.plus.view.ConsoleScanner;
import com.jump.plus.view.DollarBankView;
import com.mysql.cj.protocol.Message;

public class ConsoleController {

	
	private Scanner scanner = ConsoleScanner.getScanner();
	private DollarBankView dbView = new DollarBankView();

	public void startConsole() {
		dbView.printHeader();
		
		UserController userController = new UserController();
		while (true) {
			String message = scanner.next();
			System.out.println(message);
			if (message.equalsIgnoreCase(Command.EXIT.name())) {
				break ;
			} else if (message.equalsIgnoreCase(Command.LOGIN.name())) {
				String[] inputValue = inputLogin();
				if (inputValue != null && userController.login(inputValue[0], inputValue[1])) {
					System.out.println("User login");
				}
			} else if (message.equalsIgnoreCase(Command.LOGOUT.name())) {
				
			}
		}
	}
	
	public String[] inputLogin() {
		
		String[] loginValue = new String[2];
		String message = null;
		
		System.out.println("Enter BACK to go back to Main View");
		System.out.println("Enter Username: \n");
		while (true) {
			if (loginValue[0] == null && message != null) {
				loginValue[0] = message;
				System.out.println("Enter Password: \n");
			} else if (loginValue[1] == null) {
				loginValue[1] = message;
			} else return loginValue;
			
			message = scanner.next();
			if (message.equalsIgnoreCase(Command.BACK.name())) return null;
			
		}
	}
}
