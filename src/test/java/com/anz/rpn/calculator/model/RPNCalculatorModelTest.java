package com.anz.rpn.calculator.model;

import static org.junit.Assert.assertEquals;

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
}
