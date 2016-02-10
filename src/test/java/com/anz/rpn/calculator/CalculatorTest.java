package com.anz.rpn.calculator;

import static org.junit.Assert.assertEquals;
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
		model = TestDataHelper.createSampleModelWithStackForOperations(" ");
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
		model.getStack().clear();
		model.getCompleteInputList().clear();
		model = TestDataHelper.createSampleModel("5 2");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 2);
		assertTrue(model.getStack().get(0).equals("5"));
		assertTrue(model.getStack().get(1).equals("2"));
	}

	@Test
	public void testComputeAndPopulateStackCase2() throws Exception {
		model.getStack().clear();
		model.getCompleteInputList().clear();
		String expResult = "stack: 1.4142135624";		//rounded off
		model = TestDataHelper.createSampleModel("2 sqrt");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 1);
		assertEquals(expResult, model.printOutput());

	}

	@Test
	public void testComputeAndPopulateStackCase3() throws Exception {
		model.getStack().clear();
		model.getCompleteInputList().clear();
		model = TestDataHelper.createSampleModel("5 2 -");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: 3", model.printOutput());
		
		int index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "3 -");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: 0", model.printOutput());
		
	}
	
	@Test
	public void testComputeAndPopulateStackCase4() throws Exception {
		model.getStack().clear();
		model.getCompleteInputList().clear();
		model = TestDataHelper.createSampleModel("5 4 3 2");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 4);
		assertEquals("stack: 5 4 3 2", model.printOutput());
		
		int index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "undo undo *");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: 20", model.printOutput());
		
		index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "5 *");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: 100", model.printOutput());
		
		index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "undo");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 2);
		assertEquals("stack: 20 5", model.printOutput());
		
	}
	
	@Test
	public void testComputeAndPopulateStackCase5() throws Exception {
		model.getStack().clear();
		model.getCompleteInputList().clear();
		model = TestDataHelper.createSampleModel("7 12 2 /");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 2);
		assertEquals("stack: 7 6", model.printOutput());
		
		int index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "*");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: 42", model.printOutput());
		
		index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "4 /");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: 10.5", model.printOutput());
	}
	
	@Test
	public void testComputeAndPopulateStackCase6() throws Exception {
		model.getStack().clear();
		model.getCompleteInputList().clear();
		model = TestDataHelper.createSampleModel("1 2 3 4 5");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 5);
		assertEquals("stack: 1 2 3 4 5", model.printOutput());
		
		int index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "*");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 4);
		assertEquals("stack: 1 2 3 20", model.printOutput());
		
		index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "clear 3 4 -");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: -1", model.printOutput());
	}
		
	@Test
	public void testComputeAndPopulateStackCase7() throws Exception {
		model.getStack().clear();
		model.getCompleteInputList().clear();
		model = TestDataHelper.createSampleModel("1 2 3 4 5");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 5);
		assertEquals("stack: 1 2 3 4 5", model.printOutput());
		
		int index = model.getCompleteInputList().size();
		model = TestDataHelper.addNextInput(model, "* * * *");
		calculator.computeAndPopulateStack(model, index);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: 120", model.printOutput());
	}
	
	@Test (expected=InsufficientParameterException.class)
	public void testComputeAndPopulateStackCase8() throws Exception {
		model.getStack().clear();
		model.getCompleteInputList().clear();
		model = TestDataHelper.createSampleModel("1 2 3 * 5 + * * 6 5");
		calculator.computeAndPopulateStack(model, 0);
		assertTrue(model.getStack().size() == 1);
		assertEquals("stack: 11", model.printOutput());
	}
}
