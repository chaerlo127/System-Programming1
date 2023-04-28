import java.util.Vector;

import cosntant.Constant;

public class SMain {
	private SSymbolTable symbolTable;
	private Vector<SStatement> statements;
	
	private SLex lex;
	private SParser parser;
	private SCodeGenerator codeGenerator;
	
	public SMain() {
	}
	
	public void initialize() {
		// 공용 데이터
		symbolTable = new SSymbolTable();
		statements = new Vector<SStatement>();
		
		lex = new SLex();
		lex.initialize("executable/exe");
		parser = new SParser();
		codeGenerator = new SCodeGenerator();
	}
	
	public void finalize() {
		lex.finalize();
	}
	
	public void run() {
		// parser
		parser.parse(this.lex, symbolTable, statements); // 어떤 파일을 파싱해라
		this.showSymbolTable();
		System.out.println();
		this.showStatement();
		
		
		// code generator
		codeGenerator.connect(symbolTable, statements);
		codeGenerator.generate();
	}
	
	public void showSymbolTable() {
		System.out.println(Constant.SymbolTable.PRINT_SYMBOL_TABLE_SEN);
		for (SSymbolEntity entity: this.symbolTable) {
			System.out.println(entity.showSymbol());
		}
	}
	public void showStatement() {
		System.out.println(Constant.Statement.PRINT_STATEMENT_SEN);
		for (SStatement statement: this.statements) {
			System.out.println(statement.showStatment());
		}
	}
	
	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finalize();
	}

}
