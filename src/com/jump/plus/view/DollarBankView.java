package com.jump.plus.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
		System.out.println(header);
	}
}
