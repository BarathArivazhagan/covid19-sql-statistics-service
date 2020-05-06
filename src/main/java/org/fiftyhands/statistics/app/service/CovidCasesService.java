package org.fiftyhands.statistics.app.service;

import java.util.List;
import java.util.Optional;

import org.fiftyhands.statistics.app.entity.CovidCasesRawData;

public interface CovidCasesService {
	
	List<CovidCasesRawData> getCovidCasesRawDataByProvince(String provinceName, String countryName);
	
	Optional<CovidCasesRawData> getCovidCasesRawDataByProvince(String provinceName, String countryName,String date);
	
	Optional<CovidCasesRawData> getCovidCasesRawDataByProvince(Integer provinceId, Integer countryId,String date);
	
	Long getCovidCasesCount();
	
	List<CovidCasesRawData> getCovidCasesRawDataWithoutTodayCases();
	
	CovidCasesRawData saveCovidCasesRawData(CovidCasesRawData rawData);

}
