package code_generator;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import constant.Constant;
import instruction_design.SOperand;
import instruction_design.SOperator;
import parser.SStatement;
import parser.SSymbolEntity;
import parser.SSymbolTable;

public class SCodeGenerator {
	private SSymbolTable symbolTable;
	private Vector<SStatement> statements;
	private Vector<String> generationCode;
	

	public void connect(SSymbolTable symbolTable, Vector<SStatement> statements, Vector<String> generationCode) {
		this.symbolTable = symbolTable;
		this.statements = statements;
		this.generationCode = generationCode;
	}
	
	public void generate() {
		for (SStatement statement : statements) {
			String code = null;
			// operator
			SOperator operator = SOperator.findByAssemblyCode(statement.getOperator());
			code = Constant.CCodeGenerator.hexaCodeGenerate(operator.getCode());

			// operand1
			if (statement.getOperand1() != null) {
				code += translateOperand(statement.getOperand1());
			}
			if (statement.getOperand2() != null) {
				code += translateOperand(statement.getOperand2());
			}
			generationCode.add(code);
		}
	}
	

	public void createExeFile(String fileName) {
		try {
			File exeFile = new File(Constant.CCodeGenerator.EXEDirectory + fileName);
			FileWriter fw = new FileWriter(exeFile);
			
			for(String code: generationCode) {
				fw.write(code + "\n");
			}
			fw.flush();
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	
	/**
	 * extract methods
	 * @param String isAt
	 * @return String isAt
	 */
	private String deleteAt(String isAt) {
		if(isAt.contains("@")) {
			isAt = isAt.replace("@", "");
		}
		return isAt;
	}
	
	private String translateOperand(String operand) {
		// operand2
		operand = this.deleteAt(operand);
		SSymbolEntity entity = this.symbolTable.findByVariableName(operand);
		if (entity != null) {
			return Constant.CCodeGenerator.PRINT_HEXA_SIGN + Integer.toHexString(entity.getValue()) + " ";
		} else {
			SOperand assemblyCode = SOperand.findByAssemblyCode(operand);
			if (assemblyCode != null) {
				return Constant.CCodeGenerator.hexaCodeGenerate(assemblyCode.getCode());
			} else {
				return Constant.CCodeGenerator.hexaCodeGenerate(Integer.parseInt(operand));
			}

		}
		
	}

	
	
}
