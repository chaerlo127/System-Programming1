package instruction_design;

public enum SOperand {
	MAR(0x01, "MEMORY ADDRESS REGIESTER "),
	MBR(0x02, "MEMORY BUFFER REGISETR"),
	AC1(0x03, "AC 내 계산 register1"),
	AC2(0x04, "AC 내 계산 register2"),
	;
	
	private int code;
	private String description;
	
	
	
	SOperand(int code, String description) {
		this.code = code;
		this.description = description;
	}
	

}
