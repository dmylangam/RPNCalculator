package com.anz.rpn.calculator.operation;

import java.math.BigDecimal;
import java.math.MathContext;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidInputException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorHelper;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

public class DivisionOperation extends AbstractOperation {

	private static IOperation operation = new DivisionOperation();

	private DivisionOperation() {
		super(CalculatorConstants.DIVISION_STR);
	}

	@Override
	public void execute(RPNCalculatorModel model, OperationInfo currentOpInfo)
			throws InvalidInputException, InvalidModelException, InsufficientParameterException {
		if (validateStackBeforeOperation(model, currentOpInfo)) {
			BigDecimal val1 = CalculatorHelper.convertString(model.getStack().pop());
			BigDecimal val2 = CalculatorHelper.convertString(model.getStack().pop());

			updateModel(model, val2.divide(val1, new MathContext(15)));
		}
	}

	@Override
	protected boolean validateStackBeforeOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InsufficientParameterException, InvalidModelException, InvalidInputException {
		if (validateModelAndOperationInfo(model, currOpInfo)) {

			if (model.getStack().size() < 2) {
				throw new InvalidInputException(
						" validation error" + CalculatorConstants.POSITION_STR + currOpInfo.getOperandPosition()
								+ CalculatorConstants.OPERATOR_STR + currOpInfo.getOperationValue());
			}
			String currValinStack = CalculatorHelper.peek(model.getStack());
			if (currValinStack == null) { // if not valid, then end the
											// loop
				throw new InsufficientParameterException(currOpInfo.getOperationValue(),
						currOpInfo.getOperandPosition());
			}
			String prevValinStack = CalculatorHelper.peekPrevious(model.getStack());
			if (prevValinStack == null) {
				model.getStack().push(currValinStack);
				throw new InsufficientParameterException(currOpInfo.getOperationValue(),
						currOpInfo.getOperandPosition());
			} else {
				if (CalculatorHelper.convertString(prevValinStack).intValue() == 0) {
					throw new InvalidInputException("0 at position: " + currOpInfo.getOperandPosition()
							+ " in Division operation is not allowed");
				}
				return true;
			}
		}
		return false;
	}

	protected static IOperation getInstance() {
		return operation;
	}
}
