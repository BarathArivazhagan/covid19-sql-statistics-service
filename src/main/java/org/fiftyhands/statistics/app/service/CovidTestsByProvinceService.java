package org.fiftyhands.statistics.app.service;

import java.util.Optional;

import org.fiftyhands.statistics.app.entity.CovidTestsByProvince;
import org.fiftyhands.statistics.app.repository.CovidTestsByProvinceRepository;
import org.springframework.stereotype.Service;

@Service
public class CovidTestsByProvinceService {
	
	private final CovidTestsByProvinceRepository covidTestsByProvinceRepository;

	public CovidTestsByProvinceService(CovidTestsByProvinceRepository covidTestsByProvinceRepository) {
		super();
		this.covidTestsByProvinceRepository = covidTestsByProvinceRepository;
	}
	
	public CovidTestsByProvince saveCovidTestByProvince(CovidTestsByProvince provinceStas) {
		return this.covidTestsByProvinceRepository.save(provinceStas);
	}
	
	public Optional<CovidTestsByProvince> getTestByProvince(String provinceName){
		return this.covidTestsByProvinceRepository.findByProvinceProvinceName(provinceName);
	}
	
	public Optional<CovidTestsByProvince> getTestByProvinceId(Integer provinceId){
		return this.covidTestsByProvinceRepository.findByProvinceProvinceId(provinceId);
	}

}
