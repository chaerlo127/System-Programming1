package main;
import constant.Constant;
import instruction_design.SOperator;

import java.util.Scanner;

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
			case LOADA: loada(); break;
			case LOADC: loadc(); break;
			case JMP: break;
			case GTJ: break;
			case GEJ: break;
			case AND: break;
			case NOT: break;
			case HALT: break;
			case ADDA: break;
			case ADDC: break;
			case SUBA: break;
			case SUBC: break;
			case MULA: break;
			case MULC: break;
			case DIVA: break;
			case DIVC: break;
			case HPUSH: break;
			case HPOP: break;
			case HLOADA: break;
			case HLOADC: break;
			case HADDA: break;
			case HADDC: break;
			case HSUBA: break;
			case HMULA: break;
			case HMULC: break;
			case HDIVA: break;
			case HDIVC: break;
			case HJMP: break;
			case SPUSH: break;
			case SPOP: break;
			case SLOADA: break;
			case SLOADC: break;
			case SADDA: break;
			case SADDC: break;
			case SSUBA: break;
			case SSUBC: break;
			case SMULA: break;
			case SMULC: break;
			case SDIVA: break;
			case SDIVC: break;
			case SJMP: break;
			case LOADM: break;
			case STOREA: storea(); break;
			case HSTOREA: break;
			case SSTOREA: break;

		}

		pc.setValue(pc.getValue() + 1);
	}



	/**
	 * operator method
	 * */


	private void loada() {
		if(instruction.getOperand() == Constant.CPU.keyboard){
			System.out.println("[학생의 수를 입력하세요!]");
			Scanner scanner = new Scanner(System.in);
			mbr.setValue(scanner.nextInt());
			scanner.close();
		}else if (instruction.getOperand() == Constant.CPU.random){
			int score = (int)( Math.random() * 51 ) + 50;
			mbr.setValue(score);
		}else{
			mar.setValue(instruction.getOperand());
			memory.loadData();
		}
	}

	private void loadc() {
		mar.setValue(instruction.getOperand());
	}

	private void storea() {
		if(instruction.getOperand() == Constant.CPU.monitor){
			// todo: 코드 생성
		}else {
			// mar에 주소를 저장해두고
			mar.setValue(instruction.getOperand());
			// mbr에 저장되어 있는 것을 찾아서 data segment 에 저장!
			memory.storeData();
		}
	}



//	private void moveA() { // move address value to register
//		Register opreand1 = instruction.getOperand1();
//		int opreand2 = instruction.getIntOperand2();
//		if(opreand1 != null) {
//			opreand1.setValue(opreand2);
//		}
//
//	}
//
//	private void moveC() { // move constant to register
//		Register opreand1 = instruction.getOperand1();
//		if(opreand1 != null) {
//			opreand1.setValue(instruction.getIntOperand2());
//		}
//	}
//
//	private void moveR() { // move register to register
//		Register opreand1 = instruction.getOperand1();
//		Register opreand2 = instruction.getOperand2();
//		if(opreand1 != null && opreand2 != null) {
//			opreand1.setValue(opreand2.getValue());
//		}
//	}
//
//	private void addR() {
//		Register opreand1 = instruction.getOperand1();
//		Register opreand2 = instruction.getOperand2();
//
//		if(opreand1 != null && opreand2 != null) {
//			opreand1.setValue(opreand1.getValue() + opreand2.getValue());
//		}
//	}
//
//	private void addC() {
//		Register opreand1 = instruction.getOperand1();
//		int opreand2 = instruction.getIntOperand2();
//		if(opreand1 != null) {
//			opreand1.setValue(opreand1.getValue() + opreand2);
//		}
//	}
//
//
//	private void subR() {
//		Register opreand1 = instruction.getOperand1();
//		Register opreand2 = instruction.getOperand2();
//
//		if(opreand1 != null && opreand2 != null) {
//			int vaule1 = opreand1.getValue();
//			int value2 = opreand2.getValue();
//			opreand1.setValue(opreand1.getValue() - opreand2.getValue());
//			if(vaule1 == value2) this.bEqual = true;
//			if(vaule1 > value2) this.bGratherThan = true;
//		}
//	}
//
//	private void subC() {
//		Register opreand1 = instruction.getOperand1();
//		int opreand2 = instruction.getIntOperand2();
//		if(opreand1 != null) {
//			int vaule1 = opreand1.getValue();
//			int value2 = opreand2;
//			opreand1.setValue(opreand1.getValue() - opreand2);
//			if(vaule1 == value2) this.bEqual = true;
//			if(vaule1 > value2) this.bGratherThan = true;
//		}
//	}
//
//	private void mulR() {
//		Register opreand1 = instruction.getOperand1();
//		Register opreand2 = instruction.getOperand2();
//
//		if(opreand1 != null && opreand2 != null) {
//			opreand1.setValue(opreand1.getValue() * opreand2.getValue());
//		}
//	}
//
//	private void mulC() {
//		Register opreand1 = instruction.getOperand1();
//		int opreand2 = instruction.getIntOperand2();
//		if(opreand1 != null) {
//			opreand1.setValue(opreand1.getValue() * opreand2);
//		}
//	}
//
//	private void divR() {
//		Register opreand1 = instruction.getOperand1();
//		Register opreand2 = instruction.getOperand2();
//
//		if(opreand1 != null && opreand2 != null) {
//			opreand1.setValue(opreand1.getValue() / opreand2.getValue());
//		}
//	}
//
//	private void divC() {
//		Register opreand1 = instruction.getOperand1();
//		int opreand2 = instruction.getIntOperand2();
//		if(opreand1 != null) {
//			opreand1.setValue(opreand1.getValue() / opreand2);
//		}
//	}
//
//	private void jump(StringBuilder sb) {
//		System.out.println("----------------------jump----------------------");
//		sb.append("----------------------jump----------------------").append("<br>");
//		sb.append(this.memory.showDS()).append("<br>");
//
//		int opreand1 = instruction.getIntOperand1();
//		this.pc.setValue(opreand1);
//		this.bEqual = false;
//		this.bGratherThan = false;
//	}
//
//	private void halt(StringBuilder sb) {
//		this.eState = EState.eStopped;
//		System.out.println();
//		sb.append("</html>");
//	}
//
//
//	private void lda() {
//		this.memory.load();
//	}
//
//	private void sto() {
//		this.memory.store();
//	}
//
//	private void gtj() {
//		if(this.bGratherThan) {
//			int opreand1 = instruction.getIntOperand1();
//			this.pc.setValue(opreand1);
//		}
//		this.bGratherThan = false;
//	}
//
//	private void gej() {
//		if(this.bEqual || this.bGratherThan) {
//			int opreand1 = instruction.getIntOperand1();
//			this.pc.setValue(opreand1);
//		}
//	}

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
		private int operand;

		public Instruction(String line) {
			String[] tokens = line.split(" ");
			this.operator = changeStringToInt(tokens[0]);
			this.operand = Integer.MAX_VALUE;

			if (tokens.length > 1) {
				this.operand = changeStringToInt(tokens[1]);
			}
		}

		public SOperator getOperator() {
			for(SOperator operator: SOperator.values()) {
				if(operator.getCode() == this.operator) {
					return operator;
				}
			}return null;
		}

		public int getOperand() {
			return this.operand;
		}

	}
}
