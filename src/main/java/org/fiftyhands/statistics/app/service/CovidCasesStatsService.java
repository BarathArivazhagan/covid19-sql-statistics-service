package org.fiftyhands.statistics.app.service;

import java.util.List;

import org.fiftyhands.statistics.app.dto.CovidCasesByCountryStats;
import org.fiftyhands.statistics.app.dto.CovidStatsFromSource;
import org.fiftyhands.statistics.app.entity.CovidCasesByProvinceStats;



public interface CovidCasesStatsService {
	
	void saveCovidStatsFromSource(List<CovidStatsFromSource> sources);
	
	List<CovidCasesByProvinceStats> getProvinceWiseStatsByCountry(String countryName);
	
	List<CovidCasesByProvinceStats> getAllProvinceWiseStats();
	
	List<CovidCasesByCountryStats> getCountryWiseStats();

	List<CovidCasesByProvinceStats> getCityWiseStats();

	CovidCasesByCountryStats getCountryWiseStats(String countryName);
	
	Long getAllProvinceWiseStatsCount();

}
