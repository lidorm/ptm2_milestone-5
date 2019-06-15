package interpeter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import commands.AssignCommand;
import commands.Command;
import commands.ConnectCommand;
import commands.DefineVarCommand;
import commands.DisconnectCommand;
import commands.OpenServerCommand;
import commands.PrintCommand;
import commands.ReturnCommand;
import commands.SleepCommand;
import commands.conditions.IfCommand;
import commands.conditions.WhileCommand;
import regex.Regex;
import server.SimValuePath;
import server.Symbol;
import server.SymbolTable;

public class Parser {

	private SymbolTable st;
	private HashMap<String, Command> commandsMap;
	private String[] currLine;
	private int stringsToPass;

	public Parser(String[] args) {

		this.st = new SymbolTable();
		this.commandsMap = new HashMap<>();
		this.currLine = null;
		//in case of while loop we'll use this to count how strings to pass at the array list
		this.stringsToPass = 0;
		
		this.commandsMap.put("openDataServer", new OpenServerCommand(args));
		this.commandsMap.put("connect", new ConnectCommand());
		this.commandsMap.put("var", new DefineVarCommand());
		this.commandsMap.put("=", new AssignCommand());
		this.commandsMap.put("print", new PrintCommand());
		this.commandsMap.put("if", new IfCommand());
		this.commandsMap.put("while", new WhileCommand(this.commandsMap));
		this.commandsMap.put("disconnect", new DisconnectCommand());
		this.commandsMap.put("return", new ReturnCommand());
		this.commandsMap.put("sleep", new SleepCommand());
		
		for (String string : args) {
			st.newPath(string);
		}
	}

	

	@SuppressWarnings("unchecked")
	public double parse(ArrayList<String> textToParse) {

		double answer = 0;
		for (String string : textToParse) {
			if(this.stringsToPass > 0) {
				this.stringsToPass--;
				continue;
			}
		//	this.currLine = string.split(" ");
		//	StringBuilder b = new StringBuilder();
			Regex r = new Regex(string);
			//r.expAsStr(b);
			

		this.currLine = Arrays.stream(r.getSp())
				.filter(str-> !str.isEmpty())
				.filter((str1)->!str1.equals(" "))
				.toArray(String[]::new);
				
			Command c = commandsMap.get(currLine[0]);
			if (currLine.length > 1 && currLine[1].equals("=") && c == null) {
				c = new DefineVarCommand();
			}
			if(c.equals(commandsMap.get("while"))) {
				ArrayList<String> textToSearch = new ArrayList<>();
				textToSearch = (ArrayList<String>) textToParse.clone();
				this.stringsToPass = (int) c.execute(findLoop(textToSearch), st);
				this.stringsToPass+=1;
				continue;
				
			}
			if (c != null)
				answer = c.execute(this.currLine, this.st);
		}

		return answer;

	}
	
	
	public String[] findLoop(ArrayList<String> textToSearch) {
		int count =0;
		for (String string : textToSearch) {
			String[] line  = string.split(" ");
			if(!line[0].equals("while")) {
				count++;
				continue;
			}
			else {
				break;
			}
		}
		StringBuilder whileBlock = new StringBuilder();
		List<String> addToWhileBlock = textToSearch.subList(count, textToSearch.size());
		for (String string : addToWhileBlock) {
			if(!string.equals("}"))
				whileBlock.append(string+";");
			else 
				break;
		}
		String[] wb = null;
		wb = whileBlock.toString().split(";");
		return wb;
		
		
		
	}

}
