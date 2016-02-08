package com.anz.rpn.calculator.model;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * @author deepamylangam
 *
 */
public class CalculatorHelper {

	public static final BigDecimal convertString(String val) {
		return new BigDecimal(val, new MathContext(15));
	}

	public static final String convertBigDecimal(BigDecimal decimal) {
		return decimal.toString();
	}

	public static final boolean isKnownOperation(String val) {
		return val.matches(CalculatorConstants.OPERAND_REGEX + "|" + CalculatorConstants.COMMAND_REGEX);
	}

	public static final boolean isOperand(String val) {
		return val.matches(CalculatorConstants.OPERAND_REGEX);
	}

	public static final boolean isSquareRootCommand(String val) {
		return val.matches(CalculatorConstants.SQRT_REGEX);
	}

	public static final boolean isClearCommand(String val) {
		return val.matches(CalculatorConstants.CLEAR_REGEX);
	}

	public static final boolean isUndoCommand(String val) {
		return val.matches(CalculatorConstants.UNDO_REGEX);
	}

	public static final boolean isNumber(String val) {
		return val.matches(CalculatorConstants.REAL_NUMBERS_REGEX);
	}

	public static final boolean validateEntry(String val) {
		return isNumber(val) || isKnownOperation(val);
	}

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

	public static final String peekPrevious(Stack<String> stack) {
		if (stack == null) {
			throw new IllegalArgumentException("Invalid argument: Null");
		}
		try {
			return stack.get(stack.size() - 2); // for previous
		} catch (Exception e) {
			return null;
		}
	}

	public static final int evaluateOperandPosition(int index) {
		if (index == 0) {
			index = 1;
		} else {
			index = index * 2 + 1; // add the space position
		}
		return index;
	}
}
