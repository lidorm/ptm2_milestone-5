package client;

public interface Client {
	
    void connect(String ip, int port);
    void set(String path, Double value);
    void close();
    
}