package com.anz.rpn.calculator.operation;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

/**
 * @author deepamylangam
 *
 */
public interface IOperation {
	public void execute(RPNCalculatorModel model) throws InsufficientParameterException;
}
