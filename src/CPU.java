
public class CPU {
	private enum EState {
		eStopped, eRunning
	}
	public enum EOperator{
		eHalt,
		eAdd
	}
	private Memory memory;
	public void associate(Memory memory) {this.memory = memory;}
	
	private EState eState;
	
	// register
	private IR ir;
	public Register mar, mbr;
	public Register cs, pc; // code segment register, program counter
	
	public CPU() {
		ir = new IR();
		mar = new Register();
		mbr = new Register();
		cs = new Register();
		pc = new Register();
	}

	// 메모리에서 instruction을 가져옴.
	// PC + code segment를 더해서 위치를 찾고 가져옴 (IR에 가져다 두는 것까지가 fetch)
	public void fetch() {
		System.out.println("fetch");
		// 1. MAR <- CS + PC : Instruction이 들어가 있는 주소
		mar.setValue(cs.getValue() + pc.getValue());
		memory.load();
		// 2. MBR = memory.load() : 메모리 보고 로드를 해라.
		ir.setValue(mbr.getValue());
		// 3. IR = MBR : IR에다가 MBR를 집어넣는다.
		
		ir.setOperator(EOperator.eHalt);
	}

	public void decode() {
		System.out.println("decode");
		// 명령어에 따라서 주소가 들어와있을 수도 있고 값이 들어가 있을 수도 있음.
		// load operand
	}

	public void execute() {
		System.out.println("execute");
		// 실행한다.
		switch(ir.getOperator()) {
		case eHalt: this.halt(); break;
		default: break;
		}
	}

	public void start() {
		this.eState = EState.eRunning;
		this.run(); // 원래는 thread 의 역할임. 
	}

	public void halt() {
		this.eState = EState.eStopped;
	}

	// cpu의 과정은 3가지
	// 무한 루핑을 돌고 있음.
	public void run() {
		// callback: 나보다 밑에 있는 서비스가 상위 서비스를 호출을 하게 만드는 것임.
		// 다 정확하게 만드려면 thread로 만들어야 함.
		while (this.eState == EState.eRunning) {
			this.fetch();
			this.decode();
			this.execute();
		}
	}
	
	public class Register{
		int value;
		
		public Register() {
			this.value = 0;
		}
		public int getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}
		
	}
	
	private class IR{
		private EOperator eOperator;
		private int Operand1;
		private int Operand2;
		
		public EOperator getOperator() {
			return eOperator;
		}
		public void setOperator(EOperator eOperator) {
			this.eOperator = eOperator;
		}
		public int getOperand1() {
			return Operand1;
		}
		public void setOperand1(int operand1) {
			Operand1 = operand1;
		}
		public int getOperand2() {
			return Operand2;
		}
		public void setOperand2(int operand2) {
			Operand2 = operand2;
		}
	}

}
