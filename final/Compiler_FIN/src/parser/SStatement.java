package parser;
import lex.SLex;

public class SStatement implements INode {
	private String operator;
	private String operand;
	public String getOperator() {return operator;}
	public String getOperand() {return operand;}

	public SStatement(String operator) {
		this.operator = operator;
		System.out.println(operator);
	}

	public SStatement(String operator, String operand) {
		this.operator = operator;
		this.operand = operand;
		System.out.println(operator);
		System.out.println(operand);
	}

	@Override
	public String parse(SLex lex) {
		String[] tokens = lex.getTokens(); // 한라인의 토큰을 한번에 가져옴.
		
		operator = tokens[0];
		if (tokens.length == 2) {
			operand = tokens[1];
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