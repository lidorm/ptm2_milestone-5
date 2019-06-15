package expressions.bool;

import java.util.HashMap;

import expressions.math.MathExpression;

public abstract class BoolBinaryExpression implements BooleanExpression {

	protected MathExpression left,right;
	protected HashMap<String, BoolBinaryExpression> booleanSymbolsMap;
	public BoolBinaryExpression(MathExpression left,MathExpression right) {
		this.left=left;
		this.right=right;
		
		this.booleanSymbolsMap = new HashMap<>();
		
		this.booleanSymbolsMap.put("==", new EqualsTo(left, right));
		this.booleanSymbolsMap.put(">=", new BiggerEquals(left, right));
		this.booleanSymbolsMap.put("<=", new SmallerEquals(left, right));
		this.booleanSymbolsMap.put("!=", new NotEquals(left, right));
		this.booleanSymbolsMap.put(">", new BiggerThan(left, right));
		this.booleanSymbolsMap.put("<", new SmallerThan(left, right));
		
	}

}
