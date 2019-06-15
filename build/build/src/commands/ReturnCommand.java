package commands;



import expressions.ShuntingYardAlgorithm;
import server.SymbolTable;

public class ReturnCommand implements Command{

	@Override
	public double execute(String[] commAndArgs,SymbolTable st) {
		if(commAndArgs.length == 2) {
			return st.getSymbolTable().get(commAndArgs[1]).getSimValuePath().getValue();
		}
		else {
			return ShuntingYardAlgorithm.calc(commAndArgs,st.getSymbolTable());
		}
	}

}
