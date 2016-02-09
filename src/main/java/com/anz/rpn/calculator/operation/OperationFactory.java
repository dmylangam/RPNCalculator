package com.anz.rpn.calculator.operation;

import com.anz.rpn.calculator.model.CalculatorHelper;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

/**
 * @author deepamylangam
 *
 */
public class OperationFactory {

	public static final IOperation getOperationObj(RPNCalculatorModel model, String operandStr) {
		if (!CalculatorHelper.isKnownOperation(operandStr)) {
			throw new IllegalStateException("Unknown operation: " + operandStr);
		}
		if (CalculatorHelper.isOperand(operandStr)) {
			switch (operandStr.charAt(0)) {
			case '+':
				return AddOperation.getInstance();
			case '-':
				return SubtractOperation.getInstance();
			case '/':
				return DivisionOperation.getInstance();
			case '*':
				return MultiplyOperation.getInstance();
			default:
				return null;
			}
		} else if (CalculatorHelper.isUndoCommand(operandStr)) {
			return UndoOperation.getInstance();
		} else if (CalculatorHelper.isSquareRootCommand(operandStr)) {
			return SquarerootOperation.getInstance();
		} else if (CalculatorHelper.isClearCommand(operandStr)) {
			return ClearOperation.getInstance();
		} else {
			throw new IllegalStateException("Unknown state in evaluating the operand");
		}
	}
}
