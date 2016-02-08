package com.anz.rpn.calculator.model;

import java.util.List;
import java.util.Stack;

/**
 * @author deepamylangam
 *
 */
public class RPNCalculatorModel {
	protected List<String> input;
	protected Stack<String> stack = new Stack<String>();
	
	public List<String> getInput() {
		return input;
	}

	public void setInput(List<String> input) {
		this.input = input;
	}

	public Stack<String> getStack() {
		return stack;
	}

	public void setStack(Stack<String> stack) {
		this.stack = stack;
	}

	protected String printOutput() {
		StringBuffer sb = new StringBuffer();
		sb.append(CalculatorConstants.STACK_STR).append(CalculatorConstants.SPACE_STR);
		for (int i = 0; i < stack.size(); i++) {
			//TODO precision to 10 decimal
			sb.append(stack.get(i)).append(CalculatorConstants.SPACE_STR);
		}
		return sb.toString().trim();
	}
	
	protected boolean validate() {
		//TODO validate stack
		return false;
	}
}
