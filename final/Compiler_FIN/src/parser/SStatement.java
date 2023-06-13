package parser;
import constant.Constant;
import lex.SLex;

import java.util.Vector;

public class SStatement implements INode {
	private String operator;
	private String operand;
	public String getOperator() {return operator;}
	public String getOperand() {return operand;}

	public SStatement(String operator, Vector<String> tokens) {
		this.operator = operator;
		System.out.println(operator);
		tokens.add(operator);
	}

	public SStatement(String operator, String operand, Vector<String> tokens) {
		this.operator = operator;
		this.operand = operand;
		System.out.println(operator);
		tokens.add(operator);
		System.out.println(operand);
		tokens.add(operand);
	}

	@Override
	public String parse(SLex lex, Vector<String> token) {
		String[] tokens = lex.getTokens(); // 한라인의 토큰을 한번에 가져옴.
		
		operator = tokens[0];
		if (tokens.length == 2) {
			operand = tokens[1];
		}
		return operator;
	}
	
	public String showStatment() {
		if(this.operand == null)
			return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator ;
		return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator
				+ Constant.CStatement.PRINT_STATEMENT_OPERAND + this.operand;
	}

	public String showStatmentForUI() {
		if(this.operand == null)
			return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator ;
		return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator + Constant.TAB
				+ Constant.CStatement.PRINT_STATEMENT_OPERAND + this.operand + Constant.TAB;
	}
}