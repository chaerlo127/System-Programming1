import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {		
		try {
			File file = new File("code" + "/" + "exe");
			Scanner scanner = new Scanner(file);
			
			
			CPU cpu = new CPU();
			Memory memory = new Memory();
			memory.parse(scanner);
			
			cpu.associate(memory);
			memory.associate(cpu.mar, cpu.mbr);
			cpu.start();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
