package main;
import java.util.Vector;

import code_generator.SCodeGenerator;
import constant.Constant;
import lex.SLex;
import parser.SParser;
import parser.SStatement;
import parser.SSymbolEntity;
import parser.SSymbolTable;

public class SMain {
	private SUI ui;
	
	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finalize();
	}

	private void run() {
		ui.run();
	}

	private void initialize() {
		ui = new SUI();
		ui.initialize();
		
	}
	public void finalize() {
		ui.finalize();
	}

}
