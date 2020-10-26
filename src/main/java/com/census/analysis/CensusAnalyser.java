package com.census.analysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser<E> {
	List<CSVStateCensus> csvCensusList=null;
	List<StateCode> csvStateList= null;

	public CensusAnalyser() {
		this.csvCensusList = new ArrayList<>();
		this.csvStateList = new ArrayList<>();

	}
	public int loadCSVFile(Path path) throws WrongCSVException {
		try (Reader reader = Files.newBufferedReader(path);) {		
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder(); 
			csvCensusList = csvBuilder.getCSVList(reader, CSVStateCensus.class);
			return csvCensusList.size();
		} catch (IOException e) {
			throw new WrongCSVException("File not found", WrongCSVException.ExceptionType.WRONG_CSV);
		}
	}

	public int loadStateCSVFile(Path path) throws WrongCSVException {
		try (Reader reader = Files.newBufferedReader(path)) {
			ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder(); 
			csvStateList = csvBuilder.getCSVList(reader, StateCode.class);
			return csvStateList.size();
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
