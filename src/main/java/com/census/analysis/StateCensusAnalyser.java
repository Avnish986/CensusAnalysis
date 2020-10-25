package com.census.analysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCensusAnalyser {

	public int loadCSVFile(Path path) throws WrongCSVException {
		try {
			Reader reader = Files.newBufferedReader(path);
			CsvToBean<CSVStateCensus> csvToBean = new CsvToBeanBuilder(reader).withType(CSVStateCensus.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			
			Iterator<CSVStateCensus> iterator = csvToBean.iterator();
			ArrayList<CSVStateCensus> stateCensusList = new ArrayList<>();
			while (iterator.hasNext()) {
				stateCensusList.add(iterator.next());
			}
			System.out.println(stateCensusList);
			return stateCensusList.size();
		} catch (IOException e) {
			throw new WrongCSVException("File not found", WrongCSVException.ExceptionType.WRONG_CSV);
		} catch (RuntimeException e) {
			throw new WrongCSVException("File internal data not valid", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
	}
}