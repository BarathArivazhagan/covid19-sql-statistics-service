package org.fiftyhands.statistics.app.repository;

import java.util.List;
import java.util.Optional;

import org.fiftyhands.statistics.app.entity.CovidCasesByProvinceStats;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CovidCasesByProvinceRepository extends JpaRepository<CovidCasesByProvinceStats, Integer> {
	
	
	List<CovidCasesByProvinceStats> findAllByCountryCountryId(Integer countryId);

	Optional<CovidCasesByProvinceStats> findByProvinceProvinceId(Integer provinceId);
	
	Optional<CovidCasesByProvinceStats> findByCountryCountryIdAndProvinceProvinceId(Integer countryId,Integer provinceId);

}
