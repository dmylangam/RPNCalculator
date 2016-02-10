package com.anz.rpn.calculator.operation;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

/**
 * @author deepamylangam
 *
 */
public interface IOperation {
	/**
	 * @param model
	 * @param currentOpInfo
	 * @throws InvalidModelException
	 * @throws InsufficientParameterException
	 */
	public void execute(RPNCalculatorModel model, OperationInfo currentOpInfo)
			throws InvalidModelException, InsufficientParameterException;
	
}
