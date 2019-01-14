package com.capita.calculator.exception;

public class ValidateInputException extends Exception {

	/**
	 * Constructor for without @param.
	 * 
	 */
	public ValidateInputException() {
		// no-code
	}

	/**
	 * Constructor for ApplicationException.
	 * 
	 * @param s
	 */
	public ValidateInputException(String msg) {
		super(msg);
	}

	/**
	 * Constructor for ApplicationException.
	 * 
	 * @param s
	 * @param ex
	 */
	public ValidateInputException(String msg, Throwable ex) {
		super(msg, ex);
	}

}
