package commands;




import server.SymbolTable;

public class PrintCommand implements Command {

	    public PrintCommand() {
	        
	    }
	    
	    @Override
	    public double execute(String[] commAndArgs,SymbolTable st) {
	    	System.out.println(st.getSymbolTable().get(commAndArgs[1]).getSimValuePath().getValue());
			return 1;
	    }
	
}
