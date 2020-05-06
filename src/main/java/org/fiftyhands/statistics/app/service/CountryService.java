package org.fiftyhands.statistics.app.service;

import java.util.List;

import org.fiftyhands.statistics.app.entity.Country;

public interface CountryService {

	List<Country> getCountries();

	Country getCountryById(Integer id);
	
	Country getCountryByCountryName(String countryName);

}