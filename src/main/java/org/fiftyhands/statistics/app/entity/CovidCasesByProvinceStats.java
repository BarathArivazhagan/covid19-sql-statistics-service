package org.fiftyhands.statistics.app.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity(name = "covid_cases_stats_by_province")
public class CovidCasesByProvinceStats extends CovidCasesCommonEntity{
	
	
	@Id
	@GeneratedValue
	private Integer id;
	
	private String lastUpdatedTime;

	public String getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(String lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CovidCasesByProvinceStats() {
		super();
	}
	
	
	
}
