package org.fiftyhands.statistics.app.repository;

import java.util.Optional;

import org.fiftyhands.statistics.app.entity.CovidTestsByProvince;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidTestsByProvinceRepository extends JpaRepository<CovidTestsByProvince, Long> {

	Optional<CovidTestsByProvince> findByProvinceProvinceName(String provinceName);
	
	Optional<CovidTestsByProvince> findByProvinceProvinceId(Integer provinceId);

	Optional<CovidTestsByProvince> findByCountryCountryIdAndProvinceProvinceId(Integer countryId,
			Integer provinceId);
}
