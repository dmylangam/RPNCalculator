package com.anz.rpn.calculator.operation;

import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorUtil;
import com.anz.rpn.calculator.model.RPNCalculatorModel;


/**
 * Represents Operation factory
 * @author deepamylangam
 *
 */
public class OperationFactory {

	/**
	 * @param model
	 * @param operandStr
	 * @return
	 */
	public static final IOperation getOperationObj(RPNCalculatorModel model, String operandStr) {
		if (!CalculatorUtil.isKnownOperation(operandStr)) {
			throw new IllegalStateException("Unknown operation: " + operandStr);
		}
		if (operandStr.equals(CalculatorConstants.ADD_STR)) {
			return AddOperation.getInstance();
		} else if (operandStr.equals(CalculatorConstants.SUBTRACT_STR)) {
			return SubtractOperation.getInstance();
		} else if (operandStr.equals(CalculatorConstants.DIVISION_STR)) {
			return DivisionOperation.getInstance();
		} else if (operandStr.equals(CalculatorConstants.MULTIPLY_STR)) {
			return MultiplyOperation.getInstance();
		} else if (CalculatorUtil.isUndoCommand(operandStr)) {
			return UndoOperation.getInstance();
		} else if (CalculatorUtil.isSquareRootCommand(operandStr)) {
			return SquarerootOperation.getInstance();
		} else if (CalculatorUtil.isClearCommand(operandStr)) {
			return ClearOperation.getInstance();
		} else {
			throw new UnsupportedOperationException("Unknown state in evaluating the operand");
		}
	}
}
