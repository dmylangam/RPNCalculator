package com.anz.rpn.calculator.exception;

import com.anz.rpn.calculator.model.CalculatorConstants;

/**
 * @author deepamylangam
 *
 */
public class InvalidModelException extends CalculatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3147175531633370914L;

	public InvalidModelException(String msg) {
		setErrorMsg(CalculatorConstants.INVALID_MODEL_STR + msg);
	}

}
