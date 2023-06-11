package parser;
import lex.SLex;

public class SHeader implements INode {
	private SSymbolTable declaratioins;
	

	public SHeader(SSymbolTable symbolTable) {
		this.declaratioins = symbolTable;
	}

	@Override
	public String parse(SLex lex) {
		// lex에게 달라고 해야함.
		String token = lex.getToken();
		while (token.compareTo(".code") != 0) {
			// symbol table
			SSymbolEntity declaration = new SSymbolEntity();
			if(token.compareTo(".heap") == 0) {
				token = lex.getToken();
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