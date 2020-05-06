package org.fiftyhands.statistics.app.service;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.fiftyhands.statistics.app.dto.CovidMortalityDTO;
import org.fiftyhands.statistics.app.entity.Country;
import org.fiftyhands.statistics.app.entity.CovidMortalityRawData;
import org.fiftyhands.statistics.app.repository.CovidMortalityRawDataRepository;
import org.springframework.stereotype.Service;

@Service
public class CovidMortalityRawDataServiceImpl implements CovidMortalityRawDataService {
	
	private final CovidMortalityRawDataRepository covidMortalityRawDataRepository;
	
	private final ProvinceService provinceService;
	

	public CovidMortalityRawDataServiceImpl(CovidMortalityRawDataRepository covidMortalityRawDataRepository,
			ProvinceService provinceService,
			CountryService countryService) {
		super();
		this.covidMortalityRawDataRepository = covidMortalityRawDataRepository;
		this.provinceService=provinceService;
	}
	
	@Override
	public void saveMortalitySources(List<CovidMortalityDTO> sources) {
		Country country = new Country();
		country.setCountryId(1);
		country.setCountryName("Canada"); // canada datasource
		Long existingRecords= getCovidMortalityCount();
		List<CovidMortalityRawData> datas =sources.stream().skip(existingRecords).map( source -> {
			CovidMortalityRawData rawData = this.rawDataMapper.apply(source);
			rawData.setProvince(this.provinceService.getProvinceByName(source.getProvince()));
			rawData.setCountry(country);
			return rawData;
		}).collect(Collectors.toList());
		this.saveCovidMortalityRawDatas(datas);
	}
	
	@Override
	public CovidMortalityRawData saveCovidMortalityRawData(CovidMortalityRawData data) {
		return this.covidMortalityRawDataRepository.save(data);
	}
	
	@Override
	public List<CovidMortalityRawData> saveCovidMortalityRawDatas(List<CovidMortalityRawData> datas) {
		return this.covidMortalityRawDataRepository.saveAll(datas);
	}
	
	@Override
	public Long getCovidMortalityCount() {
		return this.covidMortalityRawDataRepository.count();
	}
	
	private Function<CovidMortalityDTO, CovidMortalityRawData> rawDataMapper = source -> {
		CovidMortalityRawData rawData = new CovidMortalityRawData();
		rawData.setId(source.getDeath_id());
		rawData.setCaseId(source.getCase_id());
		rawData.setProvinceDeathId(source.getProvince_death_id());
		rawData.setAge(source.getAge());
		rawData.setSex(source.getSex());
		rawData.setHealthRegion(source.getHealth_region());
		rawData.setAdditionalInfo(source.getAdditional_info());
		rawData.setDeathSource(source.getDeath_source());
		rawData.setDateDeathReport(source.getDate_death_report());
		return rawData;
	};
	


}
