public class SMain {
	
	private SLex lex;
	private SParser parser;
	
	public SMain() {
		
	}
	
	public void initialize() {
		lex = new SLex();
		lex.initialize("executable/exe");
		parser = new SParser();
		parser.associate(lex);
	}
	
	public void finalize() {
		lex.finalize();
	}
	
	public void run() {
		parser.parse(); // 어떤 파일을 파싱해라
		
	}
	
	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finalize();
	}

}
