package com.anz.rpn;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.CalculatorConstants;
import com.anz.rpn.calculator.model.CalculatorUtil;
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
	private static Logger logger = Logger.getLogger(Calculator.class.getName());
	private static final String LOG_FILE = "rpn-calc.log";

	static {
		try {
			FileHandler fileHandler = new FileHandler(LOG_FILE);
			logger.addHandler(fileHandler);
			logger.setUseParentHandlers(false);
		} catch (SecurityException e) {
			logger.warning(e.getMessage());
		} catch (IOException e) {
			logger.warning(e.getMessage());
		}
	}

	public static void main(String[] args) {
		new Calculator().runCalculator();
	}

	/**
	 * 
	 */
	public void runCalculator() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			try {
				int size = calcModel.getCompleteInputList().size();
				String input = sc.nextLine().trim();
				calcModel.addAll(calcModel.evaluateAndCreateInputArray(input));
				calcModel = computeAndPopulateStack(calcModel, size);
			} catch (InsufficientParameterException is) {
				logger.warning(is.getErrorMsg());
				System.out.println(is.getErrorMsg());
			} catch (InvalidModelException iv) {
				logger.warning(iv.getMessage());
				System.out.println(iv.getMessage()); // log this error
				System.exit(-1);
			} catch (RuntimeException re) {
				logger.warning(re.getMessage());
				System.out.println(re.getMessage());
			} finally {
				System.out.println(calcModel.printOutput());
			}
		}
	}

	protected RPNCalculatorModel getModel() {
		return calcModel;
	}

	/**
	 * @param model
	 * @param index
	 * @return
	 * @throws InsufficientParameterException
	 * @throws InvalidModelException
	 */
	protected RPNCalculatorModel computeAndPopulateStack(RPNCalculatorModel model, int index)
			throws InsufficientParameterException, InvalidModelException {
		List<String> inputList = model.getCompleteInputList();
		for (int i = index; i < inputList.size(); i++) {
			int pointerPos = i - index;
			String currVal = inputList.get(i);
			OperationInfo currOpInfo = new OperationInfo(i, CalculatorUtil.evaluateOperandPosition(pointerPos),
					currVal);
			if (!CalculatorUtil.validateEntry(currVal)) { // check if it
															// is a valid
															// entry
				throw new InsufficientParameterException(currVal, index);
			}
			if (CalculatorUtil.isKnownOperation(currVal)) { // if it is not a
																// number, then
																// need to do
																// operation
				OperationFactory.getOperationObj(model, currVal).execute(model, currOpInfo);

			} else if (CalculatorUtil.isNumber(currVal)) {
				model.getStack().push(currVal);
			} else {
				throw new IllegalStateException(CalculatorConstants.UNKNOWN_OPERATION_ERR);
			}
		}
		return model;
	}
}
