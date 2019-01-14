package com.capita.calculator.client;

import java.io.IOException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.capita.calculator.exception.InvalidExpressionException;
import com.capita.calculator.service.CalculateServiceImpl;
import com.capita.calculator.service.ICalculateService;



public class Calculator_client {
	private static final Logger LOGGER = Logger.getLogger(Calculator_client.class);
	static String expression;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) throws IOException {
		System.out.print("Please enter expression: ");
		expression = scanner.nextLine();

		ICalculateService calculateService = new CalculateServiceImpl();
		try {
			LOGGER.debug("Calcutaled Result:: " + calculateService.calculate(expression));
		} catch (InvalidExpressionException e) {
			LOGGER.error(e.getMessage());
			String reason = e.getReason();
			if (reason != null && !reason.isEmpty()) {
				LOGGER.error("Reason= " + reason);
			}
		}
	}

}
