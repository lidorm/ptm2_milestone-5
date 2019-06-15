package server;

import java.util.LinkedList;
import java.util.List;

public class Symbol {

	String varName;
	//Double value;
	SimValuePath simValuePath;

	public Symbol(String varName, SimValuePath simValPath) {
		super();
		this.varName = varName;
	//	this.value = value;
		this.simValuePath = simValPath;
	}

	public String getVarName() {
		return varName;
	}

	public void setVarName(String varName) {
		this.varName = varName;
	}

	 /* public Double getValue() {
		return value;
	} */

	public SimValuePath getSimValuePath() {
		return simValuePath;
	}

	public void setSimValuePath(SimValuePath simValuePath) {
		this.simValuePath = simValuePath;
	}

	/*public void setValue(Double value) {
		this.value = value;
		if(this.simValuePath != null) {
			this.simValuePath.setValue(value);
		} 
	} */
	
}


