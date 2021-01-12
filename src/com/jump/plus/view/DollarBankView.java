package com.jump.plus.view;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

public class DollarBankView {

	private final String HEADER_FILEPATH = "resources/header";

	private String header;
	
	public DollarBankView() {
		initHeader();
	}
	
	private void initHeader() {
		try {
			File file = new File(HEADER_FILEPATH);
			FileInputStream fis = new FileInputStream(file);
			byte[] data = new byte[(int) file.length()];
			fis.read(data);

			header = new String(data, "UTF-8");
			fis.close();
		} catch (FileNotFoundException e) {
			System.out.println("Cannot open the header file");
		} catch (IOException e) {
			System.out.println("Cannot read the header file");
		}
	}
	
	public void printHeader() {
		System.out.println(Ansi.ansi().eraseScreen().fg(Color.CYAN).a(header));
	}
	
	public void displaySelections(String[] selections) {
		IntStream.range(0, selections.length)
		  .forEach(index -> 
		  System.out.println(Ansi.ansi().eraseScreen().
				  fg(Color.WHITE).a((index + 1) + ". " + selections[index])));
		System.out.println(Ansi.ansi().eraseScreen().fg(Color.GREEN).a("\nEnter Choise: 0 ~ 3\n"));
		
	}
	
	public void displayIllegalWarning() {
		System.out.println(ansi().eraseScreen().fg(Color.RED).a("Illegal Input").reset());
	}
}
