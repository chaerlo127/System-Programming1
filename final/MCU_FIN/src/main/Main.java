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
		System.out.println("[MCU] (1) exe (2) exe1 (0) 끝내기");
		int input = scanner.nextInt();
		while (input != 0){
			if(input == 1) input = selectFile("exe/exe");
			if(input == 2) input = selectFile("exe/exe1");
			else System.out.println("다시 입력해주세요!");
			input = scanner.nextInt();
		}
		scanner.close();
	}

	private int selectFile(String fileName) {
		int input;
		memory.setFile(new File(fileName));
		memory.parse();
		cpu.start(fileName);
		System.out.println("[MCU] (1) exe (2) exe1 (0) 끝내기");
		input = scanner.nextInt();
		return input;
	}
}
