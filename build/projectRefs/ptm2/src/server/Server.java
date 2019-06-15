package server;

import java.util.HashMap;


public interface Server {
	
	void listen(int port);
    HashMap<String, Double> getData();
    
}
