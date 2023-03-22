
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
	public Register mar, mbr;
	public Register cs, pc, ac; // code segment register, program counter
	
	/**
	 * constructor
	 */
	public CPU() {
		ir = new IR();
		mar = new Register();
		mbr = new Register();
		cs = new Register();
		pc = new Register();
		ac = new Register();
	}

	
	/**
	 * cpu instruction cycle method
	 */
	
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
	}

	public void decode() {
		System.out.println("decode");
		// 명령어에 따라서 주소가 들어와있을 수도 있고 값이 들어가 있을 수도 있음.
		
		// load operand
	}

	public void execute() {
		pc.setValue(pc.getValue() + 1); // pc에 값을 넣어줌.
		
		System.out.println("execute");
		
		// 실행한다.
		switch(ir.getOperator()) {
		case eHalt: this.halt(); break;
		case eAdd: this.add(); break;
		default: break;
		}
	}
	
	/**
	 * operator method
	 * 
	 * */
	public void halt() {
		this.eState = EState.eStopped;
	}
	
	private void add() {
		// ir 뒤에 있는 것은 operand를 다시 로드를 해서 mbr에서 저장을 하고, ac에 값을 이미 저장이 되어 있어야 함.
		// load는 mbr까지
		// add : ac + mbr 값  => ac에 저장
		mar.setValue(ir.getOperand());
		memory.load();// mbr에 값이 저장됨. 
		
		// alu가 실제 값을 실행 하는 것임.
		ac.setValue(ac.getValue() + mbr.getValue());
	}

	public void start() {
		this.eState = EState.eRunning;
		this.run(); // 원래는 thread 의 역할임. 
	}
	
	// 무한 루핑 (polling) 
	public void run() {
		// callback: 나보다 밑에 있는 서비스가 상위 서비스를 호출을 하게 만드는 것임.
		// 다 정확하게 만드려면 thread로 만들어야 함.
		while (this.eState == EState.eRunning) {
			this.fetch();
			this.decode();
			this.execute();
			
//			checkInterrupt(); : cpu에게 내 일도 해달라고 부탁을 하는 것을 의미함. context switching 확인
//			io interrupt가 os가 없어도 interrupt를 보낼 수 있음. 
		}
	}
	
	/**
	 * inner class
	 *
	 */
	public class Register{
		protected int value;
		
		public Register() {
			this.value = 0;
		}
		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
		
	}
	
	private class IR extends Register{
		public EOperator getOperator() {
			int operator = value >> 24; // 4 바이트이므로 3개를 오른쪽으로 당겨서 초기화를 시킨다. short => 2 byte, int => 4 byte
			EOperator eOperator = EOperator.values()[operator]; // operator의 값을 찾음, 서수로 찾고 나서 enum으로 바꾼 것임.
			return eOperator;
		}
		public int getOperand() {
			// bit-wise operation
			int operand = value & 0x00FFFFFF; // 앞에 8비트를 뜯어낸 것임. 앞에 있는 operator를 지운 것임. 32비트에서 8비트는 0으로 채우고 24비트만 가지고 오는 것임.
			// 오른쪽을 다 가지고 있어야 함.
			// 왼쪽 8bit는 operator, 오른쪽 24bit는 operand의 값을 가지고 있음.
			return operand;
		}
	}

}
