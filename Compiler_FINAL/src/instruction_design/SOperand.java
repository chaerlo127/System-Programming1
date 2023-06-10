package instruction_design;

public enum SOperand {
	MAR(0x01, "MEMORY ADDRESS REGIESTER ", "mar"),
	MBR(0x02, "MEMORY BUFFER REGISETR", "mbr"),
	AC1(0x03, "AC 내 계산 register1", "ac1"),
	AC2(0x04, "AC 내 계산 register2", "ac2"),
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
	SOperand(int code, String description, String assemblyCode) {
		this.code = code;
		this.description = description;
		this.assemblyCode = assemblyCode;
	}
	
	public static SOperand findByAssemblyCode(String assemblyCode) {
		for(SOperand operator: values()) {
			if(operator.getAssemblyCode().equals(assemblyCode)) return operator;
		}
		return null;
	}
	

}
