package com.census.analysis;

public class CSVBuilderFactory<E> {
	public static ICSVBuilder createCSVBuilder() {
		//return new OpenCSVBuilder();
		return new CommonCSVBuilder<>();
	}
}
