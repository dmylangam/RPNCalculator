package com.anz.rpn.calculator.operation;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidInputException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

public class ClearOperation extends AbstractOperation {

	private static IOperation operation = new ClearOperation();

	private ClearOperation() {
		super(CalculatorConstants.CLEAR_STR);
	}

	@Override
	public void execute(RPNCalculatorModel model, OperationInfo currentOpInfo)
			throws InvalidInputException, InvalidModelException, InsufficientParameterException {
		if (validateStackBeforeOperation(model, currentOpInfo)) {
			model.getStack().clear();
		}
	}

	@Override
	protected boolean validateStackBeforeOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InsufficientParameterException, InvalidModelException {
		if (validateModelAndOperationInfo(model, currOpInfo)) {
			if (model.getStack().size() == 0) {
				throw new InsufficientParameterException(currOpInfo.getOperationValue(),
						currOpInfo.getOperandPosition());
			}
		}
		return true;
	}

	protected static IOperation getInstance() {
		return operation;
	}
}
