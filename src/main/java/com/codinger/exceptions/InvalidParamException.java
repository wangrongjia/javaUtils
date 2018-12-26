package com.codinger.exceptions;

public class InvalidParamException extends Exception {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4140738332034846952L;

	public InvalidParamException(String msg){
		super(msg);
	}
}
