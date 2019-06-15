package expressions.math;

public class Minus extends MathBinaryExpression {

	public Minus(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculate() {
		return left.calculate()-right.calculate();
	}

}
