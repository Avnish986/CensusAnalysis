package com.census.analysis;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

public class StateCensusAnalyserTest {

	public static final String STATE_CENSUS_DATA = "StateCensus.csv";
	public static final String WRONG_STATE_CENSUS_DATA = "src/main/java/com/StateCensus.csv";
	public static final String WRONG_STATE_CENSUS_DATA_HEADER = "StateCodee.csv";
	public static final String WRONG_STATE_CENSUS_DATA_TYPE = "StateCensus.txt";

	CensusAnalyser stateCensusAnalyser = new CensusAnalyser();

	@Test
	public void ensureNoOfRecordMatches() throws CensusException {
		int records = stateCensusAnalyser.loadCSVFile(Paths.get(STATE_CENSUS_DATA));
		Assert.assertEquals(29, records);
	}

	@Test
	public void checkWrongPath() throws CensusException {
		try {
			stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA));
		} catch (CensusException e) {
			Assert.assertEquals(CensusException.ExceptionType.WRONG_CSV, e.type);
		}
	}

	@Test
	public void checkWrongHeader() throws CensusException {
		try {
			stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA_HEADER));
		} catch (CensusException e) {
			Assert.assertEquals(CensusException.ExceptionType.WRONG_HEADER, e.type);

		}
	}

	@Test
	public void checkWrongType() throws CensusException {
		try {
			stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA_TYPE));
		} catch (CensusException e) {
			Assert.assertEquals(CensusException.ExceptionType.WRONG_TYPE, e.type);

		}
	}
}