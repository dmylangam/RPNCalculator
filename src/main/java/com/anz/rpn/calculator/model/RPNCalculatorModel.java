package com.anz.rpn.calculator.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.logging.Logger;

import org.apache.commons.lang.StringUtils;

/**
 * Represents the calculator model to hold the input and the output.
 * 
 * @author deepamylangam
 *
 */
public class RPNCalculatorModel {

	private static final Logger logger = Logger.getLogger(RPNCalculatorModel.class.getName());

	private List<String> completeInputList = new ArrayList<String>();
	protected Stack<String> stack = new Stack<String>();

	/**
	 * @param inputLine
	 * @return
	 */
	public List<String> evaluateAndCreateInputArray(String inputLine) {
		return validateInputString(inputLine);
	}

	/**
	 * @return
	 */
	public Stack<String> getStack() {
		return stack;
	}

	/**
	 * @return
	 */
	public List<String> getCompleteInputList() {
		return completeInputList;
	}

	/**
	 * @param list
	 */
	public void addAll(List<String> list) {
		getCompleteInputList().addAll(list);
	}

	/**
	 * @return
	 */
	public String printOutput() {
		StringBuffer sb = new StringBuffer();
		sb.append(CalculatorConstants.STACK_STR).append(CalculatorConstants.SPACE_STR);
		for (int i = 0; i < stack.size(); i++) {
			sb.append(CalculatorUtil.getStackOutputForDisplay(stack.get(i))).append(CalculatorConstants.SPACE_STR);
		}
		return sb.toString().trim();
	}

	/**
	 * @param inputLine
	 * @return
	 */
	private List<String> validateInputString(String inputLine) {
		if (StringUtils.isEmpty(inputLine.trim())) {
			logger.warning(CalculatorConstants.NO_ENTRY_VALIDATION_ERR);
			throw new IllegalStateException(CalculatorConstants.NO_ENTRY_VALIDATION_ERR);
		} else {
			List<String> inputList = Arrays.asList(inputLine.split(" "));
			if (inputList.isEmpty()) {
				logger.warning(CalculatorConstants.EMPTY_SPACE_ENTRY_VALIDATION_ERR);
				throw new IllegalStateException(CalculatorConstants.EMPTY_SPACE_ENTRY_VALIDATION_ERR);
			}
			return inputList;
		}
	}
}
