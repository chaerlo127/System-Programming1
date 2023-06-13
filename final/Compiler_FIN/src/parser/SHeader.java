package parser;
import lex.SLex;

import java.util.Vector;

public class SHeader implements INode {
	private SSymbolTable declaratioins;
	public SHeader(SSymbolTable symbolTable) {
		this.declaratioins = symbolTable;
	}

	@Override
	public String parse(SLex lex, Vector<String> tokens) {
		System.out.println("--------------token start--------------");
		// lex에게 달라고 해야함.
		String token = lex.getToken();
		while (token.compareTo(".code") != 0) {
			System.out.println(token);
			tokens.add(token);
			// symbol table
			SSymbolEntity declaration = new SSymbolEntity();
			if(token.compareTo(".heap") == 0) {
				token = lex.getToken();
				tokens.add(token);
				declaration.setLabel("heap");;
			}else {
				declaration.setLabel("header");
			}
			declaration.setVariableName(token);
			declaration.setValue(Integer.parseInt(lex.getToken()));
			this.declaratioins.add(declaration);
			token = lex.getToken();
		}
		return token;
	}
}