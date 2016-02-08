package com.anz.rpn;

import java.util.Arrays;
import java.util.List;

import com.anz.rpn.calculator.model.RPNCalculatorModel;

public class TestDataHelper {

	public static final String inputStr = "4 2 ";

	public static List<String> createSampleList(String... str) {
		List<String> inputList = Arrays.asList(str);
		return inputList;
	}

	public static RPNCalculatorModel createSampleModel(String inputStr) throws Exception {
		RPNCalculatorModel model = new RPNCalculatorModel();
		model.setInput(inputStr);
		for (int i = 0; i < model.getInput().size(); i++) {
			model.getStack().push(model.getInput().get(i));
		}
		return model;
	}
}
