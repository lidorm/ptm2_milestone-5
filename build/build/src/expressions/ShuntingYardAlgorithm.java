package expressions;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import expressions.math.Div;
import expressions.math.MathExpression;
import expressions.math.Minus;
import expressions.math.Mul;
import expressions.math.Plus;
import server.Symbol;
import server.SymbolTable;

public class ShuntingYardAlgorithm {
	
	public static double calc(String[] exp,HashMap<String, Symbol> s2) {
		Queue<String> queue = new LinkedList<String>();
		Stack<String> stack = new Stack<String>();
		Stack<MathExpression> stackExp = new Stack<MathExpression>();

		//(?<=[-+*/()])|(?=[-+*/()])
		String[] split = exp;
		for (String s : split) {
			if (isDouble(s)) {
				queue.add(s);
			} else {
				switch (s) {
				case " ":
					break;
				case "return":
					break;
				case "/":
				case "*":
				case "(":
					stack.push(s);
					break;
				case "+":
				case "-":
					while (!stack.empty() && (!stack.peek().equals("("))) {
						queue.add(stack.pop());
					}
					stack.push(s);
					break;
				case ")":
					while (!stack.peek().equals("(")) {
						queue.add(stack.pop());
					}
					stack.pop();
					break;
				default:
					if(s2.containsKey(s)) {
						stackExp.push(new Number(s2.get(s).getSimValuePath().getValue()));
					}
				}
			}
		}
		while (!stack.isEmpty()) {
			queue.add(stack.pop());
		}

		for (String str : queue) {
			if (isDouble(str)) {
				stackExp.push(new Number(Double.parseDouble(str)));
			} else {
				MathExpression right = stackExp.pop();
				MathExpression left = stackExp.pop();

				switch (str) {
				case "/":
					stackExp.push(new Div(left, right));
					break;
				case "*":
					stackExp.push(new Mul(left, right));
					break;
				case "+":
					stackExp.push(new Plus(left, right));
					break;
				case "-":
					stackExp.push(new Minus(left, right));
					break;
				}
			}
		}
		
		for (String string : exp) {
			System.out.println(string);
		}
		System.out.println("Shunting yard result:" + Math.floor((stackExp.peek().calculate() * 1000)) / 1000);
		return Math.floor((stackExp.pop().calculate() * 1000)) / 1000;
	}

	private static boolean isDouble(String val) {
		try {
			Double.parseDouble(val);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
