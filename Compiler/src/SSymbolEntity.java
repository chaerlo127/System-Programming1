
public class SSymbolEntity {
	// name, label과 같은 이름을 넣게하면 type 를 넣어야 함. ,,. 
	// type은 버려
	private String variableName; // keyword
	private int value;

	public String getVariableName() { return variableName;}
	public void setVariableName(String variableName) {this.variableName = variableName;}
	public int getValue() {return value;}
	public void setValue(int value) {this.value = value;}
}
