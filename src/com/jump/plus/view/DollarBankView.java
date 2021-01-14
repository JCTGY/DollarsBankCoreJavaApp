package com.jump.plus.view;

import static org.fusesource.jansi.Ansi.ansi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.Ansi.Color;

import com.jump.plus.model.Account;
import com.jump.plus.model.User;

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
		System.out.println(Ansi.ansi().eraseScreen().
				fg(Color.GREEN).a("\nEnter Choise: 1 ~ " + selections.length + "\n"));
	}
	
	public void displayUserDetail(User user) {
		displayTitle("Hello " + user.getFirstName() + "!");
		
	}
	
	public void displayAccounstList(List<Account> accounts) {
		if (accounts.size() == 0) {
			System.out.println(Ansi.ansi().eraseScreen().
				fg(Color.MAGENTA).a("Account List is Empty"));
			return ;
		} else {
			displayTitle("Account List");
			String[] selections = accounts.stream().map(a -> {
				return "Account Id: " + a.getId() + " Type: " + a.getType();
			}).toArray(String[]::new);
			displaySelections(selections);
		}
	}
	
	public void displayTitle(String title) {
		StringBuilder sb = new StringBuilder();
		sb.append("+");
		IntStream.range(0, title.length()).forEachOrdered(index-> sb.append("-"));
		sb.append("------+\n");
		sb.append("|   " + title + "   |\n");
		sb.append("+");
		IntStream.range(0, title.length()).forEachOrdered(index-> sb.append("-"));
		sb.append("------+\n");
		System.out.println(Ansi.ansi().eraseScreen().
				fg(Color.MAGENTA).a(sb.toString()));
	}
	
 public void diaplayTransactionTable(Map<String, String> infos) {

		final int length = infos.get("Date").length() + 10;
		StringBuilder sb = new StringBuilder();
		sb.append("+");
		IntStream.range(0, length).forEachOrdered(index-> sb.append("-"));
		sb.append("------+\n");
		infos.forEach((title, content) -> {
			int numberOfSpace = (length + 4 - title.length() - content.length()) / 2;
			sb.append("|");
			IntStream.range(0, numberOfSpace).forEachOrdered(index-> sb.append(" "));
			if (title.equals("Type")) {
				sb.append("   ");
				sb.append(content);
				sb.append("   ");
			} else {
				sb.append(title + ": " + content);				
			}
			IntStream.range(0, numberOfSpace).forEachOrdered(index-> sb.append(" "));
			if (numberOfSpace * 2 < (length + 4 - title.length() - content.length()))
				sb.append(" ");
			sb.append("|\n");
		});
		sb.append("+");
		IntStream.range(0, length).forEachOrdered(index-> sb.append("-"));
		sb.append("------+");
		System.out.println(Ansi.ansi().eraseScreen().
				fg(Color.MAGENTA).a(sb.toString()));
	}
	
	public void displayIllegalWarning() {
		System.out.println(ansi().eraseScreen().fg(Color.RED).a("Illegal Input").reset());
	}
}
