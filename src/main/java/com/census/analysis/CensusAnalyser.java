package com.census.analysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.google.gson.Gson;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CensusAnalyser<E> {
	List<CSVStateCensus> csvCensusList = null;
	List<StateCode> csvStateList = null;

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
		catch (RuntimeException e) {
			throw new WrongCSVException("File data not proper", WrongCSVException.ExceptionType.WRONG_HEADER);

		} catch (WrongCSVException e) {
			throw new WrongCSVException(e.getMessage(), WrongCSVException.ExceptionType.WRONG_HEADER);
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
		catch (RuntimeException e) {
			throw new WrongCSVException("File data not proper", WrongCSVException.ExceptionType.WRONG_HEADER);

		} catch (WrongCSVException e) {
			throw new WrongCSVException(e.getMessage(), WrongCSVException.ExceptionType.WRONG_HEADER);
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

	public String getSortedCensuByState() throws WrongCSVException {
		if (csvCensusList == null || csvCensusList.size() == 0)

		{
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Comparator<CSVStateCensus> censusComparator = Comparator.comparing(census -> census.state);

		this.sort(censusComparator);
		String sortedStateCensus = new Gson().toJson(csvCensusList);
		return sortedStateCensus;
	}

	private void sort(Comparator<CSVStateCensus> censusComparator) {
		for (int i = 0; i < csvCensusList.size() - 1; i++) {
			for (int j = 0; j < csvCensusList.size() - 1 - i; j++) {
				CSVStateCensus census1 = csvCensusList.get(j);
				CSVStateCensus census2 = csvCensusList.get(j + 1);
				if (censusComparator.compare(census1, census2) > 0) {
					csvCensusList.set(j, census2);
					csvCensusList.set(j + 1, census1);
				}
			}
		}
	}

	public String getStateCodeWiseSortedCensusData() throws WrongCSVException {
		if (csvStateList == null || csvStateList.size() == 0) {
			throw new WrongCSVException("File error", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Collections.sort(csvStateList, Comparator.comparing(code -> code.stateCode));
		return new Gson().toJson(csvStateList);
	}
	
	public String getStatePopulationWiseSortedCensusData() throws WrongCSVException {
		if(csvCensusList == null || csvCensusList.size() == 0) {
			throw new WrongCSVException("File is empty", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Collections.sort(csvCensusList, Comparator.comparing(census -> census.getPopulationData()));
		System.out.println(csvCensusList);
		return new Gson().toJson(csvCensusList);
	}
	public String getStatePopulationDensityWiseSortedCensusData() throws WrongCSVException {
		if(csvCensusList == null || csvCensusList.size() == 0) {
			throw new WrongCSVException("File is empty", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Collections.sort(csvCensusList, Comparator.comparing(census -> census.getPopulationDensity()));
		System.out.println(csvCensusList);
		return new Gson().toJson(csvCensusList);
	}
	
	public String getStateAreaWiseSortedCensusData() throws WrongCSVException {
		if(csvCensusList == null || csvCensusList.size() == 0) {
			throw new WrongCSVException("File is empty", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
		Collections.sort(csvCensusList, Comparator.comparing(census -> census.getAreaData()));
		System.out.println(csvCensusList);
		return new Gson().toJson(csvCensusList);
	}
}
