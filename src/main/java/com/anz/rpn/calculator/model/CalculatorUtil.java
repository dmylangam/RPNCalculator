package com.anz.rpn.calculator.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Helper methods to assist with the operation of the RPN calculator
 * 
 * @author deepamylangam
 *
 */
public class CalculatorUtil {

	/**
	 * @param val
	 * @return
	 * @throws NumberFormatException
	 */
	public static final BigDecimal convertString(String val) throws NumberFormatException {
		// to remove the trailing zeros and to avoid E^4
		BigDecimal decimal = new BigDecimal(val, MathContext.DECIMAL128).setScale(15, RoundingMode.CEILING)
				.stripTrailingZeros();
		return new BigDecimal(decimal.toPlainString());
	}

	/**
	 * @param decimal
	 * @return
	 */
	public static final String convertBigDecimal(BigDecimal decimal) {
		return decimal.toString();
	}

	/**
	 * @param val
	 * @return
	 */
	public static final boolean isKnownOperation(String val) {
		return val.matches(CalculatorConstants.OPERAND_REGEX + "|" + CalculatorConstants.COMMAND_REGEX);
	}

	/**
	 * @param val
	 * @return
	 */
	public static final boolean isOperand(String val) {
		return val.matches(CalculatorConstants.OPERAND_REGEX);
	}

	/**
	 * @param val
	 * @return
	 */
	public static final boolean isSquareRootCommand(String val) {
		return val.matches(CalculatorConstants.SQRT_REGEX);
	}

	/**
	 * @param val
	 * @return
	 */
	public static final boolean isClearCommand(String val) {
		return val.matches(CalculatorConstants.CLEAR_REGEX);
	}

	/**
	 * @param val
	 * @return
	 */
	public static final boolean isUndoCommand(String val) {
		return val.matches(CalculatorConstants.UNDO_REGEX);
	}

	/**
	 * @param val
	 * @return
	 */
	public static final boolean isNumber(String val) {
		return val.matches(CalculatorConstants.REAL_NUMBERS_REGEX);
	}

	/**
	 * @param val
	 * @return
	 */
	public static final boolean validateEntry(String val) {
		return isNumber(val) || isKnownOperation(val);
	}

	/**
	 * @param stack
	 * @return
	 */
	public static final String peek(Stack<String> stack) {
		if (stack == null) {
			throw new IllegalArgumentException("Invalid argument: Null");
		}
		try {
			return stack.peek();
		} catch (EmptyStackException e) {
			return null;
		}
	}

	/**
	 * @param stack
	 * @return
	 */
	public static final String peekPrevious(Stack<String> stack) {
		if (stack == null) {
			throw new IllegalArgumentException("Invalid argument: Null");
		}
		if (stack.size() - 2 < 0) {
			return null;
		}
		try {
			return stack.get(stack.size() - 2); // for previous
		} catch (Exception e) {
			return null; // return null and let the calling class handle it
		}
	}

	/**
	 * @param index
	 * @return
	 */
	public static final int evaluateOperandPosition(int index) {
		if (index == 0) {
			index = 1;
		} else {
			index = index * 2 + 1; // add the space position
		}
		return index;
	}

	/**
	 * @param val
	 * @return
	 */
	public static final String getStackOutputForDisplay(String val) {
		DecimalFormat df = new DecimalFormat(CalculatorConstants.TEN_DFORMAT_STR);
		return df.format(convertString(val));

	}
}
