package instruction_design;

public enum SSegment {
	DS(0x01, "DATA SEGMENT"),
	SS(0x02, "STACK SEGMENT"),
	CS(0x03, "CODE SEGMENT"),
	HS(0x04, "HEAP SEGMENT");
	
	private int code;
	private String description;
	public int getCode() {
		return code;
	}
	
	
	
	SSegment(int code, String description) {
		this.code = code;
		this.description = description;
	}

}
