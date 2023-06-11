package parser;
import lex.SLex;

public interface INode{
	public String parse(SLex lex);
}