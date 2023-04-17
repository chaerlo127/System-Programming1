import java.util.Vector;

public class SParser {
	private SLex lex;
	public void associate(SLex lex) {
		this.lex = lex;
	}
	public void parse() {
		Program program = new Program();
		program.parse();
	}
	
	
	//모든 함수에는 parse element가 있어야 함.
	private interface INode{
		public abstract String parse();
	}
	private class Program implements INode{
		private Header header;
		private CodeSegment codeSegment;
		
		public Program() {
			this.header = new Header();
			this.codeSegment = new CodeSegment();
		}
		@Override
		public String parse() {
			String token = lex.getToken();
			if(token.compareTo(".header") == 0) {
				token = this.header.parse();
			}
			if(token.compareTo(".code") == 0){
				token = this.codeSegment.parse();
			}
			return null;
		}
	}
	
	private class Header implements INode{
		private class Declaration{
			private String variableName;
			private String size;
			/**
			 * getters and setters
			 */
			public String getVariableName() {
				return variableName;
			}
			public void setVariableName(String variableName) {
				this.variableName = variableName;
			}
			public String getSize() {
				return size;
			}
			public void setSize(String size) {
				this.size = size;
			}	
		}
		
		public Header() {
			this.declaratioins = new Vector<Declaration>();
		}
		// 정의가 나옴.
		private Vector<Declaration> declaratioins; // variable을 정의한 것, Header는  variable의 집합임.
		@Override
		public String parse() {
			// lex에게 달라고 해야함. 
			String token = lex.getToken();
			while(token.compareTo(".code") != 0) {
				// symbol table
				Declaration declaration = new Declaration();
				declaration.setVariableName(token);
				declaration.setSize(lex.getToken());
				this.declaratioins.add(declaration);
				token = lex.getToken();
			}
			
			return token;
		}
	}
	
	private class CodeSegment implements INode{
		private Vector<Statement> statements;
		
		public CodeSegment() {
			this.statements = new Vector<Statement>(); // executable 한 문장 집합임.
		}
		@Override
		public String parse() {
			String[] tokens = lex.getTokens();
			String operator = tokens[0];
			while(operator.compareTo(".end") != 0) {
				
				if(!operator.startsWith("//")) {
					Statement statement = null;
					switch (tokens.length) {
						case 1:
							statement = new Statement(tokens[0]);
							break;
						case 2: 
							statement = new Statement(tokens[0], tokens[1]);
							break;
						case 3: 
							statement = new Statement(tokens[0], tokens[1], tokens[2]);
							break;
						default: 
							break;
					}
					this.statements.add(statement);
				}
				tokens = lex.getTokens();
				operator = tokens[0];
			}
			return operator;
		}
	}
	
	private class Statement implements INode{
		private String operator;
		private String operand1;
		private String operand2;
		
		public Statement(String operator) {
			this.operator = operator;
			
		}

		public Statement(String operator, String operand1) {
			this.operator = operator;
			this.operand1 = operand1;
			
		}

		public Statement(String operator, String operand1, String operand2) {
			this.operator = operator;
			this.operand1 = operand1;
			this.operand2 = operand2;
			
		}
		
		@Override
		public String parse() {
			String[] tokens = lex.getTokens(); // 한라인의 토큰을 한번에 가져옴.
			operator = tokens[0];
			if(tokens.length == 2) {
				operand1 = tokens[1];
			}
			if(tokens.length == 3) {
				operand1 = tokens[1];
				operand2 = tokens[2];
			}
			return operator;
		}
	}
	
	private class Operand { // 주소일수도 있고 constant(상수)일 수도 있음.
		boolean bAddress;
		boolean bRegister;
	}
}
