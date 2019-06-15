package commands;


import server.SymbolTable;

public interface Command {
	
	double execute(String[] commAndArgs,SymbolTable st);
}
