package com.anz.rpn.calculator.operation;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidInputException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorHelper;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

public abstract class AbstractOperation implements IOperation {

	protected final String operandStr;

	AbstractOperation(String operandStr) {
		this.operandStr = operandStr;
	}

	protected final void updateModel(RPNCalculatorModel model, BigDecimal value) {
		model.getStack().push(CalculatorHelper.convertBigDecimal(value));
	}

	protected final boolean validateModelAndOperationInfo(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InvalidModelException {
		if (model == null || model.getStack() == null || model.getInput() == null || model.getInput().size() == 0
				|| currOpInfo == null || StringUtils.isEmpty(currOpInfo.getOperationValue())) {
			throw new IllegalArgumentException("Invalid Argument passed into the operation");
		}
		if (!currOpInfo.getOperationValue().equalsIgnoreCase(operandStr)) {
			throw new InvalidModelException(
					"before doing operation at " + CalculatorConstants.POSITION_STR + currOpInfo.getOperandPosition()
							+ CalculatorConstants.OPERATOR_STR + currOpInfo.getOperationValue());
		}
		return true;
	}

	protected abstract boolean validateStackBeforeOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InsufficientParameterException, InvalidModelException, InvalidInputException;

	protected boolean validateStackAfterOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InvalidModelException {
		int size = model.getStack().size();
		if (size > 0) {
			String val = model.getStack().get(size - 1);
			if (!CalculatorHelper.isNumber(val)) {
				throw new InvalidModelException(
						"after doing operation at " + CalculatorConstants.POSITION_STR + currOpInfo.getOperandPosition()
								+ CalculatorConstants.OPERATOR_STR + currOpInfo.getOperationValue());
			} else {
				return true;
			}
		}
		return false;
	}
}