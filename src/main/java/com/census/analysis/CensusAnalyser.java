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

public class CensusAnalyser<E> {
	public int loadCSVFile(Path path) throws CensusException {
		try (Reader reader = Files.newBufferedReader(path);) {

			Iterator<CSVStateCensus> iterator = (Iterator<CSVStateCensus>) this.getFileIterator(reader,CSVStateCensus.class);
			return filesize(iterator);
		} catch (IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV);
		}
	}

	public int loadStateCSVFile(Path path) throws CensusException {
		try (Reader reader = Files.newBufferedReader(path)) {

			Iterator<StateCode> it = (Iterator<StateCode>) this.getFileIterator(reader, StateCode.class);
			return filesize(it);
		} catch (IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV);
		}

	}

	private <E> int filesize(Iterator<E> iterator) {
		ArrayList<E> stateCensusList = new ArrayList<E>();
		while (iterator.hasNext()) {

			stateCensusList.add(iterator.next());

		}
		System.out.println(stateCensusList);
		return stateCensusList.size();
	}

	private Iterator<E> getFileIterator(Reader reader, Class csvClass) throws CensusException {
		try {
			CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
										.withType(csvClass)
										.withIgnoreLeadingWhiteSpace(true)
										.build();

			return csvToBean.iterator();
		} catch (RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
	}

}
