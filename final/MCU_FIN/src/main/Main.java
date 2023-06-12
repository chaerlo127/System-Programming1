package main;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	private MUI ui;
	
	public void initialize() {
		ui = new MUI();
		ui.initialize();
	}
	public void run() {
		ui.run();
	}
	public static void main(String[] args) {
		
		Main main = new Main();
		main.initialize();
		main.run();
	}
}
