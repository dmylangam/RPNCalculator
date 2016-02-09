package com.anz.rpn.calculator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorHelperTest {

	@Test
	public void testIsKnownOperation() {
		assertTrue(CalculatorHelper.isKnownOperation("undo"));
		assertTrue(CalculatorHelper.isKnownOperation("sqrt"));
		assertTrue(CalculatorHelper.isKnownOperation("+"));
	}

	@Test
	public void testIsOperand() {
		assertFalse(CalculatorHelper.isOperand("sqrt"));
		assertTrue(CalculatorHelper.isOperand("+"));
	}

	@Test
	public void testIsSquareRootCommand() {
		assertTrue(CalculatorHelper.isSquareRootCommand("sqrt"));
		assertFalse(CalculatorHelper.isSquareRootCommand("+"));
	}

	@Test
	public void testIsClearCommand() {
		assertTrue(CalculatorHelper.isClearCommand("clear"));
		assertFalse(CalculatorHelper.isClearCommand("sqrt"));
	}

	@Test
	public void testIsUndoCommand() {
		assertTrue(CalculatorHelper.isUndoCommand("undo"));
		assertFalse(CalculatorHelper.isUndoCommand("sqrt"));
	}

	@Test
	public void testIsNumber() {
		assertTrue(CalculatorHelper.isNumber("-1.11111"));
		assertTrue(CalculatorHelper.isNumber("-3645645"));
		assertFalse(CalculatorHelper.isNumber("sdsdf"));
	}

	@Test
	public void testValidateEntry() {
		assertTrue(CalculatorHelper.validateEntry("-2323"));
	}

	@Test
	public void testPeek() {
		assertNull(CalculatorHelper.peek(new Stack<String>()));
	}

	@Test
	public void testPeekPrevious() {
		Stack<String> stack = new Stack<String>();
		stack.push("test");
		stack.push("test1");
		stack.push("test2");
		assertTrue("test1".equals(CalculatorHelper.peekPrevious(stack)));

	}

	@Test
	public void testEvaluateOperandPosition() {
		assertTrue(CalculatorHelper.evaluateOperandPosition(0) == 1);
		assertTrue(CalculatorHelper.evaluateOperandPosition(1) == 3);
		assertTrue(CalculatorHelper.evaluateOperandPosition(2) == 5);
	}

	@Test
	public void testGetStackOutputForDisplay() {
		assertEquals("11111.123", CalculatorHelper.getStackOutputForDisplay("11111.123"));
		assertEquals("11111.123456789", CalculatorHelper.getStackOutputForDisplay("11111.123456789012345"));
		assertEquals("11111.1234567891", CalculatorHelper.getStackOutputForDisplay("11111.123456789112345"));
		assertEquals("11111", CalculatorHelper.getStackOutputForDisplay("11111.0"));
	}
}
