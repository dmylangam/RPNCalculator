package com.anz.rpn.calculator.exception;

import com.anz.rpn.calculator.model.CalculatorConstants;

/**
 * @author deepamylangam
 *
 */
public class InsufficientParameterException extends CalculatorException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7541308640186967885L;

	private String value;
	private int position;

	public InsufficientParameterException(String aValue, int index) {
		this.value = aValue;
		this.position = index;
		setErrorMsg(generateErrorMsg());
	}

	public String generateErrorMsg() {
		StringBuffer sb = new StringBuffer();
		sb.append(CalculatorConstants.OPERATOR_STR).append(CalculatorConstants.SPACE_STR).append(value)
				.append(CalculatorConstants.SPACE_STR);
		sb.append(CalculatorConstants.OPEN_BRACKET_STR).append(CalculatorConstants.POSITION_STR)
				.append(CalculatorConstants.SPACE_STR).append(position);
		sb.append(CalculatorConstants.CLOSE_BRACKET_STR).append(CalculatorConstants.INSUFF_PARAMETERS_STR);
		return sb.toString().trim();
	}
}
