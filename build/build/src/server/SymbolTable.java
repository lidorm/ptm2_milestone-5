package server;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import commands.ConnectCommand;
import commands.OpenServerCommand;

public class SymbolTable {

	private HashMap<String, Symbol> symbolTable;
	private ConcurrentHashMap<String, SimValuePath> path;
	public ConnectCommand connect = null;
	public OpenServerCommand server = null;
	
	
	public SymbolTable() {
		this.symbolTable = new HashMap<>(); 
		this.path = new ConcurrentHashMap<>();
	}

	public HashMap<String, Symbol> getSymbolTable() {
		return symbolTable;
	}

	public ConcurrentHashMap<String, SimValuePath> getPath() {
		return path;
	}

	public SimValuePath getPath(String key) {
		return path.get(key);
	}
	public void newPath(String key) {
		path.put(key, new SimValuePath(key, 0.0));
	}


	
	
	 


	
	
	



}
