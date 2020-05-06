package org.fiftyhands.statistics.app.repository;

import java.util.List;
import java.util.Optional;

import org.fiftyhands.statistics.app.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProvinceRepository extends JpaRepository<Province, String> {
	
	List<Province> findAllByCountryCountryName(String countryName);
	
	List<Province> findByProvinceName(String provinceName);
	
	Optional<Province> findByProvinceId(Integer provinceId);

}
