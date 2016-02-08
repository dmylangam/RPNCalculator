package com.anz.rpn.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidInputException;
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
		List<String> completeInputList = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		while (true) {
			int size = completeInputList.size();
			String input = sc.nextLine().trim();
			try {
				calcModel.setInput(input);
				completeInputList.addAll(calcModel.getInput());
				computeAndPopulateStack(completeInputList, size);
			} catch (InvalidInputException ie) {
				System.out.println(ie.getErrorMsg());
			}
		}
	}

	protected void computeAndPopulateStack(List<String> inputList, int index) {
		try {
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
				if (!CalculatorHelper.isNumber(currVal)) { // if it is not a
															// number, then need
															// to
					OperationFactory.getOperationObj(calcModel, currVal).execute(calcModel, currOpInfo);

				} else if (CalculatorHelper.isNumber(currVal)) {
					calcModel.getStack().push(currVal);
				} else {
					System.out.println("Unknown operation"); // TODO log this
																// error
				}
			}
		} catch (InsufficientParameterException is) {
			System.out.println(is.getErrorMsg());
		} catch (InvalidInputException iv) {
			// System.out.println(iv.getErrorMsg()); //TODO log this error
		} catch (InvalidModelException iv) {
			// System.out.println(iv.getErrorMsg()); //TODO log this error
		} finally {
			System.out.println(calcModel.printOutput());
		}
	}
}
