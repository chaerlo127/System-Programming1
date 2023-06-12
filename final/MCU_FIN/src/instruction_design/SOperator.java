package instruction_design;

public enum SOperator {
	LOADA(0x01, "주소에 있는 값 불러오기 ","loada"),
	LOADC(0x02, "상수 불러오기 ","loadc"),
	JMP(0x03, "jump", "jump"),
	GTJ(0x04, "greater than jump","gtj"),
	GEJ(0x05, "greater than equal jump","gej"),
	AND(0x06, "and","and"),
	NOT(0x07, "not", "not"),
	HALT(0x08, "프로그램 종료 ","halt"),
	ADDA(0x09, "레지스터 내 값을 더하기 ","adda"),
	ADDC(0x0A, "상수 값을 더하기 ","addc"),
	SUBA(0x0B, "레지스터 내 값을 빼기 ", 	"suba"),
	SUBC(0x0C, "상수 값을 빼기 ", "subc"),
	MULA(0x0D, "레지스터 내 값을 곱하기 ", "mula"),
	MULC(0x0E, "상수 값을 곱하기 ", "mulc"),
	DIVA(0x0F, "레지스터 내 값을 나누기 ","diva"),
	DIVC(0x10, "상수 값을 값을 나누기 ", "divc"),
	HPUSH(0x11, "Heap segment push", "hpush"),
	HPOP(0x12, "Heap segemnt pop", "hpop"),
	HLOADA(0x13, "주소에 있는 값 불러오기 ","hloada"),
	HLOADC(0x14, "상수 불러오기 ","hloadc"),
	HADDA(0x15, "레지스터 내 값을 더하기 ","hadda"),
	HADDC(0x16, "상수 값을 더하기 ","haddc"),
	HSUBA(0x17, "레지스터 내 값을 빼기 ", "hsuba"),
	HSUBC(0x18, "상수 값을 빼기 ", "hsubc"),
	HMULA(0x19, "레지스터 내 값을 곱하기 ", "hmula"),
	HMULC(0x1A, "상수 값을 곱하기 ", "hmulc"),
	HDIVA(0x1B, "레지스터 내 값을 나누기 ","hdiva"),
	HDIVC(0x1C, "상수 값을 값을 나누기 ", "hdivc"),
	HJMP(0x1D, "jump", "hjump"),
	SPUSH(0x1E, "Stack segment push", "spush"),
	SPOP(0x1F, "Stack segemnt pop", "spop"),
	SLOADA(0x20, "주소에 있는 값 불러오기 ","sloada"),
	SLOADC(0x21, "상수 불러오기 ","sloada"),
	SADDA(0x22, "레지스터 내 값을 더하기 ","sadda"),
	SADDC(0x23, "상수 값을 더하기 ","saddc"),
	SSUBA(0x24, "레지스터 내 값을 빼기 ", 	"ssuba"),
	SSUBC(0x25, "상수 값을 빼기 ", "ssubc"),
	SMULA(0x26, "레지스터 내 값을 곱하기 ", "smula"),
	SMULC(0x27, "상수 값을 곱하기 ", "smulc"),
	SDIVA(0x28, "레지스터 내 값을 나누기 ","sdiva"),
	SDIVC(0x29, "상수 값을 값을 나누기 ", "sdivc"),
	SJMP(0x2A, "jump", "sjump"),
	LOADM(0x2B, "함수 불러오기", "loadm"),
	STOREA(0x2C, "레지스터 내 값 저장하기", "storea"),
	HSTOREA(0x2D, "레지스터 내 값 저장하기", "hstorea"),
	SSTOREA(0x2E, "레지스터 내 값 저장하기", "sstorea"),
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
