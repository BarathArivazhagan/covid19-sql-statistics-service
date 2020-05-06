package org.fiftyhands.statistics.app.service;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.dto.CovidStatsFromSource;
import org.fiftyhands.statistics.app.dto.TestCaseDTO;
import org.fiftyhands.statistics.app.entity.Country;
import org.fiftyhands.statistics.app.entity.CovidCasesByProvinceStats;
import org.fiftyhands.statistics.app.entity.CovidCasesRawData;
import org.fiftyhands.statistics.app.entity.CovidTestsByProvince;
import org.fiftyhands.statistics.app.entity.CovidTestsRawData;
import org.fiftyhands.statistics.app.entity.Province;
import org.fiftyhands.statistics.app.repository.CovidTestsByProvinceRepository;
import org.fiftyhands.statistics.app.repository.CovidTestsRawDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class CovidTestsRawDataService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final CovidTestsRawDataRepository covidTestsRawDataRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private final CovidTestsByProvinceService covidTestsByProvinceService;
	
	private final ProvinceService provinceService;
	
	private final CountryService countryService;
	
	private final CovidTestsByProvinceRepository covidTestsByProvinceRepository;
	
	private Map<String, Country> countriesMap;
	
	private Map<String,Province> provincesMap;

	public CovidTestsRawDataService(CovidTestsRawDataRepository covidTestsRawDataRepository,
			CovidTestsByProvinceService covidTestsByProvinceService,
			ProvinceService provinceService,
			CountryService countryService,
			CovidTestsByProvinceRepository covidTestsByProvinceRepository) {
		super();
		this.covidTestsRawDataRepository = covidTestsRawDataRepository;
		this.covidTestsByProvinceService=covidTestsByProvinceService;
		this.countryService=countryService;
		this.provinceService= provinceService;
		this.covidTestsByProvinceRepository= covidTestsByProvinceRepository;
	}


	public void saveSources(List<TestCaseDTO> sources) {
		List<CovidTestsRawData> rawDatas = sources.stream().map( source -> {
			CovidTestsRawData rawData = this.rawDataMapper.apply(source);
			Province province = this.getProvinceByName(source.getProvince());
			Country country = this.getCountryByName(source.getCountryName());
			rawData.setProvince(province);
			rawData.setCountry(country);
			return rawData;
		}).collect(Collectors.toList());
		String todayDate = Objects.toString(LocalDate.now().format(formatter));
		String yesterdayDate = Objects.toString(LocalDate.now().minusDays(1).format(formatter));
		rawDatas.forEach( rawData -> {
			 
			logger.debug("Inserting raw tests data {}",Objects.toString(rawData));
			//insert into raw data collection
		Optional<CovidTestsRawData> t=	this.covidTestsRawDataRepository.findByCountryCountryIdAndProvinceProvinceIdAndDateOfTesting(rawData.getCountry().getCountryId(),rawData.getProvince().getProvinceId(),rawData.getDateOfTesting());
		if( !t.isPresent()) {
			LocalDate dateTime = LocalDate.parse(rawData.getDateOfTesting(), formatter);
			Optional<CovidTestsRawData> optT=	this.covidTestsRawDataRepository.findByCountryCountryIdAndProvinceProvinceIdAndDateOfTesting(rawData.getCountry().getCountryId(),rawData.getProvince().getProvinceId(),dateTime.minusDays(1).toString());
			if(optT.isPresent()) {
				rawData.setTodayTests(rawData.getTotalTests() - optT.get().getTotalTests());
			}
			
			this.covidTestsRawDataRepository.save(rawData);
		}
		
		
		});
	
		List<CovidTestsRawData> todayStats=rawDatas.stream().filter( stat -> {
			
			if(logger.isDebugEnabled()) {
				logger.debug("stat date {}  today date {} yesterday date {}",stat.getDateOfTesting(),todayDate,yesterdayDate);
			}
			return stat.getDateOfTesting().equals(todayDate);
		}).collect(Collectors.toList());
		todayStats.stream().map(this.provinceDataMapper)
		 .forEach( provinceData -> {
			
			 Optional<CovidTestsByProvince> provinceStatsOpt = this.covidTestsByProvinceRepository.findByCountryCountryIdAndProvinceProvinceId(provinceData.getCountry().getCountryId(),provinceData.getProvince().getProvinceId());
			 
			 if(provinceStatsOpt.isPresent()) {
				 CovidTestsByProvince provinceStats = provinceStatsOpt.get();
				 logger.info("Record exists. Hence updating the record set {} ",Objects.toString(provinceData));
				  logger.info("Existing record set {} ",Objects.toString(provinceStats));
				  this.covidTestsByProvinceRepository.save(provinceStats);
			 }else {
				 
				 logger.info("Saving province stats {}",Objects.toString(provinceData));
				 this.covidTestsByProvinceRepository.save(provinceData);
			 }
			
		 });
	}

	public CovidTestsRawData saveCovidTestsRawData(CovidTestsRawData rawData) {
		return this.covidTestsRawDataRepository.save(rawData);
	}
	
	

	private Function<TestCaseDTO, CovidTestsRawData> rawDataMapper = source -> {
		CovidTestsRawData rawData = new CovidTestsRawData();
		if("NA".equalsIgnoreCase(source.getCumulative_testing())) {
			rawData.setTotalTests(0L);
		}else {
			rawData.setTotalTests(Long.parseLong(source.getCumulative_testing()));
		}
		
		rawData.setDateOfTesting(source.getDate_testing());
		
		return rawData;
	};
	
	private Function<CovidTestsRawData, CovidTestsByProvince> provinceDataMapper = rawData -> {
		CovidTestsByProvince provinceData = new CovidTestsByProvince();
		provinceData.setProvince(rawData.getProvince());
		provinceData.setCountry(rawData.getCountry());
		provinceData.setTotalTests(rawData.getTotalTests());
        provinceData.setLastUpdatedTime(Objects.toString(LocalDateTime.now()));
	
		
		return provinceData;
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
