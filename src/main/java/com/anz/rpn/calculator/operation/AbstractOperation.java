package com.anz.rpn.calculator.operation;

import java.util.Stack;

import org.apache.commons.lang.StringUtils;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorUtil;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

/**
 * Abstract class to handle the operations of the calculator.
 * 
 * @author deepamylangam
 *
 */
public abstract class AbstractOperation implements IOperation {

	protected final String operandStr;

	AbstractOperation(String operandStr) {
		this.operandStr = operandStr;
	}

	/**
	 * @param stack
	 * @param value
	 */
	protected final void updateModel(Stack<String> stack, String value) {
		stack.push(value);
	}

	/**
	 * @param model
	 * @param currOpInfo
	 * @return
	 * @throws InsufficientParameterException
	 */
	protected final boolean validateModelAndOperationInfo(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InsufficientParameterException {
		if (model == null || model.getStack() == null || model.getCompleteInputList() == null
				|| model.getCompleteInputList().size() == 0 || currOpInfo == null
				|| StringUtils.isEmpty(currOpInfo.getOperationValue())) {
			throw new InsufficientParameterException(currOpInfo.getOperationValue(), currOpInfo.getOperandPosition());
		}
		if (!currOpInfo.getOperationValue().equalsIgnoreCase(operandStr)) {
			throw new InsufficientParameterException(currOpInfo.getOperationValue(), currOpInfo.getOperandPosition());
		}
		return true;
	}

	/**
	 * @param model
	 * @param currOpInfo
	 * @return
	 * @throws InsufficientParameterException
	 * @throws InvalidModelException
	 */
	protected abstract boolean validateStackBeforeOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InsufficientParameterException, InvalidModelException;

	/**
	 * @param model
	 * @param currOpInfo
	 * @return
	 * @throws InvalidModelException
	 */
	protected boolean validateStackAfterOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InvalidModelException {
		int size = model.getStack().size();
		if (size > 0) {
			String val = model.getStack().get(size - 1);
			if (!CalculatorUtil.isNumber(val)) {
				throw new UnsupportedOperationException(
						"Invalid entry at " + CalculatorConstants.POSITION_STR + currOpInfo.getOperandPosition());
			} else {
				return true;
			}
		}
		return false;
	}
}
