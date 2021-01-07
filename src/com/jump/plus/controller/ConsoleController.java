package com.jump.plus.controller;

import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;

import com.jump.plus.constant.Command;
import com.jump.plus.view.ConsoleScanner;
import com.jump.plus.view.DollarBankView;

public class ConsoleController {

	
	private Scanner scanner = ConsoleScanner.getScanner();
	private DollarBankView dbView = new DollarBankView();
	public void startConsole() {
		dbView.printHeader();
		AnsiConsole.systemInstall();
		String[] inputValue = null;
		UserController userController = new UserController();
		while (true) {
			String message = scanner.next();
			System.out.println(message);
			if (message.equalsIgnoreCase(Command.EXIT.name())) {
				break ;
			} else if (message.equalsIgnoreCase(Command.LOGIN.name())) {
				inputValue = inputLogin();
				if (inputValue != null && userController.login(inputValue[0], inputValue[1])) {
					System.out.println(ansi().eraseScreen().fg(GREEN).a("User login").reset());
					inputValue = null;
				}
			} else if (message.equalsIgnoreCase(Command.LOGOUT.name())) {
				userController.logout();
				System.out.println(ansi().eraseScreen().fg(GREEN).a("User logout").reset());
			}
		}
	}
	
	public String[] inputLogin() {
		
		String[] loginValue = new String[2];
		String message = null;
		
		System.out.println("Enter BACK to go back to Main View");
		System.out.println("Enter Username: ");
		while (true) {
			if (loginValue[0] == null && message != null) {
				loginValue[0] = message;
				System.out.println("Enter Password: ");
			} else if (loginValue[1] == null) {
				loginValue[1] = message;
			} 
			if (loginValue[0] != null && loginValue[1] != null)
				return loginValue;
			message = scanner.next();
			if (message.equalsIgnoreCase(Command.BACK.name())) return null;
			
		}
	}
}
