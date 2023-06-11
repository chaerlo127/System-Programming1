package constant;

public enum CButton {
	Select("File", "File"), 
	Exe("exe", "exe"), 
	Exe1("exe1", "exe1"),
	Exit("exit", "exit");
	
	private String name;
	private String label;
	CButton(String name, String label) {
		this.name = name;
		this.label = label;
	}

	public String getName() {
		return name;
	}
	public String getLabel() {
		return label;
	}
}
