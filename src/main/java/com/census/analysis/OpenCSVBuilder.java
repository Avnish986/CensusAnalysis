package com.census.analysis;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder<E> implements ICSVBuilder<E> {
	@Override
	public Iterator<E> getFileIterator(Reader reader, Class csvClass) throws WrongCSVException {
		try {
			CsvToBean<E> csvToBean = new CsvToBeanBuilder(reader)
										.withType(csvClass)
										.withIgnoreLeadingWhiteSpace(true)
										.build();

			return csvToBean.iterator();
		} catch (RuntimeException e) {
			throw new WrongCSVException("File internal data not valid", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
	}

	@Override
	public List<E> getCSVList(Reader reader, Class csvClass) throws WrongCSVException {
		return this.getCSVBean(reader, csvClass).parse();
	}

	private CsvToBean<E> getCSVBean(Reader reader, Class csvClass) throws WrongCSVException {
		try {
			CsvToBeanBuilder<E> csvToBeanBuilder = new CsvToBeanBuilder<E>(reader);
			csvToBeanBuilder.withType(csvClass);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			return csvToBeanBuilder.build();
		} catch (RuntimeException e) {
			throw new WrongCSVException("File internal data not valid", WrongCSVException.ExceptionType.WRONG_HEADER);
		}
	}
}
