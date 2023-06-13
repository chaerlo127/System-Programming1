package main;

import java.io.File;
import java.util.Scanner;

public class Main {
	private CPU cpu;
	private Memory memory;
	private Scanner scanner;

	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
		main.run();
	}

	public void initialize() {
		scanner = new Scanner(System.in);
		cpu = new CPU();
		memory = new Memory();
		cpu.associate(memory, scanner);
		memory.associate(cpu.mar, cpu.mbr);
	}

	public void run() {
		System.out.println("[MCU] (1) exe (2) 끝내기");
		int input = scanner.nextInt();
		while (input != 0){
			memory.setFile(new File("exe/exe"));
			memory.parse();
			cpu.start("exe");
			System.out.println("[MCU] (1) exe (2) 끝내기");
			input = scanner.nextInt();
		}
		scanner.close();

	}
}
