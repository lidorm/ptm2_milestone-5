package commands.conditions;

import java.util.ArrayList;
import expressions.Number;
import expressions.bool.BoolBinaryExpression;
import expressions.math.MathExpression;
import interpeter.Parser;
import server.SymbolTable;

public class IfCommand extends ConditionParser {

	MathExpression left,right;
	
	@Override
	public double execute(String[] commAndArgs, SymbolTable st) {
		return 0;
		
	}
}
