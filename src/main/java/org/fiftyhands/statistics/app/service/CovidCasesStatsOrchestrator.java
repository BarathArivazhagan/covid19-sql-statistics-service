package org.fiftyhands.statistics.app.service;

import java.lang.invoke.MethodHandles;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.dto.CovidCasesByCountryStats;
import org.fiftyhands.statistics.app.dto.CovidStatsFromSource;
import org.fiftyhands.statistics.app.entity.Country;
import org.fiftyhands.statistics.app.entity.CovidCasesByProvinceStats;
import org.fiftyhands.statistics.app.entity.CovidCasesRawData;
import org.fiftyhands.statistics.app.entity.Province;
import org.fiftyhands.statistics.app.repository.CovidCasesByProvinceRepository;
import org.fiftyhands.statistics.app.repository.CovidCasesRawDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;




@Service
public class CovidCasesStatsOrchestrator implements CovidCasesStatsService{
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final CovidCasesByProvinceRepository casesByProvinceRepository;
	
	private final CovidCasesRawDataRepository casesRawDataRepository;
	
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	
	private final CountryService countryService;
	
	private final ProvinceService provinceService;
	
	private Map<String, Country> countriesMap;
	
	private Map<String,Province> provincesMap;

	public CovidCasesStatsOrchestrator(CovidCasesByProvinceRepository casesByProvinceRepository,
			CovidCasesRawDataRepository casesRawDataRepository,
			CountryService countryService,
			ProvinceService provinceService) {
		super();
		this.casesByProvinceRepository = casesByProvinceRepository;
		this.casesRawDataRepository= casesRawDataRepository;
		this.countryService= countryService;
		this.provinceService= provinceService;
	}
	
	
	public void saveCovidStatsFromSource(List<CovidStatsFromSource> sources) {
		
		List<CovidCasesRawData> rawDatas = sources.stream().skip(getTotalRawDataCount()).map( source -> {
			CovidCasesRawData rawData = this.rawDataMapper.apply(source);
			Province province = this.getProvinceByName(source.getPrname());
			Country country = this.getCountryByName(source.getCountryName());
			rawData.setProvince(province);
			rawData.setCountry(country);
			return rawData;
		}).collect(Collectors.toList());
		
		rawDatas.forEach( rawData -> {
			 
			logger.debug("Inserting raw data {}",Objects.toString(rawData));
			//insert into raw data collection
		Optional<CovidCasesRawData> t=	this.casesRawDataRepository.findByCountryCountryIdAndProvinceProvinceIdAndDate(rawData.getCountry().getCountryId(),rawData.getProvince().getProvinceId(),rawData.getDate());
		if( !t.isPresent()) {
			this.casesRawDataRepository.save(rawData);
		}
		
		//			.switchIfEmpty( () -> {
//				 this.casesRawDataRepository.save(rawData);
//			}).log().subscribe();
			
			
		
		});
		String todayDate = Objects.toString(LocalDate.now().format(formatter));
		String yesterdayDate = Objects.toString(LocalDate.now().minusDays(1).format(formatter));
		List<CovidCasesRawData> todayStats=rawDatas.stream().filter( stat -> {
			
			if(logger.isDebugEnabled()) {
				logger.debug("stat date {}  today date {} yesterday date {}",stat.getDate(),todayDate,yesterdayDate);
			}
			return stat.getDate().equals(todayDate);
		}).collect(Collectors.toList());
		todayStats.stream().map(this.provinceDataMapper)
		 .forEach( provinceData -> {
			
			 Optional<CovidCasesByProvinceStats> provinceStatsOpt = this.casesByProvinceRepository.findByCountryCountryIdAndProvinceProvinceId(provinceData.getCountry().getCountryId(),provinceData.getProvince().getProvinceId());
			 
			 if(provinceStatsOpt.isPresent()) {
				 CovidCasesByProvinceStats provinceStats = provinceStatsOpt.get();
				 logger.info("Record exists. Hence updating the record set {} ",Objects.toString(provinceData));
				  logger.info("Existing record set {} ",Objects.toString(provinceStats));
				  provinceStats.setTodayCases(provinceData.getTodayCases());
				  provinceStats.setDeathCases(provinceData.getDeathCases());
				  provinceStats.setRecoveredCases(provinceData.getRecoveredCases());
				  provinceStats.setTotalCases(provinceData.getTotalCases());
				  provinceStats.setConfirmCases(provinceData.getConfirmCases());
				  this.casesByProvinceRepository.save(provinceStats);
			 }else {
				 
				 logger.info("Saving province stats {}",Objects.toString(provinceData));
				 this.casesByProvinceRepository.save(provinceData);
			 }
			
		 });
	
	}


	@Override
	public List<CovidCasesByProvinceStats> getProvinceWiseStatsByCountry(String countryName) {
		Country country = this.countryService.getCountryByCountryName(countryName);
		return this.casesByProvinceRepository.findAllByCountryCountryId(country.getCountryId()).stream()
				.filter( record -> !countryName.equals(record.getProvince().getProvinceName()) || !"Repatriated travellers".equals(record.getProvince().getProvinceName()))
				.collect(Collectors.toList()); // required as country record is also maintained as province dataset

	}

	@Override
	public List<CovidCasesByProvinceStats> getAllProvinceWiseStats() {
		return this.casesByProvinceRepository.findAll();
	}
	
	public Long getTotalRawDataCount() {
		return this.casesRawDataRepository.count();
	}

	@Override
	public CovidCasesByCountryStats getCountryWiseStats(String countryName) {
		
		CovidCasesByCountryStats countryStats =new CovidCasesByCountryStats();
	  return null;
			  
//			  this.casesByProvinceRepository.findByProvinceName(countryName)
//	    .map( provinceStats -> {// event country stats are maintained as province stats with country id as province id
//			countryStats.setConfirmCases(provinceStats.getConfirmCases());
//			countryStats.setDeathCases(provinceStats.getDeathCases());
//			countryStats.setTotalCases(provinceStats.getTotalCases());
//			countryStats.setProbableCases(provinceStats.getProbableCases());
//			countryStats.setRecoveredCases(provinceStats.getRecoveredCases());
//			countryStats.setTodayCases(provinceStats.getTodayCases());
//			countryStats.setCountryName(provinceStats.getCountryName());
//			return countryStats;
//	    });
	}
	
	@Override
	public List<CovidCasesByCountryStats> getCountryWiseStats() {
		
		return Collections.emptyList();

	}



	@Override
	public List<CovidCasesByProvinceStats> getCityWiseStats() {
		// TODO Auto-generated method stub
		return Collections.emptyList();
	}
	
	
	private Function<CovidStatsFromSource, CovidCasesRawData> rawDataMapper = source -> {
		CovidCasesRawData rawData = new CovidCasesRawData();

		rawData.setConfirmCases(source.getNumconf());
		rawData.setDeathCases(source.getNumdeaths());
		rawData.setProbableCases(source.getNumprob());
		if("N/A".equals(source.getNumrecover()) || StringUtils.isEmpty(source.getNumrecover())) {
			rawData.setRecoveredCases(0L);
		}else {
			rawData.setRecoveredCases(Long.parseLong(source.getNumrecover()));
		}
		rawData.setTotalCases(source.getNumtotal());
		rawData.setTodayCases(source.getNumtoday());
		rawData.setDate(source.getDate());
		rawData.setCreated_date(Objects.toString(LocalDateTime.now()));
	
		
		return rawData;
	};
	
	private Function<CovidCasesRawData, CovidCasesByProvinceStats> provinceDataMapper = rawData -> {
		CovidCasesByProvinceStats provinceData = new CovidCasesByProvinceStats();
		provinceData.setProvince(rawData.getProvince());
		provinceData.setCountry(rawData.getCountry());
		provinceData.setConfirmCases(rawData.getConfirmCases());
		provinceData.setDeathCases(rawData.getDeathCases());
		provinceData.setProbableCases(rawData.getProbableCases());
		
		provinceData.setRecoveredCases(rawData.getRecoveredCases());

		provinceData.setTotalCases(rawData.getTotalCases());
		provinceData.setTodayCases(rawData.getTodayCases());
        provinceData.setLastUpdatedTime(Objects.toString(LocalDateTime.now()));
	
		
		return provinceData;
	};

	@Override
	public Long getAllProvinceWiseStatsCount() {
		return this.casesByProvinceRepository.count();
	}
	
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
