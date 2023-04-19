import java.util.Vector;

public class SCodeSegment implements INode {
	private Vector<SStatement> statements;
	private SSymbolTable symbolTable;

	public SCodeSegment(SSymbolTable symbolTable) {
		this.statements = new Vector<SStatement>(); // executable 한 문장 집합임.
		this.symbolTable = symbolTable;
	}

	@Override
	public String parse(SLex lex) {
		String[] tokens = lex.getTokens();
		String operator = tokens[0];
		while (operator.compareTo(".end") != 0) {
			if ((operator.startsWith("//")) || (operator.length() == 0)) {
				// skip
			} else if (operator.contains(":")) { // code segment 주소
				// symbol table
				// 프로그램에 만들어서 constructor를 생성
				SSymbolEntity entity = new SSymbolEntity();
				entity.setVariableName(operator.replace(":", ""));
				entity.setValue(this.statements.size()-1); // pc count
				this.symbolTable.add(entity);
			} else {
				// parse tree
				SStatement statement = null;
				switch (tokens.length) {
				case 1:
					statement = new SStatement(tokens[0]);
					break;
				case 2:
					statement = new SStatement(tokens[0], tokens[1]);
					break;
				case 3:
					statement = new SStatement(tokens[0], tokens[1], tokens[2]);
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
