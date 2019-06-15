package expressions.math;

public class Plus extends MathBinaryExpression {

	public Plus(MathExpression left, MathExpression right) {
		super(left, right);
	}

	@Override
	public double calculate() {
		return left.calculate()+right.calculate();
	}

}
