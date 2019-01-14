package com.capita.calculator;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.capita.calculator.exception.InvalidExpressionException;
import com.capita.calculator.service.CalculateServiceImpl;
import com.capita.calculator.service.ICalculateService;

public class CalculateServiceTest {

	@Test
	public void testCalculateSuccess() throws InvalidExpressionException {
		ICalculateService calculateService = new CalculateServiceImpl();
		BigDecimal bigDecimal = calculateService.calculate("(8*5/8)-(3/1)-5");
		assertEquals(new BigDecimal(-3.00).setScale(2), bigDecimal);
	}

	@Rule
	public ExpectedException thrown;

	@Test(expected = InvalidExpressionException.class)
	public void testCalculateFailInvalidExpression_1() throws InvalidExpressionException {
		ICalculateService calculateService = new CalculateServiceImpl();
		thrown = ExpectedException.none();
		// Complete this test case
		thrown.expect(InvalidExpressionException.class);
		thrown.expectMessage("Invalid Expression");
		calculateService.calculate("7+(67(56*2))");
	}

	@Test(expected = InvalidExpressionException.class)
	public void testCalculateFailInvalidExpression_2() throws InvalidExpressionException {
		ICalculateService calculateService = new CalculateServiceImpl();
		thrown = ExpectedException.none();
		thrown.expect(InvalidExpressionException.class);
		thrown.expectMessage("Invalid Expression");
		calculateService.calculate("7+(67(56*2))");
	}

	@Test(expected = InvalidExpressionException.class)
	public void testCheckNumericExpression() throws InvalidExpressionException {
		ICalculateService calculateService = new CalculateServiceImpl();
		thrown = ExpectedException.none();
		thrown.expect(InvalidExpressionException.class);
		calculateService.calculate("(8*5/8)-(3/1)-5+a");

	}
}
