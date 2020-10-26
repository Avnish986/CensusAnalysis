package com.census.analysis;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	@CsvBindByName(column = "State", required = true)
	public String state;

	@CsvBindByName(column = "Population", required = true)
	public String population;

	@CsvBindByName(column = "AreaInSqKm", required = true)
	public int areaInSqKm;

	@CsvBindByName(column = "DensityPerSqKm", required = true)
	public int densityPerSqKm;

	public int getPopulationData() {
		int pop = Integer.parseInt(population);
		return pop;
	}
	public int getPopulationDensity() {		
		return densityPerSqKm;
	}
	public int getAreaData() {		
		return areaInSqKm;
	}

	@Override
	public String toString() {
		return "IndiaCensusCSV{" + "State='" + state + '\'' + ", Population='" + population + '\'' + ", AreaInSqKm='"
				+ areaInSqKm + '\'' + ", DensityPerSqKm='" + densityPerSqKm + '\'' + '}';
	}
}