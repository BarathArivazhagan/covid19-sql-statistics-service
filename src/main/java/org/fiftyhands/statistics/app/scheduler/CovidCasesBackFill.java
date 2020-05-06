package org.fiftyhands.statistics.app.scheduler;

import java.lang.invoke.MethodHandles;
import java.util.List;

import javax.annotation.PostConstruct;

import org.fiftyhands.statistics.app.entity.CovidCasesRawData;
import org.fiftyhands.statistics.app.entity.Province;
import org.fiftyhands.statistics.app.service.CovidCasesService;
import org.fiftyhands.statistics.app.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

//@Component
public class CovidCasesBackFill {
	
	private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	private final ObjectMapper mapper = new ObjectMapper();
	private final CovidCasesService covidCasesService;
	private final ProvinceService provinceService;

	public CovidCasesBackFill(CovidCasesService covidCasesService,
			ProvinceService provinceService) {
		super();
		this.covidCasesService = covidCasesService;
		this.provinceService=provinceService;
	}


	@PostConstruct
	public void backFillMissingTodayCases() {
		
		List<Province> provinces = this.provinceService.getProvincesByCountry("Canada");
		List<CovidCasesRawData> missingTodayCases = this.covidCasesService.getCovidCasesRawDataWithoutTodayCases();
//		List<CovidCasesRawData> datasToBefilled=provinces.stream().flatMap( province -> {
//			List<CovidCasesRawData> rawDatas = this.covidCasesService.getCovidCasesRawDataByProvince(province.getProvinceName(), "Canada");
//			
//			return rawDatas.stream();
//		}).filter( rawData -> {
//			return rawData.getTodayCases() ==null;
//		});
		missingTodayCases.forEach( rawData -> {
		});
		
	}

}
