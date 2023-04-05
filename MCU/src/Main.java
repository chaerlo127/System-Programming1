
public class Main {
	public static void main(String[] args) {
		// power, on, switch
		// clock이 인스트럭션을 수행하라고 북을 치는 것과 같다. 
		// CPU를 생성해보자
		CPU cpu = new CPU();
		Memory memory = new Memory();
		cpu.associate(memory); // 실제로는 서로 연결을 하지 않고, control bus로 연결이 된 것이다.
		memory.associate(cpu.mar, cpu.mbr);
		cpu.start();
	}
}
