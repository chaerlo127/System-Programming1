package main;

import java.io.File;
import java.util.Vector;

import code_generator.SCodeGenerator;
import lex.SLex;
import parser.SParser;
import parser.SStatement;
import parser.SSymbolEntity;
import parser.SSymbolTable;

public class SMain {

	private SSymbolTable symbolTable;
	private Vector<SStatement> statements;
	private SCodeGenerator codeGenerator;
	private Vector<String> generationCode;
	
	private SLex lex;
	private SParser parser;
	
//	private Vector<File> files;
	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finalize();
	}

	private void run() {
		this.lex.initialize(new File("assembly/exe"));
		parser.parse(this.lex, symbolTable, statements); // 어떤 파일을 파싱해라
		codeGenerator.connect(symbolTable, statements, generationCode);
		codeGenerator.generate();
		codeGenerator.createExeFile("exe");
	}

	private void initialize() {
		// 공용 데이터
		symbolTable = new SSymbolTable();
		statements = new Vector<SStatement>();
		generationCode = new Vector<>();
		lex = new SLex();
		parser = new SParser();
		codeGenerator = new SCodeGenerator();  

//		this.files = new Vector<>();
	}
	
	public void finalize() {
		System.out.printf("%-15s %-15s %-15s","NAME", "VALUE", "LABEL");
		System.out.println();
		System.out.println("----------------------------------------");
		for (SSymbolEntity se: this.symbolTable) {
			System.out.printf("%-15s %-15s %-15s", se.getVariableName(), se.getValue(), se.getLabel());
			System.out.println();
		}
	}
}
