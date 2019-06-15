package expressions.bool;

import expressions.math.MathExpression;

public class BiggerEquals extends BoolBinaryExpression{

	public BiggerEquals(MathExpression left, MathExpression right) {
		super(left, right);
		
	}

	@Override
	public Boolean calculate() {
		
		return left.calculate() >= right.calculate();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof BiggerEquals))
			return false;
		BiggerEquals other = (BiggerEquals) obj;
		return left.equals(other.left) && right.equals(other.right);
		
	}
	
}
