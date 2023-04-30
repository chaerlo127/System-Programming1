import java.util.Scanner;
import java.util.Vector;

// 데이터 저장 공간의 집합
public class Memory {
	private Vector<String> memory;
	public Vector<String> getMemory() {return memory;}

	private int[] dataSegment;
	
	private CPU.Register mar;
	private CPU.Register mbr;
		
	public Memory() {
		this.memory = new Vector<String>();
		this.dataSegment = new int[9];
	}
	
	public void parse(Scanner scanner) {
		while(scanner.hasNext()) {
			String token = scanner.nextLine();
			if(!token.isEmpty()) {
				this.memory.add(token);
			}
		}
	}
	
	public void associate(CPU.Register mar, CPU.Register mbr) {
		// 메모리를 장착하면, cpu mar, mbr을 memory와 연결함.
		this.mar = mar;
		this.mbr = mbr;
		
	}

	public void load() {
		// MAR 주소 읽어오기 
		int address = mar.getValue();
		mbr.setValue(dataSegment[address]);
//		mbr.setValue(this.memory.get(address)); // 메모리를 읽어서 집어넣자 
	}
	
	
	public void store() {
		int address = mar.getValue();
		int value = mbr.getValue();
		dataSegment[address] = value;
	}

}
