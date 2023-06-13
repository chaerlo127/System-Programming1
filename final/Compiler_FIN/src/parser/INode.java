package parser;
import lex.SLex;

import java.util.Vector;

public interface INode{
	public String parse(SLex lex, Vector<String> tokens);
}