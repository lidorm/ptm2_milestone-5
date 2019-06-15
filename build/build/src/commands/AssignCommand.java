package commands;

import java.util.HashMap;

import expressions.ShuntingYardAlgorithm;
import regex.Regex;
import server.SimValuePath;
import server.Symbol;
import server.SymbolTable;

public class AssignCommand implements Command {

	private String assignType;
	
	public AssignCommand(String aType) {
          this.assignType=aType;
	}

	public AssignCommand() {
		// TODO Auto-generated constructor stub
	}

	// method to check where we have a var in the exp and put instead his value in
	// double.
	public String[] putSymbolValForVar(String[] commAndArgs, SymbolTable st, int index) {
		StringBuilder sb = new StringBuilder();
		int size = commAndArgs.length;
		int j = index;
		while (j < size) {
			sb.append(commAndArgs[j] + " ");
			j++;
		}
		String[] s = sb.toString().split(" ");
		sb = new StringBuilder();
		@SuppressWarnings("unused")
		String[] fstring;
		for (String str : s) {
			if(str.charAt(0) == '-' && str.length() > 1) {
				sb.append("-").append((st.getSymbolTable().get(str.substring(1)).getSimValuePath().getValue().toString()+" "));
			}
			else if (st.getSymbolTable().containsKey(str))
				sb.append(st.getSymbolTable().get(str).getSimValuePath().getValue().toString()+" ");
			else if(!str.equals("")){
				sb.append(str + " ");
			}
			
		}

		return fstring = sb.toString().split(" ");

	}
	
	public void bindToPath(String[] commAndArgs,SymbolTable st) {
		if(st.getPath().containsKey(commAndArgs[4])) {
			 Symbol s = new Symbol(commAndArgs[1], st.getPath().get(commAndArgs[4]));
		      st.getSymbolTable().put(commAndArgs[1], s);
		}
		else {
			st.getSymbolTable().put(commAndArgs[1],new Symbol(commAndArgs[1],new SimValuePath(commAndArgs[4], 0)));
			st.getPath().put(commAndArgs[4], st.getSymbolTable().get(commAndArgs[1]).getSimValuePath());
			
		}
			
	}
	
	public void VarValAssign(String[] commAndArgs,SymbolTable st) {
		st.getSymbolTable().get(commAndArgs[0]).getSimValuePath().setValue(Double.parseDouble(commAndArgs[2]));
		if(st.connect != null)
	    	st.connect.sendCommand(st.getSymbolTable().get(commAndArgs[0]).getSimValuePath().getPath(),
	    			st.getSymbolTable().get(commAndArgs[0]).getSimValuePath().getValue()); 
	}
	
	public void varBindToVar(String[] commAndArgs,SymbolTable st) {
		Symbol s = new Symbol(commAndArgs[1], new SimValuePath(commAndArgs[1],(double) 0));
		st.getSymbolTable().put(commAndArgs[1], s);// 
		double v = Double.parseDouble(putSymbolValForVar(commAndArgs, st, 3)[0]);
		System.out.println("AssignCommand Line 77: " + v);
		st.getSymbolTable().get(commAndArgs[1]).getSimValuePath().setValue(v);
		System.out.println("AssignCommand Line 77: " + st.getSymbolTable().get(commAndArgs[1]).getSimValuePath().getValue());
	}
	
	public void valAssignFromEXP(String[] commAndArgs,SymbolTable st) {
		String[] str = this.putSymbolValForVar(commAndArgs, st, 2);
		double v = ShuntingYardAlgorithm.calc(str, st.getSymbolTable());
		st.getSymbolTable().get(commAndArgs[0]).getSimValuePath().setValue(v);
		if(st.connect != null)
	    	st.connect.sendCommand(st.getSymbolTable().get(commAndArgs[0]).getSimValuePath().getPath(),
	    			st.getSymbolTable().get(commAndArgs[0]).getSimValuePath().getValue());
	}
	
	@Override
	public double execute(String[] commAndArgs, SymbolTable st) {
		
		
		switch (this.assignType) {
		case "BIND_TO_PATH":
			this.bindToPath(commAndArgs, st);
			break;
		case "VAL_TO_VAR":
			this.VarValAssign(commAndArgs, st);
			break;
		case "VAR_BIND_TO_VAR":
			this.varBindToVar(commAndArgs, st);
			break;
		case "VAL_FROM_EXP":
			this.valAssignFromEXP(commAndArgs, st);
			break;
		}
		
		return 2;
	}

}
