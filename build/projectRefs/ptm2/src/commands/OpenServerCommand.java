package commands;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import server.SimValuePath;
import server.Symbol;
import server.SymbolTable;

public class OpenServerCommand implements Command {

	private ServerSocket server = null;
	private Socket c = null;
	private BufferedReader reader = null;
	private volatile boolean stop = false;
	private String[] args;

	public OpenServerCommand(String[] args) {
		this.args = args;
	}

	private synchronized String readLine() {
		try {
			String line = reader.readLine();
			return line;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public double execute(String[] commAndArgs, SymbolTable st) {
		int port = Integer.parseInt(commAndArgs[1]);
		int hz = Integer.parseInt(commAndArgs[2]);
		st.server = this;
		try {
			this.server = new ServerSocket(port);
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println("waiting for accept");
		try {
			this.c = this.server.accept();
			System.out.println("server has opened");
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Thread dataServerThread = new Thread(() -> {
			try {
				this.reader = new BufferedReader(new InputStreamReader(c.getInputStream()));
				String line = readLine();
				ConcurrentHashMap<String, SimValuePath> map = st.getPath();
				while (line != null && !stop) {
					String[] vals = line.split(",");
				//	System.out.println(line);
					for (int i = 0; i < vals.length; i++) {
					//	map.get(args[i]).setValue(Double.parseDouble(vals[i]));
						SimValuePath s = map.get(args[i]);
						s.setValue(Double.parseDouble(vals[i]));
			//	System.out.println("From Thread: " + vals[i] + " From Map: " + map.get(args[i]).getValue());
					}
					
					Thread.sleep((long) 1000 / hz);
					line = readLine();


				}
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		dataServerThread.start();

		return 2;
	}

	private void close(Closeable c) {
		if (c != null) {
			try {
				c.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void closeServer() {
		this.stop = true;
		try {
			Thread.sleep((long) 1000 / 10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.close(reader);
		this.close(c);
		this.close(server);
	}

}
