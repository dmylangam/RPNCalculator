package com.anz.rpn.calculator.operation;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.anz.rpn.TestDataHelper;
import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.exception.InvalidModelException;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

@RunWith(JUnit4.class)
public class SquarerootOperationTest {

	private RPNCalculatorModel model;
	private IOperation operation;
	private OperationInfo opInfo;

	@Before
	public void initModel() {
		operation = SquarerootOperation.getInstance();
		opInfo = new OperationInfo(0, 5, "sqrt");
		try {
			model = TestDataHelper.createSampleModel(TestDataHelper.aSamplePosInputStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void testExecute() throws InvalidModelException, InsufficientParameterException {
		operation.execute(model, opInfo);
		assertTrue(model.getStack().size() == 2);
		assertTrue(model.getStack().get(0).equals("4"));
		assertTrue(model.getStack().get(1).startsWith("1.41"));
	}

	@Test(expected = InsufficientParameterException.class)
	public void testExecuteFailInvalidInput() throws Exception {
		model = TestDataHelper.createSampleModel("-4 sqrt");
		operation.execute(model, new OperationInfo(1, 3, "sqrt"));

		model = TestDataHelper.createSampleModel("sqrt");
		operation.execute(model, new OperationInfo(0, 1, "sqrt"));
	}
}
