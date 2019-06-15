package commands;

import server.SymbolTable;

public class SleepCommand implements Command{

	@Override
	public double execute(String[] commAndArgs, SymbolTable st) {
		try {
			Thread.sleep(Long.parseLong(commAndArgs[1]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
