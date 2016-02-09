package com.anz.rpn.calculator.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class CalculatorHelperTest {

	@Test
	public void testGetStackOutputForDisplay() {
		assertEquals("11111.123", CalculatorHelper.getStackOutputForDisplay("11111.123"));
		assertEquals("11111.123456789", CalculatorHelper.getStackOutputForDisplay("11111.123456789012345"));
		assertEquals("11111.1234567891", CalculatorHelper.getStackOutputForDisplay("11111.123456789112345"));
		assertEquals("11111", CalculatorHelper.getStackOutputForDisplay("11111.0"));
	}
}
