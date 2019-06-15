package server;

public class SimValuePath {

	
	private String path;
	private Double value;
	
	
	public SimValuePath(String p,double v) {
		this.path = p;
		this.value = v;
		
	}

	

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
		
	}
	
	
	
}
