package org.fiftyhands.statistics.app.service;

import java.util.List;

import org.fiftyhands.statistics.app.entity.Province;

public interface ProvinceService {
	
	 List<Province> getProvincesByCountry(String countryName);
	 Province getProvinceByName(String provinceName);

}
