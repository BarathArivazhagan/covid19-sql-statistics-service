package org.fiftyhands.statistics.app.repository;

import java.util.List;
import java.util.Optional;

import org.fiftyhands.statistics.app.entity.CovidCasesRawData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidCasesRawDataRepository  extends JpaRepository<CovidCasesRawData, String>{
	
	Optional<CovidCasesRawData> findByCountryCountryIdAndProvinceProvinceIdAndDate(Integer countryId, Integer provinceId, String date);
	List<CovidCasesRawData> findByCountryCountryIdAndProvinceProvinceId(Integer countryId, Integer provinceId);
	List<CovidCasesRawData> findByCountryCountryIdAndProvinceProvinceIdAndTodayCasesNotNull(Integer countryId, Integer provinceId);
	List<CovidCasesRawData> findByTodayCasesNull();
}
