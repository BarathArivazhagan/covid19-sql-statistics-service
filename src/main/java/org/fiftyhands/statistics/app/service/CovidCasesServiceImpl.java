package org.fiftyhands.statistics.app.service;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Optional;

import org.fiftyhands.statistics.app.entity.Country;
import org.fiftyhands.statistics.app.entity.CovidCasesRawData;
import org.fiftyhands.statistics.app.entity.Province;
import org.fiftyhands.statistics.app.repository.CovidCasesRawDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CovidCasesServiceImpl implements CovidCasesService {
	
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final CovidCasesRawDataRepository casesRawDataRepository;
	private final ProvinceService provinceService;
	private final CountryService countryService;
	
	public CovidCasesServiceImpl(CovidCasesRawDataRepository casesRawDataRepository,
			ProvinceService provinceService,
			CountryService countryService) {
		super();
		this.casesRawDataRepository = casesRawDataRepository;
		this.provinceService=provinceService;
		this.countryService=countryService;
	}

	@Override
	public List<CovidCasesRawData> getCovidCasesRawDataByProvince(String provinceName, String countryName) {
		Province province = this.provinceService.getProvinceByName(provinceName);
		Country country = this.countryService.getCountryByCountryName(countryName);
		return this.casesRawDataRepository.findByCountryCountryIdAndProvinceProvinceId(country.getCountryId(), province.getProvinceId() );
	}
	@Override
	public Optional<CovidCasesRawData> getCovidCasesRawDataByProvince(String provinceName, String countryName,String date) {
		Province province = this.provinceService.getProvinceByName(provinceName);
		Country country = this.countryService.getCountryByCountryName(countryName);
		return this.casesRawDataRepository.findByCountryCountryIdAndProvinceProvinceIdAndDate(country.getCountryId(), province.getProvinceId(),date );
	}
	
	@Override
	public List<CovidCasesRawData> getCovidCasesRawDataWithoutTodayCases(){
		return this.casesRawDataRepository.findByTodayCasesNull();
	}
	
	@Override
	public CovidCasesRawData saveCovidCasesRawData(CovidCasesRawData rawData) {
		return this.casesRawDataRepository.save(rawData);
	}

	public Long getCovidCasesCount() {
		return this.casesRawDataRepository.count();
	}

	@Override
	public Optional<CovidCasesRawData> getCovidCasesRawDataByProvince(Integer provinceId, Integer countryId,
			String date) {
		return this.casesRawDataRepository.findByCountryCountryIdAndProvinceProvinceIdAndDate(countryId, provinceId, date);
	}

}
