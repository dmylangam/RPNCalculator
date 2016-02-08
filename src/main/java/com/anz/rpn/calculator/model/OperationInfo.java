package com.anz.rpn.calculator.model;

/**
 * @author deepamylangam
 *
 */
public class OperationInfo {

	int index;
	int operandPosition;
	String operationValue;

	public OperationInfo(int index, int operandPosition, String value) {
		this.index = index;
		this.operandPosition = operandPosition;
		this.operationValue = value;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getOperandPosition() {
		return operandPosition;
	}

	public void setOperandPosition(int operandPosition) {
		this.operandPosition = operandPosition;
	}

	public String getOperationValue() {
		return operationValue;
	}

	public void setOperationValue(String operationValue) {
		this.operationValue = operationValue;
	}

}
