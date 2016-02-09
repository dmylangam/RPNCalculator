package com.anz.rpn.calculator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

/**
 * @author deepamylangam
 *
 */
public class RPNCalculatorModel {

	private List<String> completeInputList = new ArrayList<String>();
	protected Stack<String> stack = new Stack<String>();

	public List<String> evaluateAndCreateInputArray(String inputLine) {
		return validateInputString(inputLine);
	}

	public Stack<String> getStack() {
		return stack;
	}

	public List<String> getCompleteInputList() {
		return completeInputList;
	}

	public void addAll(List<String> list) {
		getCompleteInputList().addAll(list);
	}

	public String printOutput() {
		StringBuffer sb = new StringBuffer();
		sb.append(CalculatorConstants.STACK_STR).append(CalculatorConstants.SPACE_STR);
		for (int i = 0; i < stack.size(); i++) {
			sb.append(CalculatorHelper.getStackOutputForDisplay(stack.get(i))).append(CalculatorConstants.SPACE_STR);
		}
		return sb.toString().trim();
	}

	private List<String> validateInputString(String inputLine) {
		if (StringUtils.isEmpty(inputLine.trim())) {
			throw new IllegalStateException("No entry");
		} else {
			List<String> inputList = Arrays.asList(inputLine.split(" "));
			if (inputList.isEmpty()) {
				throw new IllegalStateException("Blank space entry is invalid");
			}
			return inputList;
		}
	}
}
