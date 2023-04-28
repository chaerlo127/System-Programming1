package constant;

public class Constant {
	public class CSymbolTable{
		public final static String PRINT_SYMBOL_TABLE_SEN = "------------------------ [SYMBOL TABLE] ------------------------";
		public final static String PRINT_SYMBOL_TABLE_VARIABLE_NAME = "VARIABLE NAME : ";
		public final static String PRINT_SYMBOL_TABLE_VALUE = "\t\t VALUE: ";
		
	}
	
	public class CStatement{
		public final static String PRINT_STATEMENT_SEN = "------------------------ [STATEMENT CODE] ------------------------";
		public final static String PRINT_STATEMENT_OPERATOR = "OPERATOR : ";
		public final static String PRINT_STATEMENT_OPERAND1 = "\t\t OPERAND1: ";
		public final static String PRINT_STATEMENT_OPERAND2 = "\t\t OPERAND2: ";
	}
	
	public class CCodeGenerator{
		public final static String PRINT_CODEGENERATOR_SEN = "------------------------ [OBJECT CODE] ------------------------";
		public final static String PRINT_HEXA_SIGN = "0x";
		
		
		public static String HexaCodeGenerate(int decimalNum) {
			return Constant.CCodeGenerator.PRINT_HEXA_SIGN + Integer.toHexString(decimalNum) + " ";
		}
			

		
	}

}
