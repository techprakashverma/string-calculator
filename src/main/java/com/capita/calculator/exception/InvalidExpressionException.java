package com.capita.calculator.exception;


/**
 * This class is used to wrap exceptions that should not be handled by the app - i.e. Application Failed badly!
 * @author pverma
 */
public class InvalidExpressionException extends Exception {
	
	private String reason;
	
	/**
	 * Constructor for without param.
	 * 
	 */
	public InvalidExpressionException() {
		//no-code
	}
	
	/**
	 * Constructor for ApplicationException.
	 * @param s
	 */
	public InvalidExpressionException(String msg) {
		super(msg);
	}
	
	/**
	 * Constructor for ApplicationException.
	 * @param s
	 */
	public InvalidExpressionException(String msg, String reason) {
		super(msg);
		this.reason = reason;
	}

	
	/**
	 * Constructor for ApplicationException.
	 * @param s
	 * @param ex
	 */
	public InvalidExpressionException(String msg, Throwable ex) {
		super(msg, ex);
	}
	
	
	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
