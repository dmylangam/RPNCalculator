package com.anz.rpn.calculator.exception;

import com.anz.rpn.calculator.model.CalculatorConstants;

/**
 * @author deepamylangam
 *
 */
public class InvalidInputException extends CalculatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3147175531633370914L;

	public InvalidInputException(String msg) {
		setErrorMsg(CalculatorConstants.INVALID_INPUT_STR + msg);
	}

}
