import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SLex {
	// file을 읽어서 token 을 전달해 줌.
	private Scanner scanner;
	
	public SLex() {
	}
	
	public void initialize(String fileName) {
		try {
			scanner = new Scanner(new File(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void finalize() {
		scanner.close();
	}
	
	// scanner에서는 lexical analyzer를 갖고 있음. 
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
