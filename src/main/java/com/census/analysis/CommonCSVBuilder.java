package com.census.analysis;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class CommonCSVBuilder<E> implements ICSVBuilder<E> {

	@Override
	public Iterator<E> getFileIterator(Reader reader, Class<E> csvClass) throws WrongCSVException {
		try {
			CSVParser csvParser = CSVParser.parse(reader, CSVFormat.DEFAULT.withHeaderComments(csvClass));
			return (Iterator<E>) csvParser.iterator();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<E> getCSVList(Reader reader, Class csvClass) throws WrongCSVException {
		// TODO Auto-generated method stub
		return null;
	}
}