import java.util.Vector;

public class SProgram implements INode {
	private SHeader header;
	private SCodeSegment codeSegment;

	public SProgram(SSymbolTable symbolTable, Vector<SStatement> statements) {
		this.header = new SHeader(symbolTable);
		this.codeSegment = new SCodeSegment(symbolTable, statements);
	}

	@Override
	public String parse(SLex lex) {
		String token = lex.getToken();
		if (token.compareTo(".header") == 0) {
			token = this.header.parse(lex);
		}
		if (token.compareTo(".code") == 0) {
			token = this.codeSegment.parse(lex);
		}
		return null;
	}
}