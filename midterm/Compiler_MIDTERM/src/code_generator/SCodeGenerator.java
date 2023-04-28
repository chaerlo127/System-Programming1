package code_generator;
import java.util.Vector;

import parser.SStatement;
import parser.SSymbolTable;

public class SCodeGenerator {
	private SSymbolTable symbolTable;
	private Vector<SStatement> statements;
	

	public void connect(SSymbolTable symbolTable, Vector<SStatement> statements) {
		this.symbolTable = symbolTable;
		this.statements = statements;
	}
	
	public void generate() {
		
	}
	
	
}
