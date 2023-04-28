import java.util.HashMap;
import java.util.Scanner;

public class Loader {
	public enum ESymbolType{ // symbol table
		eVariable,
		eLabel,
		eRegister
	}
	
//	variable에서 count, sum, i 가 나오면 숫자로 바꿔줘야 함. 
//	int로 바꿔줘야 함. 
	class SymbolEntity{
		public SymbolEntity(ESymbolType symbolType, int value) {
			this.symbolType = symbolType;
			this.value = value;
		}
		
		public ESymbolType symbolType;
		public int value;
	}
	HashMap<String, SymbolEntity> symbolTable; 
	
	
	public Loader() {
		symbolTable = new HashMap<>();
	}
	
	public void load() {
		Scanner scanner = new Scanner("code/exe1");
		parseHeader(scanner);
		parseCode(scanner);
		scanner.close();
	}
	
	private void parseHeader(Scanner scanner) {
		int sizeDS, sizeSS, sizeHS;
		String line = scanner.nextLine();
		String[] tokens = getTokens(line); // command, operator, operator
		if(tokens[0].charAt(0) == '$') {
			if(tokens[0].charAt(1) == 'D') { 				//data segment
				sizeDS = Integer.parseInt(tokens[1]);
			} else if(tokens[0].charAt(1) == 'S') { 		// stack segment
				sizeSS = Integer.parseInt(tokens[1]);
			} else if(tokens[0].charAt(1) == 'H') { 		// heap segment
				sizeHS = Integer.parseInt(tokens[1]);
			}
		}else {
			this.symbolTable.put(tokens[0], new SymbolEntity(ESymbolType.eVariable, Integer.parseInt(tokens[1])));
		}
	}
	
	private String[] getTokens(String line) {
//		line.trim();
		String[] tokens = line.split(" [ \t]*"); // space, tab, linefield면 다 없애고 그 사이에 있는 것,,,,?
		return tokens;
	}
	
	private void parseCode(Scanner scanner) {
		
	}

}
