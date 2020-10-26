package com.census.analysis;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class StateCodeAnalyserTest {
	public static final String STATE_CODE_DATA = "StateCode1.csv";
	public static final String WRONG_STATE_CODE_DATA = "src/main/java/com/StateCode.csv";
	public static final String WRONG_STATE_CODE_DATA_HEADER = "StateCensus.csv";

	CensusAnalyser stateCodeAnalyser = new CensusAnalyser();

	@Test
	public void ensureNoOfRecordMatches() throws WrongCSVException {
		int records = stateCodeAnalyser.loadStateCSVFile(Paths.get(STATE_CODE_DATA));
		Assert.assertEquals(37, records);
	}

	@Test
	public void checkWrongPath() throws WrongCSVException {
		try {
			stateCodeAnalyser.loadStateCSVFile(Paths.get(WRONG_STATE_CODE_DATA));
		} catch (WrongCSVException e) {
			Assert.assertEquals(WrongCSVException.ExceptionType.WRONG_CSV, e.type);
		}
	}

	@Test
	public void checkWrongHeader() throws WrongCSVException {
		try {
			stateCodeAnalyser.loadStateCSVFile(Paths.get(WRONG_STATE_CODE_DATA_HEADER));
		} catch (WrongCSVException e) {
			Assert.assertEquals(WrongCSVException.ExceptionType.WRONG_HEADER, e.type);

		}
	}
}