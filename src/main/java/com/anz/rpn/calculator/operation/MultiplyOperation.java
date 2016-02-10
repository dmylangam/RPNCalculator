package com.anz.rpn.calculator.operation;

import java.math.BigDecimal;
import java.math.MathContext;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorUtil;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

/**
 * Represents the Multiplication operation
 * 
 * @author deepamylangam
 *
 */
public class MultiplyOperation extends AbstractOperation {

	private static IOperation operation = new MultiplyOperation();

	/**
	 * 
	 */
	private MultiplyOperation() {
		super(CalculatorConstants.MULTIPLY_STR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.anz.rpn.calculator.operation.IOperation#execute(com.anz.rpn.
	 * calculator.model.RPNCalculatorModel,
	 * com.anz.rpn.calculator.model.OperationInfo)
	 */
	@Override
	public void execute(RPNCalculatorModel model, OperationInfo currentOpInfo)
			throws InvalidModelException, InsufficientParameterException {
		if (validateStackBeforeOperation(model, currentOpInfo)) {
			BigDecimal val1 = CalculatorUtil.convertString(model.getStack().pop());
			BigDecimal val2 = CalculatorUtil.convertString(model.getStack().pop());

			updateModel(model.getStack(), CalculatorUtil.convertBigDecimal(val2.multiply(val1, new MathContext(15))));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.anz.rpn.calculator.operation.AbstractOperation#
	 * validateStackBeforeOperation(com.anz.rpn.calculator.model.
	 * RPNCalculatorModel, com.anz.rpn.calculator.model.OperationInfo)
	 */
	@Override
	protected boolean validateStackBeforeOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InsufficientParameterException, InvalidModelException {
		if (validateModelAndOperationInfo(model, currOpInfo)) {

			if (model.getStack().size() < 2) {
				throw new InsufficientParameterException(currOpInfo.getOperationValue(),
						currOpInfo.getOperandPosition());
			}
			String currValinStack = CalculatorUtil.peek(model.getStack());
			String prevValinStack = CalculatorUtil.peekPrevious(model.getStack());
			if (currValinStack == null || prevValinStack == null || !CalculatorUtil.isNumber(currValinStack)
					|| !CalculatorUtil.isNumber(prevValinStack)) {
				throw new InsufficientParameterException(currOpInfo.getOperationValue(),
						currOpInfo.getOperandPosition());
			} else
				return true;

		}
		return false;
	}

	/**
	 * @return
	 */
	protected static IOperation getInstance() {
		return operation;
	}

}
