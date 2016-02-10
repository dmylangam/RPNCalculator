package com.anz.rpn.calculator.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Stack;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorUtilTest {

	@Test
	public void testConvertString() {
		assertNotNull(CalculatorUtil.convertString("1234578.123456789012345678"));
		assertTrue(CalculatorUtil.convertString("12342342434.123456789056456975").toString()
				.equals("12342342434.123456789056457"));
		assertTrue(CalculatorUtil.convertString("12342342434.123456").toString().equals("12342342434.123456"));
		assertTrue(CalculatorUtil.convertString("999.9").toString().equals("999.9"));
		assertTrue(CalculatorUtil.convertString("999.999").toString().equals("999.999"));
		assertTrue(CalculatorUtil.convertString("999.00").toString().equals("999"));
		
	}

	@Test
	public void testIsKnownOperation() {
		assertTrue(CalculatorUtil.isKnownOperation("undo"));
		assertTrue(CalculatorUtil.isKnownOperation("sqrt"));
		assertTrue(CalculatorUtil.isKnownOperation("+"));
	}

	@Test
	public void testIsOperand() {
		assertFalse(CalculatorUtil.isOperand("sqrt"));
		assertTrue(CalculatorUtil.isOperand("+"));
	}

	@Test
	public void testIsSquareRootCommand() {
		assertTrue(CalculatorUtil.isSquareRootCommand("sqrt"));
		assertFalse(CalculatorUtil.isSquareRootCommand("+"));
	}

	@Test
	public void testIsClearCommand() {
		assertTrue(CalculatorUtil.isClearCommand("clear"));
		assertFalse(CalculatorUtil.isClearCommand("sqrt"));
	}

	@Test
	public void testIsUndoCommand() {
		assertTrue(CalculatorUtil.isUndoCommand("undo"));
		assertFalse(CalculatorUtil.isUndoCommand("sqrt"));
	}

	@Test
	public void testIsNumber() {
		assertTrue(CalculatorUtil.isNumber("-1.11111"));
		assertTrue(CalculatorUtil.isNumber("-3645645"));
		assertFalse(CalculatorUtil.isNumber("sdsdf"));
		assertFalse(CalculatorUtil.isNumber("-"));
	}

	@Test
	public void testValidateEntry() {
		assertTrue(CalculatorUtil.validateEntry("-2323"));
	}

	@Test
	public void testPeek() {
		assertNull(CalculatorUtil.peek(new Stack<String>()));
	}

	@Test
	public void testPeekPrevious() {
		Stack<String> stack = new Stack<String>();
		stack.push("test");
		stack.push("test1");
		stack.push("test2");
		assertTrue("test1".equals(CalculatorUtil.peekPrevious(stack)));

	}

	@Test
	public void testEvaluateOperandPosition() {
		assertTrue(CalculatorUtil.evaluateOperandPosition(0) == 1);
		assertTrue(CalculatorUtil.evaluateOperandPosition(1) == 3);
		assertTrue(CalculatorUtil.evaluateOperandPosition(2) == 5);
	}

	@Test
	public void testGetStackOutputForDisplay() {
		assertEquals("11111.123", CalculatorUtil.getStackOutputForDisplay("11111.123"));
		assertEquals("11111.123456789", CalculatorUtil.getStackOutputForDisplay("11111.123456789012345"));
		assertEquals("11111.1234567891", CalculatorUtil.getStackOutputForDisplay("11111.123456789112345"));
		assertEquals("11111", CalculatorUtil.getStackOutputForDisplay("11111.0"));
	}
}
