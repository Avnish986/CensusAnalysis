package com.census.analysis;

public class CensusException extends Exception {

	enum ExceptionType {
		WRONG_CSV, WRONG_TYPE, WRONG_HEADER
	}

	ExceptionType type;

	public CensusException(String message, ExceptionType type) {
		super(message);
		this.type = type;
	}

}