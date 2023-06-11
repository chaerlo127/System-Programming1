package parser;
import java.util.Vector;

public class SSymbolTable extends Vector<SSymbolEntity> {
	private static final long serialVersionUID = 1L;
	
	public SSymbolEntity findByVariableName(String variableName) {
		for (SSymbolEntity entity: this) {
			if(entity.getVariableName().equals(variableName)) return entity;
		}
		return null;
	}
	
}
