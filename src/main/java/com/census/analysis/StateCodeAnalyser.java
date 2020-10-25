package com.census.analysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class StateCodeAnalyser {
	public int loadCSVFile(Path path) throws WrongCSVException {
		try {
			Reader reader = Files.newBufferedReader(path);
			CsvToBean<StateCode> csvToBean = new CsvToBeanBuilder(reader).withType(StateCode.class)
					.withIgnoreLeadingWhiteSpace(true).build();
			Iterator<StateCode> it = csvToBean.iterator();
			ArrayList<StateCode> stateCensusList = new ArrayList<>();
			while (it.hasNext()) {
				stateCensusList.add(it.next());
			}
			for(StateCode x :stateCensusList ) {
				System.out.println(x);
			}
			return stateCensusList.size();
		} catch (IOException e) {
			throw new WrongCSVException("File not found", WrongCSVException.ExceptionType.WRONG_CSV);
		}
		catch (RuntimeException e) {
			throw new WrongCSVException("File internal data not valid", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
	}
}