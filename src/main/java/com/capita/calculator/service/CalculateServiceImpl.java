package com.capita.calculator.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import com.capita.calculator.exception.InvalidExpressionException;
import com.capita.calculator.exception.ValidateInputException;
import com.capita.calculator.util.ConverterUtil;

public class CalculateServiceImpl implements ICalculateService {
	private static final Logger LOGGER = Logger.getLogger(CalculateServiceImpl.class);
	private static final String ERROR_MESSAGE = "Invalid Expression";

	public BigDecimal calculate(String inputExpression) throws InvalidExpressionException {

		// for validate coming expression
		final String regex = "[^A-Za-z]+";

		List<String> expression = new ArrayList<String>();
		Stack<Double> stack = new Stack<Double>();

		if (inputExpression.matches(regex)) {
			expression = ConverterUtil.convertExpression(inputExpression);
			LOGGER.debug("expression::" + expression);
		} else {
			throw new InvalidExpressionException("Please enter only numeric expression.");
		}

		// [ 4 3 + 2 0 / - ] [8, *, 7, +]
		for (int i = 0; i != expression.size(); i++) {
			// Determine if current element is digit or not
			if (Character.isDigit(expression.get(i).charAt(0))) {
				stack.push(Double.parseDouble(expression.get(i)));

			} else {
				double tempResult = 0;
				double value;

				switch (expression.get(i)) {

				case "^":
					value = stack.pop();
					tempResult = checkStackEmpty(value, stack, "^");
					break;

				case "/":
					value = stack.pop();
					tempResult = checkStackEmpty(value, stack, "/");
					break;

				case "*":
					value = stack.pop();
					tempResult = checkStackEmpty(value, stack, "*");
					break;

				case "+":
					value = stack.pop();
					tempResult = checkStackEmpty(value, stack, "+");
					break;

				case "-":
					value = stack.pop();
					tempResult = checkStackEmpty(value, stack, "-");
					break;
				}
				stack.push(tempResult);
			}
		}
		return new BigDecimal(stack.pop()).setScale(2);
	}

	// [ 4 3 + 2 0 / - ]
	public double checkStackEmpty(double value, Stack<Double> stack, String operator)
			throws InvalidExpressionException {
		double tempResult = 0;
		if (operator.equals("+")) {
			if (!stack.isEmpty()) {
				tempResult = stack.pop() + value;

			} else {
				throw new InvalidExpressionException(ERROR_MESSAGE);
			}
		} else if (operator.equals("-")) {
			if (!stack.isEmpty()) {
				tempResult = stack.pop() - value;

			} else {
				throw new InvalidExpressionException(ERROR_MESSAGE);
			}
		} else if (operator.equals("*")) {
			if (!stack.isEmpty()) {
				tempResult = stack.pop() * value;

			} else {
				throw new InvalidExpressionException(ERROR_MESSAGE);
			}
		} else if (operator.equals("/")) {
			if (!stack.isEmpty() && value != 0.0) {

				tempResult = stack.pop() / value;

			} else {
				if (value == 0.0) {
					throw new InvalidExpressionException(ERROR_MESSAGE, "Division By Zero!");
				}
				throw new InvalidExpressionException(ERROR_MESSAGE);

			}
			return tempResult;
		} else if (operator.equals("^")) {
			if (!stack.isEmpty()) {
				tempResult = Math.pow(stack.pop(), value);

			} else {
				throw new InvalidExpressionException(ERROR_MESSAGE);
			}
		}
		return tempResult;
	}

}
