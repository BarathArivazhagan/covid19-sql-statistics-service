package org.fiftyhands.statistics.app.controller;

import java.util.List;

import org.fiftyhands.statistics.app.dto.CovidCasesByCountryStats;
import org.fiftyhands.statistics.app.entity.CovidCasesByProvinceStats;
import org.fiftyhands.statistics.app.service.CovidCasesStatsService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
@Api(value = "Covid Statistics APIs ", tags = { "Covid Statistics APIs" })
public class CovidStatsController {
	
	private final CovidCasesStatsService casesStatsService;
	
	
	
	public CovidStatsController(CovidCasesStatsService casesStatsService) {
		super();
		this.casesStatsService = casesStatsService;
	}



	@GetMapping(value = "/province/stats")
	public List<CovidCasesByProvinceStats> getProvinceWiseStats(@RequestParam String countryName){
		return this.casesStatsService.getProvinceWiseStatsByCountry(countryName);
	}
	
	@GetMapping(value = "/country/stats")
	public CovidCasesByCountryStats getCountryWiseStats(@RequestParam String countryName){
		return this.casesStatsService.getCountryWiseStats(countryName);
	}
	
	@GetMapping(value = "/city/stats")
	public List<CovidCasesByProvinceStats> getCityWiseStats(){
		return this.casesStatsService.getCityWiseStats();
	}
	
	@GetMapping(value = "/province/stats/count")
	public Long getAllProvinceStatsCount(){
		return this.casesStatsService.getAllProvinceWiseStatsCount();
	}


}
