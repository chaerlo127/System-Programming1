
public class SProgram implements INode {
	private SSymbolTable symbolTable;
	private SHeader header;
	private SCodeSegment codeSegment;

	public SProgram() {
		this.header = new SHeader();
		this.codeSegment = new SCodeSegment();
		this.symbolTable = new SSymbolTable();
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