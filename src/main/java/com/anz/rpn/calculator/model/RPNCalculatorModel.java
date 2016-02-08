package com.anz.rpn.calculator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.anz.rpn.calculator.exception.InvalidInputException;

/**
 * @author deepamylangam
 *
 */
public class RPNCalculatorModel {

	private List<String> input = new ArrayList<String>();
	protected Stack<String> stack = new Stack<String>();
	private int operandPosition = 0;
	
	public void setInput(String inputLine) throws InvalidInputException {
		validateInputString(inputLine);
	}

	public Stack<String> getStack() {
		return stack;
	}

	public final List<String> getInput() {
		return input;
	}

	public int getOperandPosition() {
		return operandPosition;
	}

	public void setOperandPosition(int operandPosition) {
		this.operandPosition = operandPosition;
	}

	public String printOutput() {
		StringBuffer sb = new StringBuffer();
		sb.append(CalculatorConstants.STACK_STR).append(CalculatorConstants.SPACE_STR);
		for (int i = 0; i < stack.size(); i++) {
			// TODO precision to 10 decimal
			sb.append(stack.get(i)).append(CalculatorConstants.SPACE_STR);
		}
		return sb.toString().trim();
	}

	protected boolean validate() {
		// TODO validate stack
		return false;
	}

	private void validateInputString(String inputLine) throws InvalidInputException {
		if (StringUtils.isEmpty(inputLine.trim())) {
			throw new InvalidInputException("No entry");
		} else {
			input = Arrays.asList(inputLine.split(" "));
			if (input.isEmpty()) {
				throw new InvalidInputException("Blank space entry is invalid");
			}
		}
	}
}
