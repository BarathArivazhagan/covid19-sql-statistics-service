package org.fiftyhands.statistics.app.repository;

import org.fiftyhands.statistics.app.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CountryRepository extends JpaRepository<Country, Integer>{
	
	Country findByCountryName(String countryName);
}
