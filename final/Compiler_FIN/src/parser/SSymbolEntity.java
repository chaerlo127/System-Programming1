package parser;

import constant.Constant;

public class SSymbolEntity {
	// name, label과 같은 이름을 넣게하면 type 를 넣어야 함. ,,. 
	// type은 버려
	private String variableName; // keyword
	private int value;
	private String label;

	public String getVariableName() { return variableName;}
	public void setVariableName(String variableName) {this.variableName = variableName;}
	public int getValue() {return value;}
	public void setValue(int value) {this.value = value;}
	public void setLabel(String label) {this.label = label;}
	public String getLabel() {return this.label;}
	
	public String showSymbol() {
		return Constant.CSymbolTable.PRINT_SYMBOL_TABLE_VARIABLE_NAME + this.variableName
				+ Constant.CSymbolTable.PRINT_SYMBOL_TABLE_VALUE + this.value
				+ Constant.CSymbolTable.PRINT_SYMBOL_TABLE_LABEL + this.label;
	}

	public String showSymbolForUI() {
		return Constant.CSymbolTable.PRINT_SYMBOL_TABLE_VARIABLE_NAME + this.variableName + Constant.TAB +
				Constant.CSymbolTable.PRINT_SYMBOL_TABLE_VALUE + this.value + Constant.TAB +
				Constant.CSymbolTable.PRINT_SYMBOL_TABLE_LABEL + this.label;
	}
}
