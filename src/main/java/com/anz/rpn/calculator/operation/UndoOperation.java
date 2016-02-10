package com.anz.rpn.calculator.operation;

import java.util.Stack;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorUtil;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

/**
 * Represents the Undo operation
 * 
 * @author deepamylangam
 *
 */
public class UndoOperation extends AbstractOperation {

	private static IOperation operation = new UndoOperation();

	/**
	 * 
	 */
	private UndoOperation() {
		super(CalculatorConstants.UNDO_STR);
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
			handleUndo(model, currentOpInfo.getIndex(), currentOpInfo);
		}
	}

	/**
	 * @param model
	 * @param index
	 * @param currentOpInfo
	 * @throws InsufficientParameterException
	 * @throws InvalidModelException
	 */
	protected void handleUndo(RPNCalculatorModel model, int index, OperationInfo currentOpInfo)
			throws InsufficientParameterException, InvalidModelException {
		if (index == 0) {
			throw new InsufficientParameterException(currentOpInfo.getOperationValue(),
					currentOpInfo.getOperandPosition());
		}
		String currVal = model.getCompleteInputList().get(index - 1);

		// if currVal is Number, then just pop
		if (CalculatorUtil.isNumber(currVal)) {
			if (CalculatorUtil.peek(model.getStack()) == null) {
				throw new InsufficientParameterException(currentOpInfo.getOperationValue(),
						currentOpInfo.getOperandPosition());
			} else {
				model.getStack().pop();
			}
		} else if (CalculatorUtil.isUndoCommand(currVal)) { // if prev is also
															// undo then
															// just pop
			if (CalculatorUtil.peek(model.getStack()) == null) {
				throw new InsufficientParameterException(currentOpInfo.getOperationValue(),
						currentOpInfo.getOperandPosition());
			} else {
				model.getStack().pop();
			}
		} else { // if prev is any other operation, then try to run all the
					// previous operations from the beginning
			model.getStack().clear();
			redoAllOperations(model.getStack(), index - 1, model, currentOpInfo);
		}
	}

	/**
	 * @param newStack
	 * @param index
	 * @param model
	 * @param currentOpInfo
	 * @throws InsufficientParameterException
	 * @throws InvalidModelException
	 */
	protected void redoAllOperations(Stack<String> newStack, int index, RPNCalculatorModel model,
			OperationInfo currentOpInfo) throws InsufficientParameterException, InvalidModelException {

		for (int i = 0; i < index; i++) {
			String val = model.getCompleteInputList().get(i);
			if (CalculatorUtil.isKnownOperation(val)) {
				OperationInfo opInfo = new OperationInfo(i, i, val);
				// run the operations
				OperationFactory.getOperationObj(model, val).execute(model, opInfo);
			} else if (CalculatorUtil.isNumber(val)) {
				newStack.push(val);
			} else {
				// TODO error
			}
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
		return validateModelAndOperationInfo(model, currOpInfo);
	}

	/**
	 * @return
	 */
	protected static IOperation getInstance() {
		return operation;
	}
}
