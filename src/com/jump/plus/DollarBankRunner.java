package com.jump.plus;

import com.jump.plus.controller.ConsoleController;

import java.io.IOException;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.gui2.*;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class DollarBankRunner {

	public static void main(String[] args) {
		System.setProperty("jansi.passthrough", "true");
		ConsoleController consoleController = new ConsoleController();
		consoleController.startConsole();
	}
	
}
