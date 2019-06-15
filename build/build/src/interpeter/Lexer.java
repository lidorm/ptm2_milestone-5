package interpeter;

import java.util.ArrayList;




public class Lexer {
	
	private ArrayList<String> textToParse; 
	
	public Lexer() {
		this.textToParse = new ArrayList<>();
	}
	
	
	public ArrayList<String> getTextToParse() {
		return textToParse;
	}


	public void setTextToParse(ArrayList<String> textToParse) {
		this.textToParse = textToParse;
	}


	public ArrayList<String> getLineAndAdd(String[] line) {
			
			for (String string : line) {
				
				textToParse.add(string);
			}
		
		return textToParse;
		
		
		
	}
	
}
