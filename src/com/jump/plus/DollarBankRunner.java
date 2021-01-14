package com.jump.plus;

import com.jump.plus.controller.ConsoleController;


public class DollarBankRunner {

	public static void main(String[] args) {
		System.setProperty("jansi.passthrough", "true");
		ConsoleController consoleController = new ConsoleController();
		consoleController.startConsole();
	}
}
