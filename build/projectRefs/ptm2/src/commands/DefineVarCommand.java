package commands;

import expressions.ShuntingYardAlgorithm;
import server.SimValuePath;
import server.Symbol;
import server.SymbolTable;

public class DefineVarCommand implements Command {

	private static final String varBindAssign = "BIND_TO_PATH";
	private static final String varValAssign = "VAL_TO_VAR";
	private static final String varBindToVar = "VAR_BIND_TO_VAR";
	private static final String valAssignFromEXP = "VAL_FROM_EXP";
	
	
	
	public DefineVarCommand() {

	}

	@Override
	public double execute(String[] commAndArgs, SymbolTable st) {
		if(commAndArgs.length >3 && commAndArgs[3].equals("bind")) {
		     
		      AssignCommand a = new AssignCommand(this.varBindAssign);
		      a.execute(commAndArgs, st);
		}
		else if (commAndArgs.length == 3 && isDouble(commAndArgs[2])) {
			AssignCommand a = new AssignCommand(this.varValAssign);
			a.execute(commAndArgs, st);
		}
		else if(commAndArgs.length == 4 && st.getSymbolTable().containsKey(commAndArgs[3])) {
			
			AssignCommand a = new AssignCommand(this.varBindToVar);
			a.execute(commAndArgs, st);
		}
		else if(st.getSymbolTable().containsKey(commAndArgs[0]) && commAndArgs.length > 3) {
			AssignCommand a = new AssignCommand(this.valAssignFromEXP);
			a.execute(commAndArgs, st);
		}else if(commAndArgs.length == 2 && commAndArgs[0].equals("var")) {
			Symbol s = new Symbol(commAndArgs[1], new SimValuePath(commAndArgs[1], 0));
		      st.getSymbolTable().put(commAndArgs[1], s);
		}
		
		return 4;
	}

	
	private boolean isDouble(String val) {
		try {
			Double.parseDouble(val);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	
	
}
