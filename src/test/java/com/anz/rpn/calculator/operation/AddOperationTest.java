package com.anz.rpn.calculator.operation;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.anz.rpn.TestDataHelper;
import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.model.OperationInfo;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

@RunWith(JUnit4.class)
public class AddOperationTest {

	private RPNCalculatorModel model;
	private IOperation operation;
	private OperationInfo opInfo;

	@Before
	public void initModel() {
		operation = AddOperation.getInstance();
		opInfo = new OperationInfo(0, 5, "+");
		try {
			model = TestDataHelper.createSampleModelWithStackForOperations(TestDataHelper.aSamplePosInputStr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test // for positive
	public void testExecute() throws Exception {
		operation.execute(model, opInfo);
		assertTrue(model.getStack().size() == 1);
		assertTrue(model.getStack().get(0).equals("6"));

		model = TestDataHelper.createSampleModelWithStackForOperations(TestDataHelper.aSampleNegInputStr);
		operation.execute(model, opInfo);
		assertTrue(model.getStack().size() == 1);
		assertTrue(model.getStack().get(0).equals("3"));

	}

	@Test(expected = InsufficientParameterException.class)
	public void testExecuteFailInvalidInput() throws Exception {
		model = TestDataHelper.createSampleModelWithStackForOperations("4 +");
		operation.execute(model, new OperationInfo(1, 3, "+"));

		model = TestDataHelper.createSampleModelWithStackForOperations("+");
		operation.execute(model, new OperationInfo(0, 1, "+"));

		model = TestDataHelper.createSampleModelWithStackForOperations("4 + 5");
		operation.execute(model, new OperationInfo(1, 3, "+"));

		model = TestDataHelper.createSampleModelWithStackForOperations("4");
		operation.execute(model, new OperationInfo(0, 1, "+"));
	}

}
