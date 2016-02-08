package com.anz.rpn.calculator.operation;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidInputException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorHelper;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

public class UndoOperation extends AbstractOperation {

	private static IOperation operation = new UndoOperation();

	private UndoOperation() {
		super(CalculatorConstants.UNDO_STR);
	}

	@Override
	public void execute(RPNCalculatorModel model, OperationInfo currentOpInfo)
			throws InvalidInputException, InvalidModelException, InsufficientParameterException {

		if (validateStackBeforeOperation(model, currentOpInfo)) {
			handleUndoOperation(model, currentOpInfo.getIndex(), currentOpInfo);
		}
	}

	protected int handleUndoOperation(RPNCalculatorModel model, int index, OperationInfo currentOpInfo)
			throws InvalidInputException, InsufficientParameterException, InvalidModelException {
		String currVal = model.getInput().get(index);
		if (CalculatorHelper.isNumber(currVal)) { // if prev is number, then
													// just pop it
			model.getStack().pop();
		} else if (CalculatorHelper.isOperand(currVal)) { // if it is some
															// operation, u need
															// to
			// find two numbers before this
			// operation
			if (index >= 2) {
				boolean donePrevVal = false;
				String op1 = model.getInput().get(index - 1);
				String op2 = model.getInput().get(index - 2);
				if (CalculatorHelper.isNumber(op1) || CalculatorHelper.isNumber(op2)) {
					model.getStack().pop();
				}
				if (!CalculatorHelper.isNumber(op1)) {
					handleUndoOperation(model, index - 1, currentOpInfo);
					donePrevVal = true; // this is a pointer to check if prevVal
										// has looked at and operation handled
				}
				if (!CalculatorHelper.isNumber(op2) && !donePrevVal) {
					handleUndoOperation(model, index - 2, currentOpInfo);
				}
				if (CalculatorHelper.isNumber(op2)) {
					model.getStack().push(op2);
				}
				if (CalculatorHelper.isNumber(op1)) {
					model.getStack().push(op1);
				}

			} else {
				throw new InsufficientParameterException(currVal, currentOpInfo.getOperandPosition());
			}
		} else if (CalculatorHelper.isSquareRootCommand(currVal)) {
			if (CalculatorHelper.peek(model.getStack()) == null) {
				throw new InsufficientParameterException(currVal, currentOpInfo.getOperandPosition());
			} else {
				String op1 = model.getInput().get(index - 1);
				if (CalculatorHelper.isNumber(op1)) { // if it is number, then
														// only pop the sqrt
														// result and and push
														// the orig
					model.getStack().pop();
					model.getStack().push(op1);
				} else {
					if (handleUndoOperation(model, index - 1, currentOpInfo) == 1) {
						SquarerootOperation.getInstance().execute(model, currentOpInfo);
					}
				}
			}
		} else if (CalculatorHelper.isUndoCommand(currVal)) { // if prev is also
																// undo, then
																// peek
			// to see if a number exists in
			// the stack
			if (CalculatorHelper.peek(model.getStack()) == null) {
				throw new InsufficientParameterException(currVal, currentOpInfo.getOperandPosition());
			} else {
				String op1 = model.getInput().get(index - 1);
				if (CalculatorHelper.isUndoCommand(op1)) {
					model.getStack().pop();
				} else {
					handleUndoOperation(model, index - 1, currentOpInfo);
				}
			}
		}
		return 1;
	}

	@Override
	protected boolean validateStackBeforeOperation(RPNCalculatorModel model, OperationInfo currOpInfo)
			throws InsufficientParameterException, InvalidModelException {
		return validateModelAndOperationInfo(model, currOpInfo);
	}

	protected static IOperation getInstance() {
		return operation;
	}
}
