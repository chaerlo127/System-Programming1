package parser;
import java.util.Vector;

import lex.SLex;

public class SParser {
	public void parse(SLex lex, SSymbolTable symbolTable, Vector<SStatement> statements, Vector<String> tokens) {
		SProgram program = new SProgram(symbolTable, statements);
		program.parse(lex, tokens);
	}
}
