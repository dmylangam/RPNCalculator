package com.anz.rpn.calculator.model;

/**
 * Represents the operation and its index in the array
 * 
 * @author deepamylangam
 *
 */
public class OperationInfo {

	int index;
	int operandPosition;
	String operationValue;

	/**
	 * @param index
	 * @param operandPosition
	 * @param value
	 */
	public OperationInfo(int index, int operandPosition, String value) {
		this.index = index;
		this.operandPosition = operandPosition;
		this.operationValue = value;
	}

	/**
	 * @return
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return
	 */
	public int getOperandPosition() {
		return operandPosition;
	}

	/**
	 * @param operandPosition
	 */
	public void setOperandPosition(int operandPosition) {
		this.operandPosition = operandPosition;
	}

	/**
	 * @return
	 */
	public String getOperationValue() {
		return operationValue;
	}

	/**
	 * @param operationValue
	 */
	public void setOperationValue(String operationValue) {
		this.operationValue = operationValue;
	}

}
