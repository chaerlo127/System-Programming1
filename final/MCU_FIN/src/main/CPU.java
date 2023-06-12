package main;
import constant.Constant;
import instruction_design.SOperand;
import instruction_design.SOperator;

public class CPU {
	/**
	 * Enumeration 
	 */
	private enum EState {
		eStopped, eRunning
	}
	public enum EOperator{
		eHalt,
		eAdd
	}
	
	private Memory memory;
	private EState eState;
	public void associate(Memory memory) {this.memory = memory;}

	/**
	 * register
	 */
	private IR ir;
	public Register mbr;
	public Register mar, cs, pc, ac1, ac2; // code segment register, program counter
	private Instruction instruction;
	
	
	private boolean bGratherThan;
	private boolean bEqual;
	
	/**
	 * constructor
	 */
	public CPU() {
		ir = new IR();
		mar = new Register();
		mbr = new Register();
		cs = new Register();
		pc = new Register();
		ac1 = new Register();
		ac2 = new Register();
		
		this.bEqual = false;
		this.bGratherThan = false;
	}


	public String start(String processName) {
		this.eState = EState.eRunning;
		return this.run(processName); // 원래는 thread 의 역할임. 
	}
	
	// 무한 루핑 (polling) 
	private String run(String processName) {
		System.out.println("[This Running Process Name]: " + processName);
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("[This Running Process Name]: " + processName).append("<br>");
		
		while (this.eState == EState.eRunning) {
			this.fetch(sb);
			this.decode();
			this.execute(sb);
		}
		return sb.toString();
	}
	
	/**
	 * cpu instruction cycle method
	 */
	
	public void fetch(StringBuilder sb) {
		
		
		instruction = new Instruction(this.memory.getMemory().get(cs.getValue() + pc.getValue()));
		System.out.println(this.pc.value + " : " + instruction.getOperator());
		sb.append(this.pc.value + " : " + instruction.getOperator()).append("<br>");
		
	}

	public void decode() {
	}

	public void execute(StringBuilder sb) {
		switch(instruction.getOperator()) {
		case MOVEA: moveA(); break;
		case MOVEC: moveC(); break;
		case MOVER: moveR(); break;
		case STO: sto(); break;
		case LDA: lda(); break;
		case JMP: jump(sb); break;
		case GTJ: gtj(); break;
		case GEJ: gej(); break;
		case AND: break;
		case NOT: break;
		case HALT: halt(sb); break;
		case ADDR: addR(); break;
		case ADDC: addC(); break;
		case SUBR: subR(); break;
		case SUBC: subC(); break;
		case MULR: mulR(); break;
		case MULC: mulC(); break;
		case DIVR: divR(); break;
		case DIVC: divC(); break;
		}
		
		pc.setValue(pc.getValue() + 1);
	}
	
	/**
	 * operator method
	 * 
	 * */
	
	private void moveA() { // move address value to register
		Register opreand1 = instruction.getOperand1();
		int opreand2 = instruction.getIntOperand2();
		if(opreand1 != null) {
			opreand1.setValue(opreand2);
		}
		
	}
	
	private void moveC() { // move constant to register
		Register opreand1 = instruction.getOperand1();
		if(opreand1 != null) {
			opreand1.setValue(instruction.getIntOperand2());
		}
	}
	
	private void moveR() { // move register to register
		Register opreand1 = instruction.getOperand1();
		Register opreand2 = instruction.getOperand2();
		if(opreand1 != null && opreand2 != null) {
			opreand1.setValue(opreand2.getValue());
		}
	}
	
	private void addR() {
		Register opreand1 = instruction.getOperand1();
		Register opreand2 = instruction.getOperand2();
		
		if(opreand1 != null && opreand2 != null) {
			opreand1.setValue(opreand1.getValue() + opreand2.getValue());
		}
	}
	
	private void addC() {
		Register opreand1 = instruction.getOperand1();
		int opreand2 = instruction.getIntOperand2();
		if(opreand1 != null) {
			opreand1.setValue(opreand1.getValue() + opreand2);
		}
	}
	
	
	private void subR() {
		Register opreand1 = instruction.getOperand1();
		Register opreand2 = instruction.getOperand2();
		
		if(opreand1 != null && opreand2 != null) {
			int vaule1 = opreand1.getValue();
			int value2 = opreand2.getValue();
			opreand1.setValue(opreand1.getValue() - opreand2.getValue());
			if(vaule1 == value2) this.bEqual = true;
			if(vaule1 > value2) this.bGratherThan = true;
		}
	}
	
	private void subC() {
		Register opreand1 = instruction.getOperand1();
		int opreand2 = instruction.getIntOperand2();
		if(opreand1 != null) {
			int vaule1 = opreand1.getValue();
			int value2 = opreand2;
			opreand1.setValue(opreand1.getValue() - opreand2);
			if(vaule1 == value2) this.bEqual = true;
			if(vaule1 > value2) this.bGratherThan = true;
		}
	}
	
	private void mulR() {
		Register opreand1 = instruction.getOperand1();
		Register opreand2 = instruction.getOperand2();
		
		if(opreand1 != null && opreand2 != null) {
			opreand1.setValue(opreand1.getValue() * opreand2.getValue());
		}
	}
	
	private void mulC() {
		Register opreand1 = instruction.getOperand1();
		int opreand2 = instruction.getIntOperand2();
		if(opreand1 != null) {
			opreand1.setValue(opreand1.getValue() * opreand2);
		}
	}
	
	private void divR() {
		Register opreand1 = instruction.getOperand1();
		Register opreand2 = instruction.getOperand2();
		
		if(opreand1 != null && opreand2 != null) {
			opreand1.setValue(opreand1.getValue() / opreand2.getValue());
		}
	}
	
	private void divC() {
		Register opreand1 = instruction.getOperand1();
		int opreand2 = instruction.getIntOperand2();
		if(opreand1 != null) {
			opreand1.setValue(opreand1.getValue() / opreand2);
		}
	}
	
	private void jump(StringBuilder sb) {
		System.out.println("----------------------jump----------------------");
		sb.append("----------------------jump----------------------").append("<br>");
		sb.append(this.memory.showDS()).append("<br>");
		
		int opreand1 = instruction.getIntOperand1();
		this.pc.setValue(opreand1);
		this.bEqual = false;
		this.bGratherThan = false;
	}
	
	private void halt(StringBuilder sb) {
		this.eState = EState.eStopped;
		System.out.println();
		sb.append("</html>");
	}

	
	private void lda() {
		this.memory.load();
	}

	private void sto() {
		this.memory.store();
	}
	
	private void gtj() {
		if(this.bGratherThan) {
			int opreand1 = instruction.getIntOperand1();
			this.pc.setValue(opreand1);
		}
		this.bGratherThan = false;
	}
	
	private void gej() {
		if(this.bEqual || this.bGratherThan) {
			int opreand1 = instruction.getIntOperand1();
			this.pc.setValue(opreand1);
		}
	}
	
	/**
	 * inner class
	 *
	 */
	public class Register{
		protected int value;
		
		public Register() {

		}
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
	}
	
	public class IR extends Register{
		
	}
	
	public int changeStringToInt(String command) {
		command = command.replace("0x", "");
		return Integer.parseInt(command, 16);
	}
	
	
	private class Instruction {
		private int operator;
		private int operand1;
		private int operand2;

		public Instruction(String line) {
			String[] tokens = line.split(" ");
			this.operator = changeStringToInt(tokens[0]);
			this.operand1 = Integer.MAX_VALUE;
			this.operand2 = Integer.MAX_VALUE;

			if (tokens.length > 1) {
				this.operand1 = changeStringToInt(tokens[1]);
			}
			if (tokens.length > 2) {
				this.operand2 = changeStringToInt(tokens[2]);
			}
		}
		
		public SOperator getOperator() {
			for(SOperator operator: SOperator.values()) {
				if(operator.getCode() == this.operator) {
					return operator;
				}
			}return null;
		}

		public Register getOperand1() {
			return returnOperand(this.operand1);
		}

		public Register getOperand2() {
			return returnOperand(this.operand2);
		}
		
		private Register returnOperand(int operand) {
			if(operand == SOperand.MAR.getCode()) {
				return mar;
			}else if(operand == SOperand.MBR.getCode()) {
				return mbr;
			}else if(operand == SOperand.AC1.getCode()) {
				return ac1;
			}else if(operand == SOperand.AC2.getCode()) {
				return ac2;
			}
			return null;
		}
		
		public int getIntOperand1() {
			return this.operand1;
		}
		
		public int getIntOperand2() {
			return this.operand2;
		}


	}
}
