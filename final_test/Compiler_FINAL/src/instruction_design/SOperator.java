package instruction_design;

public enum SOperator {
	MOVEA(0x01, "주소에 저장된값을 레지스터로 이동 ","movea"),
	MOVEC(0x02, "상수를 레지스터로 이동 ","movec"),
	MOVER(0x03, "레지스터에 저장된 값을 다른 레지스터로 이동 ","mover"),
	STO(0x04, "저장","sto"),
	LDA(0x05, "불러오기 ","lda"),
	JMP(0x06, "jump", "jump"),
	GTJ(0x07, "greater than jump","gtj"),
	GEJ(0x08, "greater than equal jump","gej"),
	AND(0x0A, "and","and"),
	NOT(0x0B, "not", "not"),
	HALT(0x0C, "프로그램 종료 ","halt"),
	ADDR(0x0D, "레지스터 내 값을 더하기 ","addr"),
	ADDC(0x0E, "상수 값을 더하기 ","addc"),
	SUBR(0x0F, "레지스터 내 값을 빼기 ", 	"subr"),
	SUBC(0x10, "상수 값을 빼기 ", "subc"),
	MULR(0x11, "레지스터 내 값을 곱하기 ", "mulr"),
	MULC(0x12, "상수 값을 곱하기 ", "mulc"),
	DIVR(0x13, "레지스터 내 값을 나누기 ","divr"),
	DIVC(0x14, "상수 값을 값을 나누기 ", "divc"),
	PUSH(0x15, " ", "push"),
	POP(0x16, " ", "pop")
	;
	
	private int code;
	private String description;
	private String assemblyCode;
	
	public int getCode() {
		return code;
	}
	public String getAssemblyCode() {
		return assemblyCode;
	}
	
	SOperator(int code, String description, String assemblyCode) {
		this.code = code;
		this.description = description;
		this.assemblyCode = assemblyCode;
	}
	
	public static SOperator findByAssemblyCode(String assemblyCode) {
		for(SOperator operator: SOperator.values()) {
			if(assemblyCode.equals(operator.getAssemblyCode())) {
				return operator;
			}
		}
		return null;
	}
	

}
