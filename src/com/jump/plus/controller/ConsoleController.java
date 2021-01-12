package com.jump.plus.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;

import com.jump.plus.constant.CreateNewUserMenu;
import com.jump.plus.constant.MainMenu;
import com.jump.plus.view.ConsoleScanner;
import com.jump.plus.view.DollarBankView;

public class ConsoleController {

	
	private Scanner scanner = ConsoleScanner.getScanner();
	private DollarBankView dbView = new DollarBankView();
	public void startConsole() {
		dbView.printHeader();
		while (true) {
			startMenuSelections();
		}
	}
	
	private void startMenuSelections() {
		AnsiConsole.systemInstall();

		dbView.displaySelections(MainMenu.selections);
		String[] inputValue = null;
		UserController userController = new UserController();
		try {
			int message = Integer.parseInt(scanner.next());
			if (message >= 0 && message < MainMenu.selections.length) {
				if (message == MainMenu.EXIT) {
					System.exit(0);;
				} else if (message == MainMenu.LOGIN) {
					inputValue = inputLogin();
					if (inputValue != null && userController.login(inputValue[0], inputValue[1])) {
						System.out.println(ansi().eraseScreen().fg(Color.MAGENTA).a("User login").reset());
						inputValue = null;
					}
				} else if (message == MainMenu.LOGOUT) {
					userController.logout();
					System.out.println(ansi().eraseScreen().fg(Color.MAGENTA).a("User logout").reset());
				} else if (message == MainMenu.CREATE_NEW_USER) {
					userController.createNewUser(inputUserInfos());
				}
			} else dbView.displayIllegalWarning();
		} catch (NumberFormatException e) {
			dbView.displayIllegalWarning();
		}
	}
	
	/*
	 *  Helper Method to collect username/password for login
	 */
	private String[] inputLogin() {
		
		String[] loginValue = new String[2];
		String message = null;
		
		System.out.println(ansi().eraseScreen().fg(Color.WHITE).a("Enter BACK to go back to Main View").reset());
		System.out.println(ansi().eraseScreen().fg(Color.GREEN).a("Enter Username: ").reset());
		while (true) {
			if (loginValue[0] == null && message != null) {
				loginValue[0] = message;
				System.out.println(ansi().eraseScreen().fg(Color.GREEN).a("Enter Password: ").reset());
			} else if (loginValue[1] == null) {
				loginValue[1] = message;
			} 
			if (loginValue[0] != null && loginValue[1] != null)
				return loginValue;
			message = scanner.next();
			if (message.equalsIgnoreCase(MainMenu.BACK)) return null;
			
		}
	}
	
	private Map<String, String> inputUserInfos() {
		Map<String, String> userInfo = new HashMap<>();
		for (String s : CreateNewUserMenu.selections) {
			System.out.println(ansi().eraseScreen().fg(Color.GREEN).a("Enter " + s + ": ").reset());
			String message = scanner.next();
			userInfo.put(s, message);
		}
		return userInfo;
	}
}
