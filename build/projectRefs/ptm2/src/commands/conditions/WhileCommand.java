package commands.conditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import commands.AssignCommand;
import commands.Command;
import commands.DefineVarCommand;
import commands.DisconnectCommand;
import commands.PrintCommand;
import commands.ReturnCommand;
import interpeter.Parser;
import regex.Regex;
import server.SymbolTable;

public class WhileCommand extends ConditionParser {

	private int stringsToPass;
	private HashMap<String, Command> commandsMap;
	

	public WhileCommand(HashMap<String, Command> cm) {
		this.listOfCommands = new LinkedList<>();
		this.stringsToPass = 2;
		this.commandsMap = cm;
		

	}

	@Override
	public double execute(String[] commAndArgs, SymbolTable st) {
		// validate arguments
		String variable = commAndArgs[0].split(" ")[1];
		String operator = commAndArgs[0].split(" ")[2];
		String value = commAndArgs[0].split(" ")[3];
		Command c = null;
		for (String string : commAndArgs) {
			if(string.contains("{")){
				continue;	
			}		
			else {
				
				if (string.equals("}")){
					break;
				}
				else if (!string.equals("}") && !string.equals("{")) {
					
					String[] commandToExec = string.split(" ");
						
					this.listOfCommands.add(commandToExec);
				}
			}
		}
		this.stringsToPass = listOfCommands.size();
		while (condition(variable, operator, value, st)) {

			for (int i = 0; i < listOfCommands.size(); i++) {

				if (listOfCommands.size()-1 == i || listOfCommands.get(i) == null) {
					i = 0;
					break;
				}
				if(listOfCommands.get(i)[1].equals("=")) {
					c = new DefineVarCommand();
					c.execute(listOfCommands.get(i), st);
				}
				else if (c == null) {
					c = commandsMap.get(listOfCommands.get(i)[0]);
					c.execute(listOfCommands.get(i), st);
				}
				

			}

		}
		return this.listOfCommands.size() - 1;
	}

	private boolean condition(String variable, String operator, String value, SymbolTable st) {

		switch (operator) {
		case "<":
			if(st.getSymbolTable().get(variable).getSimValuePath().getValue() < Double.valueOf(value))
				return true;
		case ">":
			if(st.getSymbolTable().get(variable).getSimValuePath().getValue() > Double.valueOf(value))
				return true;
		case "<=":
			if(st.getSymbolTable().get(variable).getSimValuePath().getValue() <= Double.valueOf(value))
				return true;
		case ">=":
			if(st.getSymbolTable().get(variable).getSimValuePath().getValue() >= Double.valueOf(value))
				return true;
		case "==":
			if(st.getSymbolTable().get(variable).getSimValuePath().getValue().equals(Double.valueOf(value)))
				return true;
		case "!=":
			if(!st.getSymbolTable().get(variable).getSimValuePath().getValue().equals(Double.valueOf(value)))
				return true;
		default:
			return false;
		}

	}

}
