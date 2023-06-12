package main;

import java.io.File;

public class Main {
	private CPU cpu;
	private Memory memory;
	public static void main(String[] args) {
		Main main = new Main();
		main.initialize();
		main.run();
	}
	
	public void initialize() {
		cpu = new CPU();
		memory = new Memory();
		cpu.associate(memory);
		memory.associate(cpu.mar, cpu.mbr);
	}

	public void run() {
		memory.setFile(new File("exe/exe"));
		memory.parse();
		cpu.start("exe");
	}
}
