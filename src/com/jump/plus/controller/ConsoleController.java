package com.jump.plus.controller;

import java.util.Scanner;

import com.jump.plus.view.ConsoleScanner;
import com.jump.plus.view.DollarBankView;

public class ConsoleController {

	
	private Scanner scanner = ConsoleScanner.getScanner();
	private DollarBankView dbView = new DollarBankView();

	public void startConsole() {
		dbView.printHeader();
		while (true) {
			String message = scanner.next();
			System.out.println(message);
		}
	}
}
