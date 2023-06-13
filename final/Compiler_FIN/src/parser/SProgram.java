package parser;
import java.util.Vector;

import lex.SLex;

public class SProgram implements INode {
	private SHeader header;
	private SCodeSegment codeSegment;

	public SProgram(SSymbolTable symbolTable, Vector<SStatement> statements) {
		this.header = new SHeader(symbolTable);
		this.codeSegment = new SCodeSegment(symbolTable, statements);
	}

	@Override
	public String parse(SLex lex, Vector<String> tokens) {
		String token = lex.getToken();
		if (token.compareTo(".header") == 0) {
			token = this.header.parse(lex, tokens);
		}
		if (token.compareTo(".code") == 0) {
			token = this.codeSegment.parse(lex, tokens);
		}
		return null;
	}
}