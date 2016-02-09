package com.anz.rpn.calculator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

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
	public void testEvaluateAndCreateInputArray() {
		List<String> list = model.evaluateAndCreateInputArray("1");
		assertTrue(list.size() == 1);
		assertTrue(list.get(0).equals("1"));
		
		list = model.evaluateAndCreateInputArray("1 2");
		assertTrue(list.size() == 2);
		assertTrue(list.get(0).equals("1"));
		assertTrue(list.get(1).equals("2"));

		list = model.evaluateAndCreateInputArray("1 2 + 3");
		assertTrue(list.size() == 4);
		assertTrue(list.get(2).equals("+"));
		assertTrue(list.get(3).equals("3"));

		list = model.evaluateAndCreateInputArray("1 2 + 3 undo sqrt");
		assertTrue(list.size() == 6);
		assertTrue(list.get(4).equals("undo"));
		assertTrue(list.get(5).equals("sqrt"));

	}

	@Test(expected = IllegalStateException.class)
	public void testInvalidInput() {
		model.evaluateAndCreateInputArray("");
		model.evaluateAndCreateInputArray(" ");
	}

}
