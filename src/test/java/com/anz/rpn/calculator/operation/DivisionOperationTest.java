package com.anz.rpn.calculator.operation;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.anz.rpn.TestDataHelper;
import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidInputException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

@RunWith(JUnit4.class)
public class DivisionOperationTest {

	private RPNCalculatorModel model;
	private IOperation operation;
	private OperationInfo opInfo;

	@Before
	public void initModel() {
		operation = DivisionOperation.getInstance();
		opInfo = new OperationInfo(0, 5, "/");
		try {
			model = TestDataHelper.createSampleModel(TestDataHelper.inputStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testExecute() throws InvalidInputException, InvalidModelException, InsufficientParameterException {
		operation.execute(model, opInfo);
		assertTrue(model.getStack().size() == 1);
		assertTrue(model.getStack().get(0).equals("2"));
	}
}