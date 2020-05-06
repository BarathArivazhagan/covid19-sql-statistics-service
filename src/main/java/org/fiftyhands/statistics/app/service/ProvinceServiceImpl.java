package org.fiftyhands.statistics.app.service;

import java.io.File;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.dto.CovidStatsFromSource;
import org.fiftyhands.statistics.app.dto.GeoProvinceStatsDTO;
import org.fiftyhands.statistics.app.entity.Country;
import org.fiftyhands.statistics.app.entity.Province;
import org.fiftyhands.statistics.app.repository.ProvinceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

@Service
public class ProvinceServiceImpl implements ProvinceService {
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	
	private final ProvinceRepository provinceRepository;
	
	public ProvinceServiceImpl(ProvinceRepository provinceRepository) {
		super();
		this.provinceRepository = provinceRepository;
	}
	
	public List<Province> getProvincesByCountry(String countryName){
		return this.provinceRepository.findAllByCountryCountryName(countryName);
	}
	
	public List<Province> getProvince(){
		return this.provinceRepository.findAll();
	} 
	
	public Province getProvinceByName(String provinceName) {
		return this.provinceRepository.findByProvinceName(provinceName).stream().findFirst().get();
	}
	
	//@PostConstruct
	public void init() throws IOException {
		
	    CsvSchema schema = CsvSchema.emptySchema().withHeader();
	    CsvMapper mapper = new CsvMapper();
		String fileName="geo-province.csv";
		Resource resource= new ClassPathResource(fileName);
		File csvFile =resource.getFile();
		List<CovidStatsFromSource> stats = new ArrayList<>();
		MappingIterator<GeoProvinceStatsDTO> it = mapper.readerFor(GeoProvinceStatsDTO.class)
				   .with(schema)
				   .readValues(csvFile);
		while (it.hasNext()) {
			GeoProvinceStatsDTO row = it.next();
			if(logger.isDebugEnabled()) {
				logger.info(Objects.toString(row));
			}
			Optional<Province> provinceOpt=this.provinceRepository.findByProvinceId(row.getPruid());
			if(!provinceOpt.isPresent()) {
				
				Province province = new Province();
				province.setProvinceId(row.getPruid());
				province.setPopulation(row.getPopulation());
				province.setProvinceFR(row.getPrnameFR());
				province.setProvinceName(row.getPrname());
				province.setLatitude(row.getLatitude());
				province.setLongitude(row.getLongitude());
				Country country = new Country();
				country.setCountryId(1);
				country.setCountryName("Canada");
				province.setCountry(country);
				logger.info("Savin province with details {}",Objects.toString(province));
				if(province.getProvinceId() !=null) {
					this.provinceRepository.save(province);
				}
			}
			
		}
	}

}
