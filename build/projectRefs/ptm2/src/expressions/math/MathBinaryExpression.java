package expressions.math;

public abstract class MathBinaryExpression implements MathExpression {

	protected MathExpression left,right;
	public MathBinaryExpression(MathExpression left,MathExpression right) {
		this.left=left;
		this.right=right;
	}
	
}