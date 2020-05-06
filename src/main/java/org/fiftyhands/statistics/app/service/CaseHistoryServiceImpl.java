package org.fiftyhands.statistics.app.service;

import java.lang.invoke.MethodHandles;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.dto.CaseHistorySource;
import org.fiftyhands.statistics.app.entity.CaseHistory;
import org.fiftyhands.statistics.app.entity.Country;
import org.fiftyhands.statistics.app.entity.Province;
import org.fiftyhands.statistics.app.repository.CaseHistoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CaseHistoryServiceImpl implements CaseHistoryService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final CaseHistoryRepository caseHistoryRepository;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	private CountryService countryService;
	
	
	private Map<String, Country> countriesMap;
	
	private Map<String,Province> provincesMap;

	public CaseHistoryServiceImpl(CaseHistoryRepository caseHistoryRepository) {
		super();
		this.caseHistoryRepository = caseHistoryRepository;
	}
	
	@Override
	public void saveCaseHistories(List<CaseHistorySource> sources) {
		logger.info("Saving Case histories ");
		 Long caseHistoryCount = this.getCaseHistoryCount();
		sources.stream().skip(caseHistoryCount).forEach( source -> {
			
			CaseHistory caseHistory =this.caseHistoryMapper.apply(source);
			Province province= this.getProvinceByName(source.getProvince());
			caseHistory.setProvince(province);
			Country country = this.getCountryByName("Canada");
			caseHistory.setCountry(country);
			logger.info("Case History {}",Objects.toString(caseHistory));
			this.caseHistoryRepository.save(caseHistory);
		});
		
			
	}
		
	@Override
	public Long getCaseHistoryCount() {
		return this.caseHistoryRepository.count();
	}
	
	private Function<CaseHistorySource,CaseHistory> caseHistoryMapper = historySource -> {
		CaseHistory caseHistory = new CaseHistory();
		caseHistory.setCaseId(historySource.getCase_id());
		caseHistory.setProvincialCaseId(historySource.getProvincial_case_id());
		caseHistory.setAgeRange(historySource.getAge());
		caseHistory.setGender(historySource.getSex());
		caseHistory.setHealthRegion(historySource.getHealth_region());
		caseHistory.setDateReport(historySource.getDate_report());
		caseHistory.setReportWeek(historySource.getReport_week());
		caseHistory.setTravelYn(historySource.getTravel_yn());
		caseHistory.setTravelHistoryCountry(historySource.getTravel_history_country());
		caseHistory.setLocallyAcquired(historySource.getLocally_acquired());

		return caseHistory;
	};
	

	@PostConstruct
	public void init() {
	 List<Country> countries= 	this.countryService.getCountries();
	 
	 List<Province> provinces= this.provinceService.getProvincesByCountry("Canada");
	 
	 countriesMap = new HashMap<>(countries.size());
	 provincesMap = new HashMap<>(provinces.size());
	 countries.stream().forEach(  country -> {
		 countriesMap.put(country.getCountryName(), country);
	 });
	 
	 provinces.stream().forEach( province -> {
		 provincesMap.put(province.getProvinceName(), province);
	 });
		
	}
	
	private Province getProvinceByName(String province) {
		
		if(provincesMap.isEmpty() || countriesMap.isEmpty()) {
			init();
		}
		return this.provincesMap.get(province);
	}
	
	private Country getCountryByName(String country) {
		
		if(provincesMap.isEmpty() || countriesMap.isEmpty()) {
			init();
		}
		return this.countriesMap.get(country);
	}

}
