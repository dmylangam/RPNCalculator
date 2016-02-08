package com.anz.rpn.calculator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

/**
 * @author deepamylangam
 *
 */
@RunWith(JUnit4.class)
public class RPNCalculatorModelTest {

	private RPNCalculatorModel model;

	@Before
	public void initModel() {
		model = new RPNCalculatorModel();
	}

	@Test
	public void testPrintStack() {
		String testStr = "1.234";
		model.getStack().push(testStr);

		assertEquals(CalculatorConstants.STACK_STR + CalculatorConstants.SPACE_STR + testStr, model.printOutput());
	}

	@Test
	public void testevaluateAndCreateInputArray() {
		model.evaluateAndCreateInputArray("1");
		assertTrue(model.getCompleteInputList().size() == 1);
		assertTrue(model.getCompleteInputList().get(0).equals("1"));
		assertTrue(model.getStack().size() == 0);

		model.evaluateAndCreateInputArray("1 2");
		assertTrue(model.getCompleteInputList().size() == 2);
		assertTrue(model.getCompleteInputList().get(0).equals("1"));
		assertTrue(model.getCompleteInputList().get(1).equals("2"));
		assertTrue(model.getStack().size() == 0);

		model.evaluateAndCreateInputArray("1 2 + 3");
		assertTrue(model.getCompleteInputList().size() == 4);
		assertTrue(model.getCompleteInputList().get(2).equals("+"));
		assertTrue(model.getCompleteInputList().get(3).equals("3"));
		assertTrue(model.getStack().size() == 0);

		model.evaluateAndCreateInputArray("1 2 + 3 undo sqrt");
		assertTrue(model.getCompleteInputList().size() == 6);
		assertTrue(model.getCompleteInputList().get(4).equals("undo"));
		assertTrue(model.getCompleteInputList().get(5).equals("sqrt"));
		assertTrue(model.getStack().size() == 0);

	}

	@Test(expected = IllegalStateException.class)
	public void testInvalidInput() {
		model.evaluateAndCreateInputArray("");
		model.evaluateAndCreateInputArray(" ");
	}

}
