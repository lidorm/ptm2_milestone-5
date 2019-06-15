package expressions.math;

public class Div extends MathBinaryExpression {

	public Div(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculate() {
		return left.calculate()/right.calculate();
	}

}
