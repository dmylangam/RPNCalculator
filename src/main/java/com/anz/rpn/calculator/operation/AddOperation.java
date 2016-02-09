package com.anz.rpn.calculator.operation;

import java.math.BigDecimal;
import java.math.MathContext;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorHelper;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

public class AddOperation extends AbstractOperation {

	private static IOperation operation = new AddOperation();

	private AddOperation() {
		super(CalculatorConstants.ADD_STR);
	}

	@Override
	public void execute(RPNCalculatorModel model, OperationInfo currentOpInfo)
			throws InvalidModelException, InsufficientParameterException {
		if (validateStackBeforeOperation(model, currentOpInfo)) {
			BigDecimal val1 = CalculatorHelper.convertString(model.getStack().pop());
			BigDecimal val2 = CalculatorHelper.convertString(model.getStack().pop());

			updateModel(model, val1.add(val2, new MathContext(15)));
		}
	}

	public void evaluate(BigDecimal val1, BigDecimal val2) {
		val1.add(val2, new MathContext(15));
	}

	@Override
	protected boolean validateStackBeforeOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InsufficientParameterException, InvalidModelException {
		if (validateModelAndOperationInfo(model, currOpInfo)) {

			if (model.getStack().size() < 2) {
				throw new InsufficientParameterException(currOpInfo.getOperationValue(),
						currOpInfo.getOperandPosition());
			}
			String currValinStack = CalculatorHelper.peek(model.getStack());
			String prevValinStack = CalculatorHelper.peekPrevious(model.getStack());
			if (currValinStack == null || prevValinStack == null || !CalculatorHelper.isNumber(currValinStack)
					|| !CalculatorHelper.isNumber(prevValinStack)) {
				throw new InsufficientParameterException(currOpInfo.getOperationValue(),
						currOpInfo.getOperandPosition());
			} else {
				return true;
			}

		}
		return false;
	}

	protected static IOperation getInstance() {
		return operation;
	}
}
