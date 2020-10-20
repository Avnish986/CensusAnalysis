package com.census.analysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	public int loadCSVFile(Path path) throws CensusException {
		try {
			Reader reader = Files.newBufferedReader(path);
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader).withType(CSVStateCensus.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<CSVStateCensus> iterator = csvToBean.iterator();
			ArrayList<CSVStateCensus> stateCensusList = new ArrayList<CSVStateCensus>();
			while (iterator.hasNext()) {
				stateCensusList.add(iterator.next());
			}
			return stateCensusList.size();
		} catch (IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV);
		} catch (RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
	}
}