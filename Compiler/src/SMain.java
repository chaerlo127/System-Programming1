import java.util.Scanner;

public class SMain {
	
	private SLex lex;
	private SParser parser;
	private Scanner scanner;
	
	public SMain() {
		lex = new SLex();
		parser = new SParser();
	}
	
	public void initialize() {
		scanner = new Scanner("resource/exe1");
	}
	public void finalize() {
		scanner.close();
	}
	public void run() {
		parser.parse(scanner); // 어떤 파일을 파싱해라
		
	}
	public static void main(String[] args) {
		SMain main = new SMain();
		main.run();
	}

}
