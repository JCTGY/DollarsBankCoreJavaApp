package com.jump.plus.view;

import java.util.Scanner;

public class ConsoleScanner {

	public static Scanner scanner;

	private static void makeScanner() {
		scanner = new Scanner(System.in);
	}
	
	public static Scanner getScanner() {
		if (scanner == null) makeScanner();
		return scanner;
	}
}
