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
	public static final String SQRT_REGEX = "(?i:sqrt)";
	public static final String UNDO_REGEX = "(?i:undo)";
	public static final String CLEAR_REGEX = "(?i:clear)";
	public static final String COMMAND_REGEX = SQRT_REGEX + "|" + UNDO_REGEX + "|" + CLEAR_REGEX;
	public static final String REAL_NUMBERS_REGEX = "(-)?(^\\d+(\\.\\d+))?$";
	public static final String ADD_STR = "+";
	public static final String SUBTRACT_STR = "-";
	public static final String MULTIPLY_STR = "*";
	public static final String DIVISION_STR = "/";
	public static final String CLEAR_STR = "clear";
	public static final String UNDO_STR = "undo";
	public static final String SQRT_STR = "sqrt";
	public static final String INVALID_MODEL_STR = "Invalid model: ";
	public static final String INVALID_INPUT_STR = "Invalid inpu: ";

}
