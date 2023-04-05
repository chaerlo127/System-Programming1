import java.util.Scanner;

public class SParser {
	private SLex lex;
	public void associate(SLex lex) {
		this.lex = lex;
	}
	public void parse(Scanner scanner) {
		String token = lex.getToken(scanner);
		// 키워드를 만들어 함. 
		while(!token.contentEquals("end")) { // 끝이 아닌 경우에 계속 돌아라
			// 문법이 여기서 만들짐.
			
			token = lex.getToken(scanner);
		
			
		}
		
		
	}

}
