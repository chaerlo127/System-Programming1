import java.util.Vector;

public class SParser {
	public void parse(SLex lex) {
		SProgram program = new SProgram();
		program.parse(lex);
	}
}
