package com.capita.calculator.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.capita.calculator.exception.InvalidExpressionException;

public class ConverterUtil {
	protected static final Logger LOGGER = Logger.getLogger(ConverterUtil.class);

	// Incoming expression to be converted
	private static Stack<Character> stack = new Stack<Character>();

	// for hold the converted expression
	private static List<String> convertedExpression = new ArrayList<String>();

	public static List<String> convertExpression(String expression) throws InvalidExpressionException {//8*+7
		for (int i = 0; i != expression.length(); i++)
		{
			if (Character.isDigit(expression.charAt(i))) {
				StringBuilder operandBuilder = new StringBuilder();
				operandBuilder.append(expression.charAt(i));

				while ((i + 1) != expression.length()
						&& (Character.isDigit(expression.charAt(i + 1)) || expression.charAt(i + 1) == '.')) {
					operandBuilder.append(expression.charAt(i++));
				}

				convertedExpression.add(operandBuilder.toString());
			}

			else
				inputToStack(expression.charAt(i));
		}
		clearDownStack();
		return convertedExpression;
	}

	private static void inputToStack(char input) throws InvalidExpressionException {
		if (stack.isEmpty() || (input == '(' && !stack.peek().equals('(')))
			stack.push(input);
		else {
			if (input == ')') {
				while (!stack.peek().equals('(')) {
					convertedExpression.add(stack.pop().toString());
				}
				stack.pop();
			} 
			else {
				if (stack.peek().equals('(')) {
					if (input != '(')
						stack.push(input);
					else
						throw new InvalidExpressionException("Invalid Expression");
				}

				else {
					while (!stack.isEmpty() && !stack.peek().equals('(')
							&& getOperatorPrecedence(input) <= getOperatorPrecedence(stack.peek())) {
						
						convertedExpression.add(stack.pop().toString());
					}
					stack.push(input);
				}
			}
		}
	}

	private static int getOperatorPrecedence(char operator) {
		if (operator == '+' || operator == '-')
			return 1;
		else if (operator == '*' || operator == '/')
			return 2;
		else if (operator == '^')
			return 3;
		else
			return 0;
	}

	private static void clearDownStack() {
		while (!stack.isEmpty()) {
			convertedExpression.add(stack.pop().toString());
		}
	}

}
