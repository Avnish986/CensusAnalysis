package com.census.analysis;

import static org.junit.Assert.assertEquals;

import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

public class StateCensusAnalyserTest {

	public static final String STATE_CENSUS_DATA = "StateCensus.csv";
	public static final String WRONG_STATE_CENSUS_DATA = "src/main/java/com/StateCensus.csv";
	public static final String WRONG_STATE_CENSUS_DATA_HEADER = "StateCodee.csv";
	public static final String WRONG_STATE_CENSUS_DATA_TYPE = "StateCensus.txt";

	CensusAnalyser stateCensusAnalyser = new CensusAnalyser();

	@Test
	public void ensureNoOfRecordMatches() throws WrongCSVException {
		int records = stateCensusAnalyser.loadCSVFile(Paths.get(STATE_CENSUS_DATA));
		Assert.assertEquals(29, records);

	}

	@Test
	public void checkWrongPath() throws WrongCSVException {
		try {
			stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA));
		} catch (WrongCSVException e) {
			Assert.assertEquals(WrongCSVException.ExceptionType.WRONG_CSV, e.type);
		}
	}

	@Test
	public void checkWrongHeader() throws WrongCSVException {
		try {
			stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA_HEADER));
		} catch (WrongCSVException e) {
			Assert.assertEquals(WrongCSVException.ExceptionType.WRONG_HEADER, e.type);

		}
	}

	@Test
	public void checkWrongType() throws WrongCSVException {
		try {
			stateCensusAnalyser.loadCSVFile(Paths.get(WRONG_STATE_CENSUS_DATA_TYPE));
		} catch (WrongCSVException e) {
			Assert.assertEquals(WrongCSVException.ExceptionType.WRONG_TYPE, e.type);

		}
	}

	@Test
	public void censusSortedOnState() throws WrongCSVException {
		stateCensusAnalyser.loadCSVFile(Paths.get(STATE_CENSUS_DATA));
		String sortedCensusData = stateCensusAnalyser.getSortedCensuByState();
		CSVStateCensus[] censusCsv = new Gson().fromJson(sortedCensusData, CSVStateCensus[].class);
		Assert.assertEquals("Andhra Pradesh", censusCsv[0].state);
	}
	
	@Test
	   public void censusSortedOnStatePopulation() throws WrongCSVException {
		stateCensusAnalyser.loadCSVFile(Paths.get(STATE_CENSUS_DATA));
		  String sortedCensus = stateCensusAnalyser.getStatePopulationWiseSortedCensusData();
		  CSVStateCensus[] censusSortedCsv = new Gson().fromJson(sortedCensus, CSVStateCensus[].class);
		  Assert.assertEquals("Uttar Pradesh", censusSortedCsv[28].state);
	   }
}