package expressions;

import expressions.math.MathExpression;

public class Number implements MathExpression{

	private double value;
	
	public Number(double value) {
		this.value=value;
	}
	
	public void setValue(double value){
		this.value=value;
	}

	@Override
	public double calculate() {
		return value;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Number))
			return false;
		Number other = (Number) obj;
		return Double.doubleToLongBits(value) == Double.doubleToLongBits(other.value);
	}
	
}
