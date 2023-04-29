package main;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Vector;

import javax.swing.*;

import code_generator.SCodeGenerator;
import constant.CButton;
import constant.CShowButton;
import constant.Constant;
import lex.SLex;
import parser.SParser;
import parser.SStatement;
import parser.SSymbolEntity;
import parser.SSymbolTable;

public class SUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private SSymbolTable symbolTable;
	private Vector<SStatement> statements;
	private Vector<String> generationCode;
	
	private SLex lex;
	private SParser parser;
	private SCodeGenerator codeGenerator;
	
	private Vector<File> files;
	private boolean exitButton;
	
	private JLabel list;
	
	
	public SUI() {
		this.setSize(600, 600); // 너비, 길이
		this.setLocationRelativeTo(null); // 가운데로 맞추기
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionHandler actionHandler = new ActionHandler();
		ActionInstructionHandler instructionHandler = new ActionInstructionHandler();
		
		JPanel btnPanel = new JPanel();
		for(CButton button : CButton.values()) {
			JButton btn = new JButton(button.getLabel());
			btn.setActionCommand(button.getName());
			btn.addActionListener(actionHandler);
			btnPanel.add(btn);
		}
		
		JPanel insBtnPanel = new JPanel();
		for(CShowButton button : CShowButton.values()) {
			JButton btn = new JButton(button.toString());
			btn.setActionCommand(button.toString());
			btn.addActionListener(instructionHandler);
			insBtnPanel.add(btn);
		}
		
		JPanel showPanel = new JPanel();
		showPanel.setBackground(Color.white);
		list = new JLabel();
		list.setHorizontalAlignment(JLabel.CENTER);
		showPanel.add(list);
		
		this.add(btnPanel, BorderLayout.NORTH);
		this.add(insBtnPanel, BorderLayout.SOUTH);
		this.add(showPanel, BorderLayout.CENTER);
		this.setVisible(true);
	}

	
	public void initialize() {
		// 공용 데이터
		symbolTable = new SSymbolTable();
		statements = new Vector<SStatement>();
		generationCode = new Vector<>();
		
		lex = new SLex();
		parser = new SParser();
		codeGenerator = new SCodeGenerator();
		
//		lex.initialize(new File("executable/exe"));
		this.files = new Vector<>();
		this.exitButton = true;
	}
	
	public void finalize() {
		lex.finalize();
	}
	
	public void reset() {
		// 공용 데이터
		symbolTable = new SSymbolTable();
		statements = new Vector<SStatement>();

		lex = new SLex();
		parser = new SParser();
		codeGenerator = new SCodeGenerator();
	}
	
	public void run() {
		while(this.exitButton) {
			if(!files.isEmpty()) {
				File file = files.remove(0);
				this.lex.initialize(file);
				// parser
				parser.parse(this.lex, symbolTable, statements); // 어떤 파일을 파싱해라
				this.showSymbolTable();
				System.out.println();
				this.showStatement();
				
				// code generator
				codeGenerator.connect(symbolTable, statements, generationCode);
				codeGenerator.generate();
				codeGenerator.createExeFile(file.getName());
			}
		}
		System.exit(0);
	}
	
	// symbol table 보여주기
	public String showSymbolTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		System.out.println(Constant.CSymbolTable.PRINT_SYMBOL_TABLE_SEN); // console
		sb.append("<html>");
		sb.append(Constant.CSymbolTable.PRINT_SYMBOL_TABLE_SEN).append("<br/>");
		for (SSymbolEntity entity: this.symbolTable) {
			System.out.println(entity.showSymbol()); // console
			sb.append(entity.showSymbolForUI()).append("<br/>");
		}
		sb.append("</html>");
		return sb.toString();
	}
	
	// statement 보여주기
	public String showStatement() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(Constant.CStatement.PRINT_STATEMENT_SEN).append("<br/>");
		System.out.println(Constant.CStatement.PRINT_STATEMENT_SEN); // console
		for (SStatement statement: this.statements) {
			System.out.println(statement.showStatment()); //console
			sb.append(statement.showStatmentForUI()).append("<br/>");
		}
		sb.append("</html>");
		return sb.toString();
	}
	
	// code generation 보여주기
	public String showCodeGenerationCode() {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append(Constant.CCodeGenerator.PRINT_CODEGENERATOR_SEN).append("<br/>");
		System.out.println(Constant.CStatement.PRINT_STATEMENT_SEN); // console
		for (String code: generationCode) {
			System.out.println(code); // console
			sb.append(code).append("<br/>");
		}
		sb.append("</html>");
		return sb.toString();
	}
	
	// open
	private File open() {
		JFileChooser fileChooser = new JFileChooser(new File(System.getProperty(Constant.CUI.userOpenFileDir)));
		int ret = fileChooser.showOpenDialog(this);
		if (ret == JFileChooser.APPROVE_OPTION) {
			return fileChooser.getSelectedFile();
		}
		return null;
		
	}
	
	public class ActionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			File file = null;
			if(e.getActionCommand().equals(CButton.Select.getName())) {
				file = open();
			}else if(e.getActionCommand().equals(CButton.Exe.getName())) {
				file = new File(Constant.CUI.directory + CButton.Exe.getName());
			}else if(e.getActionCommand().equals(CButton.Exe1.getName())) {
				file = new File(Constant.CUI.directory + CButton.Exe1.getName());
			}else if(e.getActionCommand().equals(CButton.Exit.getName())) {
				exitButton = false;
			}
			if(file != null) {
				files.add(file);
			}
		}
	}
	
	public class ActionInstructionHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals(CShowButton.SYMBOL_TABLE.toString())) {
				list.setText(showSymbolTable());
			}else if(e.getActionCommand().equals((CShowButton.STATEMENT.toString()))) {
				list.setText(showStatement());
			}else if(e.getActionCommand().equals((CShowButton.OBJECT_CODE.toString()))){
				list.setText(showCodeGenerationCode());
			}
		}
	}
}
