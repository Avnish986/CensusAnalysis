package com.census.analysis;

import java.io.Reader;
import java.util.Iterator;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {
	@Override
	public Iterator<E> getFileIterator(Reader reader, Class csvClass) throws CensusException {
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
