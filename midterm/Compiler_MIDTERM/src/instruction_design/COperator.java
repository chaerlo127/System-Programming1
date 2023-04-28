package instruction_design;

public enum COperator {
	MOVEA(0x01, "주소에 저장된값을 레지스터로 이동 "),
	MOVEC(0x02, "상수를 레지스터로 이동 "),
	MOVER(0x03, "레지스터에 저장된 값을 다른 레지스터로 이동 "),
	STO(0x04, "저장"),
	LDA(0x05, "불러오기 "),
	JMP(0x06, "jump"),
	JTJ(0x07, "greater than jump"),
	GEJ(0x08, "greater than equal jump"),
	AND(0x0A, "and"),
	NOT(0x0B, "not"),
	HALT(0x0C, "프로그램 종료 "),
	ADDR(0x0D, "레지스터 내 값을 더하기 "),
	ADDC(0x0E, "상수 값을 더하기 "),
	SUBR(0x0F, "레지스터 내 값을 빼기 "),
	SUBC(0x10, "상수 값을 빼기 "),
	MULA(0x11, "레지스터 내 값을 곱하기 "),
	MULC(0x12, "상수 값을 곱하기 "),
	DIVA(0x13, "레지스터 내 값을 나누기 "),
	DIVC(0x14, "상수 값을 값을 나누기 ")
	;
	
	private int code;
	private String description;
	
	
	
	COperator(int code, String description) {
		this.code = code;
		this.description = description;
	}
	

}
