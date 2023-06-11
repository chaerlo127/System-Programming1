package lex;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SLex {
	private Scanner scanner;
	
	public SLex() {
	}
	
	public void initialize(File file) {
		try {
			scanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		scanner.close();
	}
	
	public String getToken() {
		if(scanner.hasNext()) {
			return scanner.next();
		}
		return null;
	}

	public String[] getTokens() {
		if(scanner.hasNext()) {
			String line = scanner.nextLine();
			line = line.trim();
			String[] tokens = line.split("[ \t]+");
			return tokens; // regular expression
		}
		return null;
	}
}
