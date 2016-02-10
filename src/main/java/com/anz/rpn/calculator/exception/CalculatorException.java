package com.anz.rpn.calculator.exception;

/**
 * @author deepamylangam
 *
 */
public abstract class CalculatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4395713874082864469L;

	private String errorMsg;

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	
}
