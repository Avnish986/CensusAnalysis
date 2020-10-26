package com.census.analysis;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
	public Iterator<E> getFileIterator(Reader reader, Class<E> csvClass) throws WrongCSVException;
	public List<E> getCSVList(Reader reader, Class csvClass) throws WrongCSVException;
}
