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
	public int loadCSVFile(Path path) throws WrongCSVException {
		try (Reader reader = Files.newBufferedReader(path);) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder(); 
			Iterator<CSVStateCensus> iterator = (Iterator<CSVStateCensus>) csvBuilder.getFileIterator(reader,
					CSVStateCensus.class);
			return filesize(iterator);
		} catch (IOException e) {
			throw new WrongCSVException("File not found", WrongCSVException.ExceptionType.WRONG_CSV);
		}
	}

	public int loadStateCSVFile(Path path) throws WrongCSVException {
		try (Reader reader = Files.newBufferedReader(path)) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder(); 
			Iterator<StateCode> it = (Iterator<StateCode>) csvBuilder.getFileIterator(reader,
					StateCode.class);
			return filesize(it);
		} catch (IOException e) {
			throw new WrongCSVException("File not found", WrongCSVException.ExceptionType.WRONG_CSV);
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
}
