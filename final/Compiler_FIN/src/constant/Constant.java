package constant;

public class Constant {
	public final static String TAB = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
	public class CSymbolTable{
		public final static String PRINT_SYMBOL_TABLE_SEN = "------------------------ [SYMBOL TABLE] ------------------------";
		public final static String PRINT_SYMBOL_TABLE_VARIABLE_NAME = "VARIABLE NAME : ";
		public final static String PRINT_SYMBOL_TABLE_VALUE = "\t\t VALUE: ";
		public final static String PRINT_SYMBOL_TABLE_LABEL = "\t\t LABEL: ";

	}
	
	public class CStatement{
		public final static String PRINT_STATEMENT_SEN = "------------------------ [STATEMENT CODE] ------------------------";
		public final static String PRINT_STATEMENT_OPERATOR = "OPERATOR : ";
		public final static String PRINT_STATEMENT_OPERAND = "\t\t OPERAND: ";
	}
	
	public class CCodeGenerator{
		public final static String PRINT_CODEGENERATOR_SEN = "------------------------ [OBJECT CODE] ------------------------";
		public final static String PRINT_HEXA_SIGN = "0x";
		public static final String EXEDirectory = "exe" + "/";
		
		public static String hexaCodeGenerate(int decimalNum) {
			return Constant.CCodeGenerator.PRINT_HEXA_SIGN + Integer.toHexString(decimalNum) + " ";
		}
	}

	public class CToken{
		public final static String PRINT_TOKEN_SEN = "------------------------ [TOKEN] ------------------------";
	}
	
	public class CUI{
		public static final String userOpenFileDir = "user.dir";
		public static final String directory = "assembly" + "/";
	}

}
