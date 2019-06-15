package commands;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import server.SymbolTable;

public class ConnectCommand implements Command {

	private PrintWriter writer = null;
	private Socket cSocket = null;
	private final int NULL_BUFFER_SIZE = 1024;
	private byte[] nullBytes = new byte[NULL_BUFFER_SIZE]; 

	public ConnectCommand() {

	}

	@Override
	public double execute(String[] commAndArgs, SymbolTable st) {
		/*try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		st.connect = this;
		String ip = commAndArgs[1];
		int clientPort = Integer.parseInt(commAndArgs[2]);

		try {
			cSocket = new Socket(ip, clientPort);
			writer = new PrintWriter(cSocket.getOutputStream());
			System.out.println("connected to flight gear");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 2;
	}

	public void sendString(String s) {
		this.writer.write(s + "\r\n");
		this.writer.flush();
	}

	public void sendCommand(String s, Double val) {
		this.writer.write("set " + s + " " + val.toString() + "\r\n");
		System.out.println("set " + s + " " + val.toString() + "\r\n");
		this.writer.flush();
		try {
			while (this.cSocket.getInputStream().available() > 0)
				System.out.println(this.cSocket.getInputStream().read(this.nullBytes));
		} catch (IOException e) {}
	}
	
	
	
	private void close(Closeable c) {
		if(c != null) {
			try {
				c.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void closeServer() {
		this.close(writer);
		this.close(cSocket);
	}
}