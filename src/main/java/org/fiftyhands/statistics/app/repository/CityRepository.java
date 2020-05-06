package org.fiftyhands.statistics.app.repository;

import java.util.List;

import org.fiftyhands.statistics.app.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CityRepository extends JpaRepository<City, String> {
	
	
	List<City> findAllByProvinceProvinceName(String provinceName);
	
}
