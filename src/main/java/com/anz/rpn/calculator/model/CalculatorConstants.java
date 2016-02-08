package com.anz.rpn.calculator.model;

public class CalculatorConstants {
	public static final String INSUFF_PARAMETERS_STR = ": insufficient parameters";
	public static final String OPERATOR_STR = "operator";
	public static final String SPACE_STR = " ";
	public static final String POSITION_STR = "position:";
	public static final String OPEN_BRACKET_STR = "(";
	public static final String CLOSE_BRACKET_STR = ")";
	public static final String STACK_STR = "stack:";
	public static final String OPERAND_REGEX = "[-+*/]";
	public static final String COMMAND_REGEX = "(?i:sqrt)|(?i:undo)|(?i:clear)";
	public static final String REAL_NUMBERS_REGEX = "^\\d+(\\.\\d+)?$";
}
