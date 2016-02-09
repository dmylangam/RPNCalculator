package com.anz.rpn.calculator;

import java.util.List;
import java.util.Scanner;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorHelper;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;
import com.anz.rpn.calculator.operation.OperationFactory;

/**
 * This is the main class to run the RPNCalculator
 * 
 * @author deepamylangam
 *
 */
public class Calculator {

	private RPNCalculatorModel calcModel = new RPNCalculatorModel();

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.runCalculator();
	}

	public void runCalculator() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				int size = calcModel.getCompleteInputList().size();
				String input = sc.nextLine().trim();
				calcModel.addAll(calcModel.evaluateAndCreateInputArray(input));
				calcModel = computeAndPopulateStack(calcModel, size);
			} catch (InsufficientParameterException is) {
				System.out.println(is.getErrorMsg());
			} catch (InvalidModelException iv) {
				// System.out.println(iv.getErrorMsg()); //TODO log this error
			} finally {
				System.out.println(calcModel.printOutput());
			}
		}
	}

	protected RPNCalculatorModel getModel() {
		return calcModel;
	}

	protected RPNCalculatorModel computeAndPopulateStack(RPNCalculatorModel model, int index)
			throws InsufficientParameterException, InvalidModelException {
		List<String> inputList = model.getCompleteInputList();
		for (int i = index; i < inputList.size(); i++) {
			int pointerPos = i - index;
			String currVal = inputList.get(i);
			OperationInfo currOpInfo = new OperationInfo(i, CalculatorHelper.evaluateOperandPosition(pointerPos),
					currVal);
			if (!CalculatorHelper.validateEntry(currVal)) { // check if it
															// is a valid
															// entry
				throw new InsufficientParameterException(currVal, index);
			}
			if (CalculatorHelper.isKnownOperation(currVal)) { // if it is not a
														// number, then need
														// to
				OperationFactory.getOperationObj(model, currVal).execute(model, currOpInfo);

			} else if (CalculatorHelper.isNumber(currVal)) {
				model.getStack().push(currVal);
			} else {
				System.out.println("Unknown operation"); // TODO log this
															// error
			}

		}
		return model;
	}
}
