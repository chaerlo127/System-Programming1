package parser;
import parser.INode;
import constant.Constant;
import lex.SLex;

public class SStatement implements INode {
	private String operator;
	private String operand1;
	private String operand2;
	public String getOperator() {return operator;}
	public String getOperand1() {return operand1;}
	public String getOperand2() {return operand2;}
	public SStatement(String operator) {this.operator = operator;}

	public SStatement(String operator, String operand1) {
		this.operator = operator;
		this.operand1 = operand1;
	}

	public SStatement(String operator, String operand1, String operand2) {
		this.operator = operator;
		this.operand1 = operand1;
		this.operand2 = operand2;
	}

	@Override
	public String parse(SLex lex) {
		String[] tokens = lex.getTokens(); // 한라인의 토큰을 한번에 가져옴.
		operator = tokens[0];
		if (tokens.length == 2) {
			operand1 = tokens[1];
		}
		if (tokens.length == 3) {
			operand1 = tokens[1];
			operand2 = tokens[2];
		}
		return operator;
	}
	
//	public String showStatment() {
//		if(this.operand1 == null) 
//			return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator ;
//		if(this.operand2 == null) 
//			return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator 
//					+ Constant.CStatement.PRINT_STATEMENT_OPERAND1 + this.operand1;
//		return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator 
//				+ Constant.CStatement.PRINT_STATEMENT_OPERAND1 + this.operand1
//				+ Constant.CStatement.PRINT_STATEMENT_OPERAND2 + this.operand2;
//	}
//	
//	public String showStatmentForUI() {
//		if(this.operand1 == null) 
//			return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator ;
//		if(this.operand2 == null) 
//			return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator +  Constant.TAB 
//					+ Constant.CStatement.PRINT_STATEMENT_OPERAND1 + this.operand1;
//		return Constant.CStatement.PRINT_STATEMENT_OPERATOR + this.operator + Constant.TAB 
//				+ Constant.CStatement.PRINT_STATEMENT_OPERAND1 + this.operand1 + Constant.TAB 
//				+ Constant.CStatement.PRINT_STATEMENT_OPERAND2 + this.operand2;
//	}
}