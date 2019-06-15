package commands;

import server.SymbolTable;

public class DisconnectCommand implements Command {

	@Override
	public double execute(String[] commAndArgs, SymbolTable st) {
		if (st.connect != null) {
			st.connect.sendString("bye");
			st.connect.closeServer();
		}
		if (st.server != null)
			st.server.closeServer();
		return 1;
	}

}
