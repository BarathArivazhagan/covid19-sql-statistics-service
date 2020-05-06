package org.fiftyhands.statistics.app.repository;

import java.util.Optional;

import org.fiftyhands.statistics.app.entity.CovidTestsRawData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CovidTestsRawDataRepository  extends JpaRepository<CovidTestsRawData, Long> {
	
	Optional<CovidTestsRawData> findByCountryCountryIdAndProvinceProvinceIdAndDateOfTesting(Integer countryId, Integer provinceId, String dateOfTesting);
}
