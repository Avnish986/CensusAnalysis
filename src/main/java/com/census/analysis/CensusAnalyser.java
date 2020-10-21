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

			Iterator<CSVStateCensus> iterator = (Iterator<CSVStateCensus>) this.getFileIterator(reader,
					CSVStateCensus.class);
			ArrayList<CSVStateCensus> stateCensusList = new ArrayList<>();
			while (iterator.hasNext()) {
				stateCensusList.add(iterator.next());
			}
			System.out.println(stateCensusList);
			return stateCensusList.size();
		} catch (IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV);
		}
	}

	public int loadStateCSVFile(Path path) throws CensusException {
		try (Reader reader = Files.newBufferedReader(path)) {

			Iterator<StateCode> it = (Iterator<StateCode>) this.getFileIterator(reader, StateCode.class);
			ArrayList<StateCode> stateCensusList = new ArrayList<>();
			while (it.hasNext()) {
				stateCensusList.add(it.next());
			}
			for (StateCode x : stateCensusList) {
				System.out.println(x);
			}
			return stateCensusList.size();
		} catch (IOException e) {
			throw new CensusException("File not found", CensusException.ExceptionType.WRONG_CSV);
		}

	}

	private Iterator<E> getFileIterator(Reader reader, Class csvClass) throws CensusException {
		try {
			CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader).withType(csvClass).withIgnoreLeadingWhiteSpace(true)
					.build();

			return csvToBean.iterator();
		} catch (RuntimeException e) {
			throw new CensusException("File internal data not valid", CensusException.ExceptionType.WRONG_HEADER);
		}
	}

}
