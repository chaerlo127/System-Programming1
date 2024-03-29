import java.util.Scanner;
import java.util.Vector;

// 데이터 저장 공간의 집합
public class Memory {
	private Vector<String> memory;
	
	private CPU.Register mar;
	private CPU.Register mbr;
	public Memory() {
		this.memory = new Vector<String>();
		Scanner scanner = new Scanner("code/exe1");
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			if(!line.startsWith("//")) this.memory.add(line);
		}
		scanner.close();
	}
	
	public void associate(CPU.Register mar, CPU.Register mbr) {
		// 메모리를 장착하면, cpu mar, mbr을 memory와 연결함.
		this.mar = mar;
		this.mbr = mbr;
		
	}
	
	// control 버스를 통해 명령어 이동 
	// MAR 번지수를 읽어서 MBR에 저장함. 
	// MAR(CPU, Memory의 위치를 알고 있음)과 MBR을 알고 있어야 함.
	public void load() {
		// MAR 주소 읽어오기 
//		int address = mar.getValue();
//		mbr.setValue(this.memory.get(address)); // 메모리를 읽어서 집어넣자 
	}
	
	public void store() {
		int address = mar.getValue();
//		int value = mbr.getValue();
//		memory.set(address, value);
	}

}
