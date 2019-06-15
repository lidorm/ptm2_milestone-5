package expressions.bool;

import expressions.math.MathExpression;

public class EqualsTo extends BoolBinaryExpression {
	
	public EqualsTo(MathExpression left, MathExpression right) {
		super(left, right);
		
	}

	@Override
	public Boolean calculate() {
		//how to check if Doubles are equals..
		return (left.calculate() - right.calculate()) <= 1e-3;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EqualsTo))
			return false;
		EqualsTo other = (EqualsTo) obj;
		return left.equals(other.left) && right.equals(other.right);
		
	
	}

}