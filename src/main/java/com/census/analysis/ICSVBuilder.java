package com.census.analysis;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
	public Iterator<E> getFileIterator(Reader reader, Class<E> csvClass) throws WrongCSVException;
}
