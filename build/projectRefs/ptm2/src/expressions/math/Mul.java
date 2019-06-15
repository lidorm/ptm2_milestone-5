package expressions.math;

public class Mul extends MathBinaryExpression {

	public Mul(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculate() {
		return left.calculate()*right.calculate();
	}

}
