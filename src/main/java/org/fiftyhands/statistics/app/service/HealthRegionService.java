package org.fiftyhands.statistics.app.service;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.dto.CovidStatsFromSource;
import org.fiftyhands.statistics.app.dto.HealthRegionDTO;
import org.fiftyhands.statistics.app.entity.Country;
import org.fiftyhands.statistics.app.entity.HealthRegion;
import org.fiftyhands.statistics.app.entity.Province;
import org.fiftyhands.statistics.app.repository.HealthRegionRepository;
import org.fiftyhands.statistics.app.utils.ProvinceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Service
public class HealthRegionService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final HealthRegionRepository healthRegionRepository;
	
	private final CountryService countryService;
	
	private final ProvinceService provinceService;
	
	private Map<String, Country> countriesMap;
	
	private Map<String,Province> provincesMap;

	public HealthRegionService(HealthRegionRepository healthRegionRepository,
			CountryService countryService,
			ProvinceService provinceService) {
		super();
		this.healthRegionRepository = healthRegionRepository;
		this.provinceService=provinceService;
		this.countryService=countryService;
	}
	
	
	public HealthRegion saveHealthRegion(HealthRegion healthRegion) {
		return this.healthRegionRepository.save(healthRegion);
	}

	public List<HealthRegion> getHealthRegions(){
		return this.healthRegionRepository.findAll();
	}
	
	public List<HealthRegion> getHealthRegionsByProvince(String provinceName){
		Province province = this.getProvinceByName(provinceName);
		return this.healthRegionRepository.findByProvinceProvinceId(province.getProvinceId());
	}
	
	public Optional<HealthRegion> isHealthExists(Integer provinceId, String healthRegion){
		return this.healthRegionRepository.findByNameAndProvinceProvinceId(healthRegion,provinceId);
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
	 try {
		 CsvMapper mapper = new CsvMapper();
		 CsvSchema schema = CsvSchema.emptySchema().withHeader();
		 List<HealthRegion> healthRegions = new ArrayList<>();
		 File csvFile=	new ClassPathResource("Health-Region.csv").getFile();
			MappingIterator<HealthRegionDTO> it = mapper.readerFor(HealthRegionDTO.class)
					   .with(schema)
					   .readValues(csvFile);
			while (it.hasNext()) {
				HealthRegionDTO row = it.next();
				if(logger.isDebugEnabled()) {
					logger.info(Objects.toString(row));
				}
				HealthRegion healthRegion = this.healthRegionMapper.apply(row);
				healthRegions.add(healthRegion);
				
			}
			if(csvFile !=null ) csvFile.delete(); // clean up the file. TODO: may be upload to blob storage for future retrievals

			healthRegions = healthRegions.stream().distinct().collect(Collectors.toList());
			System.out.println("HEALTH REGIONS SIZE "+healthRegions.size());
	  }catch (Exception e) {
		logger.error("Exception");
	  }
	}
	
	private Function<HealthRegionDTO,HealthRegion> healthRegionMapper = dto -> {
		
		HealthRegion healthRegion = new HealthRegion();
		healthRegion.setCountry(this.countriesMap.get("Canada"));
		healthRegion.setProvince(this.provincesMap.get(ProvinceUtils.resolveProvinceName(dto.getProvince())));
		healthRegion.setName(dto.getHealth_region());
		healthRegion.setLatitude(dto.getLatitude());
		healthRegion.setLongitude(dto.getLongitude());
		
		return healthRegion;
	};
	
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
