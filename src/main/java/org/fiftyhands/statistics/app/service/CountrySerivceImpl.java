package org.fiftyhands.statistics.app.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.entity.Country;
import org.fiftyhands.statistics.app.entity.Province;
import org.fiftyhands.statistics.app.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class CountrySerivceImpl implements CountryService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final CountryRepository countryRepository;

	public CountrySerivceImpl(CountryRepository countryRepository) {
		super();
		this.countryRepository = countryRepository;
	}
	
	@Override
	public List<Country> getCountries(){
		return this.countryRepository.findAll();
	}
	
	@Override
	public Country getCountryById(Integer id) {
		return this.countryRepository.findById(id).get();
	}
	
	//@PostConstruct
	public void init() {
		
		Optional<Country> countryOpt=this.countryRepository.findById(1);
		if(!countryOpt.isPresent()) {
			Country country = new Country();
			country.setCountryId(1);
			country.setCountryName("Canada");
			country.setPopulation(38056261L);
			country.setLatitude(56.1304);
			country.setLongitude(106.3468);
			this.countryRepository.save(country);
		}
	}

	@Override
	public Country getCountryByCountryName(String countryName) {
		return this.countryRepository.findByCountryName(countryName);
	}

}
