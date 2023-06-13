package parser;
import java.util.Vector;

import lex.SLex;

public class SCodeSegment implements INode {
	private Vector<SStatement> statements;
	private SSymbolTable symbolTable;

	public SCodeSegment(SSymbolTable symbolTable, Vector<SStatement> statements) {
		this.statements = statements; // executable 한 문장 집합임.
		this.symbolTable = symbolTable;
	}

	@Override
	public String parse(SLex lex, Vector<String> printtoken) {
		String[] tokens = lex.getTokens();
		String operator = tokens[0];
		while (operator.compareTo(".end") != 0) {
			if ((operator.startsWith("//")) || (operator.length() == 0)) {
				// skip
			} else if (operator.contains(":")) { // code segment 주소
				// symbol table
				SSymbolEntity entity = new SSymbolEntity();
				entity.setVariableName(operator.replace(":", ""));
				entity.setValue(this.statements.size()); // pc count
				entity.setLabel("jump");
				this.symbolTable.add(entity);
			} else {
				// parse tree
				SStatement statement = null;
				switch (tokens.length) {
				case 1:
					statement = new SStatement(tokens[0], printtoken);
					break;
				case 2:
					statement = new SStatement(tokens[0], tokens[1], printtoken);
					break;
				default:
					break;
				}
				this.statements.add(statement);
			}
			tokens = lex.getTokens();
			operator = tokens[0];
		}
		System.out.println("--------------token finish--------------");
		return operator;
	}
}
