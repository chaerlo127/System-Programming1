package main;


public class SMain {
	private SUI ui;

	public static void main(String[] args) {
		SMain main = new SMain();
		main.initialize();
		main.run();
		main.finalize();
	}
	public void initialize() {
		ui = new SUI();
		ui.initialize();

	}
	public void run() {
		ui.run();
	}
	public void finalize() {
		ui.finalize();
	}

}
