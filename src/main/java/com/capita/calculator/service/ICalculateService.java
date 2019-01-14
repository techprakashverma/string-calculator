package com.capita.calculator.service;

import java.math.BigDecimal;

import com.capita.calculator.exception.InvalidExpressionException;

public interface ICalculateService {
	
	public BigDecimal calculate(String inputExpression) throws InvalidExpressionException;

}
