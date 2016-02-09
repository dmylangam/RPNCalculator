package com.anz.rpn.calculator.operation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorHelper;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

public class UndoOperation extends AbstractOperation {

	private static IOperation operation = new UndoOperation();
	private List<IOperation> operationList = new ArrayList<IOperation>();

	private UndoOperation() {
		super(CalculatorConstants.UNDO_STR);
	}

	@Override
	public void execute(RPNCalculatorModel model, OperationInfo currentOpInfo)
			throws InvalidModelException, InsufficientParameterException {

		if (validateStackBeforeOperation(model, currentOpInfo)) {
			if (currentOpInfo.getIndex() - 1 < 0) {
				throw new InsufficientParameterException(currentOpInfo.getOperationValue(),
						currentOpInfo.getOperandPosition());
			} else handleUndo(model, currentOpInfo.getIndex() - 1, currentOpInfo, true);
		}
	}

	protected int handleUndo(RPNCalculatorModel model, int index, OperationInfo currentOpInfo, boolean undoOp)
			throws InsufficientParameterException, InvalidModelException {
		
		String currVal = model.getCompleteInputList().get(index);
		// if currVal is Number
		if ((CalculatorHelper.isNumber(currVal) || CalculatorHelper.isUndoCommand(currVal)) && undoOp) {
			model.getStack().pop();
		} else if (CalculatorHelper.isNumber(currVal)) {
			model.getStack().push(currVal);
			handleUndo(model, index - 1, currentOpInfo, false);
		} else if (CalculatorHelper.isOperand(currVal)) {
			// find two numbers before this
			// operation
			if (index >= 2) {
				String op1 = model.getCompleteInputList().get(index - 1);
				String op2 = model.getCompleteInputList().get(index - 2);
				if (CalculatorHelper.isNumber(op1) || CalculatorHelper.isNumber(op2)) {
					model.getStack().pop();
				}
				if (CalculatorHelper.isNumber(op1) && CalculatorHelper.isNumber(op2)) {
					model.getStack().push(op2);
					model.getStack().push(op1);
				} else {
					int counter = 0;
					if (!CalculatorHelper.isNumber(op1)) {
						counter = index - 1;
					} else {
						counter = index - 2;
					}
					if (CalculatorHelper.isOperand(op1) || CalculatorHelper.isOperand(op2)) {
						handleUndo(model, counter, currentOpInfo, false);
						OperationFactory.getOperationObj(model, currVal).execute(model, currentOpInfo);
					} else if(CalculatorHelper.isSquareRootCommand(currVal) || CalculatorHelper.isUndoCommand(currVal)) {
						handleUndo(model, counter, currentOpInfo, false);
					}
					if (CalculatorHelper.isNumber(op2)) {
						model.getStack().push(op2);
					}
					if (CalculatorHelper.isNumber(op1)) {
						model.getStack().push(op1);
					}
				}
			} else {
				throw new InsufficientParameterException(currentOpInfo.getOperationValue(), currentOpInfo.getOperandPosition());
			}
		} else if (CalculatorHelper.isSquareRootCommand(currVal)) {
			if (CalculatorHelper.peek(model.getStack()) == null) {
				throw new InsufficientParameterException(currentOpInfo.getOperationValue(),
						currentOpInfo.getOperandPosition());
			} else {
				String op1 = model.getCompleteInputList().get(index - 1);
				if (CalculatorHelper.isNumber(op1)) { // if it is number, then
														// only pop the sqrt
														// result and and push
														// the orig
					model.getStack().pop();
					model.getStack().push(op1);
				} else {
					if (handleUndo(model, index - 1, currentOpInfo, false) == 1) {
						SquarerootOperation.getInstance().execute(model, currentOpInfo);
					}
				}
			}
		} else if (CalculatorHelper.isUndoCommand(currVal)) {
			String op1 = model.getCompleteInputList().get(index - 1);
			if (CalculatorHelper.isNumber(op1)) {
				model.getStack().push(op1);
				handleUndo(model, index - 1, currentOpInfo, false);
			} else if (CalculatorHelper.isUndoCommand(op1)) {
				handleUndo(model, index - 1, currentOpInfo, false);
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

	@Override
	public BigDecimal evaluate(BigDecimal... value) {
		return null;
	}
}
