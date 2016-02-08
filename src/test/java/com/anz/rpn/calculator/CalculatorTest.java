package com.anz.rpn.calculator;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.anz.rpn.TestDataHelper;
import com.anz.rpn.calculator.exception.InsufficientParameterException;
import com.anz.rpn.calculator.model.RPNCalculatorModel;

@RunWith(JUnit4.class)
public class CalculatorTest {

	Calculator calculator = new Calculator();
	private RPNCalculatorModel model;

	@Before
	public void initModel() throws Exception {
		model = TestDataHelper.createSampleModel(TestDataHelper.aSamplePosInputStr);
	}

	@Test
	public void testRunCalculator() throws Exception {
		int index = 0;
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 2);
		assertTrue(model.getStack().get(0).equals("4"));
		assertTrue(model.getStack().get(1).equals("2"));
	}

	@Test(expected = IllegalStateException.class)
	public void testComputeAndPopulateStackWithNoEntry() throws Exception {
		model = TestDataHelper.createSampleModel(" ");
		calculator.computeAndPopulateStack(model, 0);
	}

	@Test(expected = InsufficientParameterException.class)
	public void testComputeAndPopulateStackWithInvalidInput() throws Exception {
		model = TestDataHelper.createSampleModel("undo");
		calculator.computeAndPopulateStack(model, 0);

		model = TestDataHelper.createSampleModel("-4 sqrt");
		calculator.computeAndPopulateStack(model, 0);

		model = TestDataHelper.createSampleModel("1 +");
		calculator.computeAndPopulateStack(model, 0);

		model = TestDataHelper.createSampleModel("-");
		calculator.computeAndPopulateStack(model, 0);

		model = TestDataHelper.createSampleModel("1 0 /");
		calculator.computeAndPopulateStack(model, 0);

		model = TestDataHelper.createSampleModel("1 - 0");
		calculator.computeAndPopulateStack(model, 0);
	}
	
	@Test
	public void testComputeAndPopulateStackCase1() throws Exception {
		model = TestDataHelper.createSampleModel("5 2");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 2);
		assertTrue(model.getStack().get(0).equals("5"));
		assertTrue(model.getStack().get(1).equals("2"));
	}
	
	@Test
	public void testComputeAndPopulateStackCase2() {
		
	}
	
}
